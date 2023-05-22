package com.kubit.android.data.util

import com.kubit.android.data.model.coin.CoinSnapshotData
import com.kubit.android.data.model.coin.KubitCoinInfoData
import com.kubit.android.data.model.coin.PriceChangeType
import com.kubit.android.data.model.market.KubitMarketData
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.Locale

class JsonParserUtil {

    // region Base Function
    fun getString(jsonObj: JSONObject, key: String, strDefault: String = "") =
        if (jsonObj.has(key) && !jsonObj.isNull(key)) jsonObj.getString(key)
        else strDefault

    fun getBoolean(jsonObj: JSONObject, key: String, default: Boolean = false): Boolean {
        return if (jsonObj.has(key) && !jsonObj.isNull(key)) {
            val value = jsonObj.getString(key).trim()

            when (value.lowercase(Locale.ROOT)) {
                "yes",
                "true",
                "y",
                "1" -> true

                else -> false
            }
        } else {
            default
        }
    }

    fun getInt(jsonObj: JSONObject, key: String, intDefault: Int = -1): Int {
        return if (jsonObj.has(key) && !jsonObj.isNull(key)) {
            val value = jsonObj.getString(key).trim()

            try {
                value.toInt()
            } catch (e: NumberFormatException) {
                intDefault
            }
        } else {
            intDefault
        }
    }

    fun getLong(jsonObj: JSONObject, key: String, longDefault: Long = -1): Long {
        return if (jsonObj.has(key) && !jsonObj.isNull(key)) {
            val value = jsonObj.getString(key).trim()

            try {
                value.toLong()
            } catch (e: NumberFormatException) {
                longDefault
            }
        } else {
            longDefault
        }
    }

    fun getFloat(jsonObj: JSONObject, key: String, floatDefault: Float = -1f): Float {
        return if (jsonObj.has(key) && !jsonObj.isNull(key)) {
            val value = jsonObj.getString(key).trim()

            try {
                value.toFloat()
            } catch (e: NumberFormatException) {
                floatDefault
            }
        } else {
            floatDefault
        }
    }

    fun getDouble(jsonObj: JSONObject, key: String, doubleDefault: Double = -1.0): Double {
        return if (jsonObj.has(key) && !jsonObj.isNull(key)) {
            val value = jsonObj.getString(key).trim()

            try {
                value.toDouble()
            } catch (e: NumberFormatException) {
                doubleDefault
            }
        } else {
            doubleDefault
        }
    }

    fun getJsonObject(jsonObject: JSONObject, key: String): JSONObject? {
        return if (jsonObject.has(key) && !jsonObject.isNull(key)) {
            try {
                jsonObject.getJSONObject(key)
            } catch (e: JSONException) {
                null
            }
        } else {
            null
        }
    }

    fun getJSONArray(jsonObject: JSONObject, key: String): JSONArray? {
        return if (jsonObject.has(key) && !jsonObject.isNull(key)) {
            try {
                jsonObject.getJSONArray(key)
            } catch (e: JSONException) {
                null
            }
        } else {
            null
        }
    }
    // endregion Base Function

    fun getKubitMarketData(jsonArray: JSONArray): KubitMarketData {
        val ret = KubitMarketData()

        if (jsonArray.length() == 0)
            return ret

        for (idx in 0 until jsonArray.length()) {
            if (!jsonArray.isNull(idx)) {
                val obj = jsonArray.getJSONObject(idx)

                if (obj != null) {
                    val market = getString(obj, KEY_MARKET)
                    val marketCode = market.split('-').ifEmpty { listOf("") }[0]
                    val nameKor = getString(obj, KEY_KOR_NAME)
                    val nameEng = getString(obj, KEY_ENG_NAME)
                    val marketWarning = (getString(obj, KEY_MARKET_WARNING) == "CAUTION")

                    ret.addCoin(
                        KubitCoinInfoData(
                            market,
                            marketCode,
                            nameKor,
                            nameEng,
                            marketWarning
                        )
                    )
                }
            }
        }

        return ret
    }

    fun getCoinSnapshotDataList(
        jsonArray: JSONArray,
        coinInfoDataList: List<KubitCoinInfoData>
    ): List<CoinSnapshotData> {
        val ret = arrayListOf<CoinSnapshotData>()

        for (idx in 0 until jsonArray.length()) {
            if (!jsonArray.isNull(idx)) {
                val obj = jsonArray.getJSONObject(idx)

                if (obj != null) {
                    val market = getString(obj, KEY_MARKET)
                    val marketCode = coinInfoDataList[idx].marketCode
                    val nameKor = coinInfoDataList[idx].nameKor
                    val nameEng = coinInfoDataList[idx].nameEng
                    val tradeDate = getString(obj, KEY_TRADE_DATE)
                    val tradeTime = getString(obj, KEY_TRADE_TIME)
                    val tradeDateKST = getString(obj, KEY_TRADE_DATE_KST)
                    val tradeTimeKST = getString(obj, KEY_TRADE_TIME_KST)
                    val tradeTimestamp = getLong(obj, KEY_TRADE_TIMESTAMP)
                    val openingPrice = getDouble(obj, KEY_OPENING_PRICE)
                    val highPrice = getDouble(obj, KEY_HIGH_PRICE)
                    val lowPrice = getDouble(obj, KEY_LOW_PRICE)
                    val tradePrice = getDouble(obj, KEY_TRADE_PRICE)
                    val prevClosingPrice = getDouble(obj, KEY_PREV_CLOSING_PRICE)
                    val change = when (getString(obj, KEY_CHANGE)) {
                        "EVEN" -> PriceChangeType.EVEN
                        "RISE" -> PriceChangeType.RISE
                        "FALL" -> PriceChangeType.FALL
                        else -> PriceChangeType.EVEN
                    }
                    val changePrice = getDouble(obj, KEY_CHANGE_PRICE)
                    val changeRate = getDouble(obj, KEY_CHANGE_RATE)
                    val signedChangePrice = getDouble(obj, KEY_SIGNED_CHANGE_PRICE)
                    val signedChangeRate = getDouble(obj, KEY_SIGNED_CHANGE_RATE)
                    val tradeVolume = getDouble(obj, KEY_TRADE_VOLUME)
                    val accTradePrice = getDouble(obj, KEY_ACC_TRADE_PRICE)
                    val accTradePrice24H = getDouble(obj, KEY_ACC_TRADE_PRICE_24H)
                    val accTradeVolume = getDouble(obj, KEY_ACC_TRADE_VOLUME)
                    val accTradeVolume24H = getDouble(obj, KEY_ACC_TRADE_VOLUME_24H)
                    val highest52WeekPrice = getDouble(obj, KEY_HIGHEST_52_WEEK_PRICE)
                    val highest52WeekDate = getString(obj, KEY_HIGHEST_52_WEEK_DATE)
                    val lowest52WeekPrice = getDouble(obj, KEY_LOWEST_52_WEEK_PRICE)
                    val lowest52WeekDate = getString(obj, KEY_LOWEST_52_WEEK_DATE)
                    val timestamp = getLong(obj, KEY_TIMESTAMP)

                    if (market.isNotEmpty()) {
                        ret.add(
                            CoinSnapshotData(
                                market,
                                marketCode,
                                nameKor,
                                nameEng,
                                tradeDate,
                                tradeTime,
                                tradeDateKST,
                                tradeTimeKST,
                                tradeTimestamp,
                                openingPrice,
                                highPrice,
                                lowPrice,
                                tradePrice,
                                prevClosingPrice,
                                change,
                                changePrice,
                                changeRate,
                                signedChangePrice,
                                signedChangeRate,
                                tradeVolume,
                                accTradePrice,
                                accTradePrice24H,
                                accTradeVolume,
                                accTradeVolume24H,
                                highest52WeekPrice,
                                highest52WeekDate,
                                lowest52WeekPrice,
                                lowest52WeekDate,
                                timestamp
                            )
                        )
                    }
                }
            }
        }

        return ret
    }

    companion object {
        private const val TAG: String = "JsonParserUtil"

        private const val KEY_MARKET: String = "market"
        private const val KEY_KOR_NAME: String = "korean_name"
        private const val KEY_ENG_NAME: String = "english_name"
        private const val KEY_MARKET_WARNING: String = "market_warning"

        private const val KEY_TRADE_DATE: String = "trade_date"
        private const val KEY_TRADE_TIME: String = "trade_time"
        private const val KEY_TRADE_DATE_KST: String = "trade_date_kst"
        private const val KEY_TRADE_TIME_KST: String = "trade_time_kst"
        private const val KEY_TRADE_TIMESTAMP: String = "trade_timestamp"
        private const val KEY_OPENING_PRICE: String = "opening_price"
        private const val KEY_HIGH_PRICE: String = "high_price"
        private const val KEY_LOW_PRICE: String = "low_price"
        private const val KEY_TRADE_PRICE: String = "trade_price"
        private const val KEY_PREV_CLOSING_PRICE: String = "prev_closing_price"
        private const val KEY_CHANGE: String = "change"
        private const val KEY_CHANGE_PRICE: String = "change_price"
        private const val KEY_CHANGE_RATE: String = "change_rate"
        private const val KEY_SIGNED_CHANGE_PRICE: String = "signed_change_price"
        private const val KEY_SIGNED_CHANGE_RATE: String = "signed_change_rate"
        private const val KEY_TRADE_VOLUME: String = "trade_volume"
        private const val KEY_ACC_TRADE_PRICE: String = "acc_trade_price"
        private const val KEY_ACC_TRADE_PRICE_24H: String = "acc_trade_price_24h"
        private const val KEY_ACC_TRADE_VOLUME: String = "acc_trade_volume"
        private const val KEY_ACC_TRADE_VOLUME_24H: String = "acc_trade_volume_24h"
        private const val KEY_HIGHEST_52_WEEK_PRICE: String = "highest_52_week_price"
        private const val KEY_HIGHEST_52_WEEK_DATE: String = "highest_52_week_date"
        private const val KEY_LOWEST_52_WEEK_PRICE: String = "lowest_52_week_price"
        private const val KEY_LOWEST_52_WEEK_DATE: String = "lowest_52_week_date"
        private const val KEY_TIMESTAMP: String = "timestamp"
    }

}