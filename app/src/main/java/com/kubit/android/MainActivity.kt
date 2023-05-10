package com.kubit.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kubit.android.ui.screen.KubitApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KubitApp(
                kubitViewModel = viewModel<KubitViewModel>(
                    factory = KubitViewModel.KubitViewModelFactory(
                        application = application
                    )
                ).apply {
                    requestMarketCode()
                }
            )
        }
    }

    // region Util Function
    private fun showProgress(pMsg: String = "") {

    }

    private fun dismissProgress() {

    }

    private fun showToastMsg(pMsg: String) {
        Toast.makeText(this, pMsg, Toast.LENGTH_SHORT).show()
    }

    private fun showErrorMsg() {
        Toast.makeText(
            this,
            resources.getText(R.string.toast_msg_error_001),
            Toast.LENGTH_SHORT
        ).show()
    }
    // endregion Util Function

    companion object {
        private const val TAG: String = "MainActivity"
    }

}