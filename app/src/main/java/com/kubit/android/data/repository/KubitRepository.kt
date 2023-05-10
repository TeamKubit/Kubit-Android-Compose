package com.kubit.android.data.repository

import android.app.Application
import com.kubit.android.data.model.market.KubitMarketData
import com.kubit.android.data.model.network.NetworkResult
import com.kubit.android.data.util.JsonParserUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException

class KubitRepository(
    private val application: Application
) : BaseNetworkRepository(application = application, TAG = TAG) {

    private val jsonParserUtil: JsonParserUtil = JsonParserUtil()

    suspend fun makeMarketCodeRequest(): NetworkResult<KubitMarketData> {
        return withContext(Dispatchers.IO) {
            val strUrl = "${UPBIT_API_HOST_URL}market/all"
            val hsParams = HashMap<String, String>().apply {
                put("isDeatils", "true")
            }
            val message = sendRequest(strUrl, hsParams, "GET")

            val result = if (message.isNotEmpty()) {
                val jsonRoot = try {
                    JSONArray(message)
                } catch (e: JSONException) {
                    JSONArray()
                }

                val data = jsonParserUtil.getKubitMarketData(jsonRoot)
                if (data.isValid) {
                    NetworkResult.Success(data)
                } else {
                    NetworkResult.Fail("Response Data is Empty")
                }
            } else {
                NetworkResult.Error(Exception("Can't Open Connection"))
            }

            result
        }
    }

    companion object {
        private const val TAG: String = "KubitRepository"
    }

}