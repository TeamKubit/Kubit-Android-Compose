package com.kubit.android.ui.util

import androidx.compose.ui.graphics.Color
import com.kubit.android.data.model.coin.PriceChangeType
import com.kubit.android.ui.theme.CoinBlueDark
import com.kubit.android.ui.theme.CoinBlueLight
import com.kubit.android.ui.theme.CoinRedDark
import com.kubit.android.ui.theme.CoinRedLight
import com.kubit.android.ui.theme.TextDark
import com.kubit.android.ui.theme.TextLight

object CommonUtil {

    /**
     * 코인 가격과 관련된 데이터 텍스트 색상을 반환하는 함수
     *
     * @param priceChangeType   가격 변화 타입
     * @param isDarkTheme       다크모드 설정 여부
     */
    fun getPriceColor(
        priceChangeType: PriceChangeType,
        isDarkTheme: Boolean
    ): Color {
        return if (isDarkTheme) {
            when (priceChangeType) {
                PriceChangeType.EVEN -> TextDark
                PriceChangeType.RISE -> CoinRedDark
                PriceChangeType.FALL -> CoinBlueDark
            }
        } else {
            when (priceChangeType) {
                PriceChangeType.EVEN -> TextLight
                PriceChangeType.RISE -> CoinRedLight
                PriceChangeType.FALL -> CoinBlueLight
            }
        }
    }

}