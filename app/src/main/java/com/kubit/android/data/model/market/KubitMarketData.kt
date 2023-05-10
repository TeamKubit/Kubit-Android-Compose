package com.kubit.android.data.model.market

import com.kubit.android.data.model.coin.KubitCoinInfoData

class KubitMarketData(
    private val kubitCoinMap: HashMap<String, ArrayList<KubitCoinInfoData>> = hashMapOf()
) : java.io.Serializable {

    /**
     * 마켓 코드에 대응되는 마켓 코드 파라미터를 저장하는 HashMap
     */
    private val marketCode: HashMap<String, String> = hashMapOf()

    val isValid get() = kubitCoinMap.size > 0

    /**
     * Kubit 코인 데이터를 추가하는 함수
     *
     * @param pCoinInfoData     추가할 코인 데이터
     */
    fun addCoin(pCoinInfoData: KubitCoinInfoData) {
        // 이전에 추가한 적이 있는 마켓의 코인인 경우
        if (kubitCoinMap.containsKey(pCoinInfoData.marketCode)) {
            kubitCoinMap[pCoinInfoData.marketCode]?.add(pCoinInfoData)
        }
        // 처음 추가하는 마켓의 코인인 경우
        else {
            kubitCoinMap[pCoinInfoData.marketCode] = arrayListOf(pCoinInfoData)
        }
    }

    /**
     * 마켓 코드 파라미터를 반환하는 함수
     *
     * @param pMarketCode   마켓 코드
     *
     * @see KubitMarketCode
     */
    fun getMarketCode(pMarketCode: String): String {
        val sb = StringBuilder("")
        when (pMarketCode) {
            KubitMarketCode.KRW -> {
                val krwMarketList = kubitCoinMap[pMarketCode]
                if (krwMarketList != null) {
                    for (krwMarket in krwMarketList) {
                        if (sb.isNotEmpty()) sb.append(", ")
                        sb.append(krwMarket.market)
                    }
                }
            }
            KubitMarketCode.BTC -> {
                val btcMarketList = kubitCoinMap[pMarketCode]
                if (btcMarketList != null) {
                    for (btcMarket in btcMarketList) {
                        if (sb.isNotEmpty()) sb.append(", ")
                        sb.append(btcMarket.market)
                    }
                }
            }
            else -> {
                throw UnsupportedOperationException("MarketCode $pMarketCode is not supported.\nPlease retry with other marketCode.")
            }
        }
        return sb.toString()
    }

    override fun toString(): String {
        return "KubitMarketData{" +
                "kubitCoinMap=$kubitCoinMap}"
    }

}