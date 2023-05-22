package com.kubit.android.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import java.math.RoundingMode
import java.text.DecimalFormat

object ConvertUtil {

    private val priceFormatterOver100 = DecimalFormat("###,###,###").apply {
        roundingMode = RoundingMode.DOWN
    }
    private val priceFormatterUnder100 = DecimalFormat("##.00######").apply {
        roundingMode = RoundingMode.DOWN
    }

    private val changeRateFormatter = DecimalFormat("#0.00").apply {
        roundingMode = RoundingMode.DOWN
    }

    @Composable
    fun dp2sp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

    /**
     * double 타입의 가격을 문자열로 변환하는 함수
     *
     * @param pTradePrice   가격
     */
    fun tradePrice2string(pTradePrice: Double): String {
        return if (pTradePrice < 100) {
            priceFormatterUnder100.format(pTradePrice)
        } else {
            priceFormatterOver100.format(pTradePrice)
        }
    }

    /**
     * double 타입의 부호가 있는 변화율을 문자열로 변환하는 함수
     *
     * @param pSignedChangeRate 부호가 있는 변화율
     */
    fun changeRate2string(pSignedChangeRate: Double): String {
        return "${changeRateFormatter.format(pSignedChangeRate * 100)}%"
    }

    /**
     * 24시간 누적 거래대금을 문자열로 변환하는 함수
     *
     * @param pAccTradePrice24H 24시간 누적 거래대금
     */
    fun accTradePrice24H2string(pAccTradePrice24H: Double): String {
        val volumeUnitMillion = pAccTradePrice24H.div(1000000).toInt()
        return if (volumeUnitMillion < 100) {
            "${priceFormatterUnder100.format(volumeUnitMillion)}백만"
        } else {
            "${priceFormatterOver100.format(volumeUnitMillion)}백만"
        }
    }

}