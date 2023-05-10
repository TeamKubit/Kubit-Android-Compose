package com.kubit.android

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kubit.android.data.model.market.KubitMarketData
import com.kubit.android.data.model.network.NetworkResult
import com.kubit.android.data.repository.KubitRepository
import com.kubit.android.data.util.DLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KubitViewModel(
    private val kubitRepository: KubitRepository
) : ViewModel() {

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var _marketCode: KubitMarketData? = null
    private val marketCode: KubitMarketData get() = _marketCode!!

    fun requestMarketCode() {
        DLog.d("${TAG}_requestMarketCode", "requestMarketCode() is called!")
        viewModelScope.launch {
            when (val result = kubitRepository.makeMarketCodeRequest()) {
                is NetworkResult.Success<KubitMarketData> -> {
                    _marketCode = result.data
                    DLog.d("${TAG}_requestMarketCode", "marketCode=$marketCode")

                    _isLoading.value = false
                }

                is NetworkResult.Fail -> {

                }

                is NetworkResult.Error -> {

                }
            }
        }
    }

    class KubitViewModelFactory(private val application: Application) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = KubitViewModel(
            KubitRepository(application)
        ) as T
    }

    companion object {
        private const val TAG: String = "KubitViewModel"
    }

}