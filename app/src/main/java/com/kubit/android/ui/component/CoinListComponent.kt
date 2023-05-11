package com.kubit.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.kubit.android.ui.theme.CandleDark
import com.kubit.android.ui.theme.CandleLight
import com.kubit.android.ui.theme.CoinBlueDark
import com.kubit.android.ui.theme.CoinBlueLight
import com.kubit.android.ui.theme.CoinRedDark
import com.kubit.android.ui.theme.CoinRedLight
import com.kubit.android.ui.theme.KubitTheme
import com.kubit.android.ui.theme.PrimaryDark
import com.kubit.android.ui.theme.TextDark
import com.kubit.android.ui.theme.TextGray
import com.kubit.android.ui.theme.TextLight
import com.kubit.android.ui.util.ConvertUtil

@OptIn(ExperimentalUnitApi::class)
@Composable
fun CoinListItem(
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    KubitTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = if (isDarkTheme) PrimaryDark else Color.White),
            horizontalArrangement = Arrangement.End
        ) {

            Spacer(modifier = Modifier.width(10.dp))

            CoinChangeRateStick(isDarkTheme = isDarkTheme)

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.padding(top = 7.dp)) {
                Text(
                    text = "비트코인",
                    fontWeight = FontWeight.Normal,
                    fontSize = ConvertUtil.dp2sp(dp = 14.dp),
                    color = if (isDarkTheme) TextDark else TextLight,
                    textAlign = TextAlign.Left,
                    letterSpacing = TextUnit(-0.05f, TextUnitType.Sp),
                    overflow = TextOverflow.Clip
                )
                Text(
                    text = "BTC/KRW",
                    fontWeight = FontWeight.Normal,
                    fontSize = ConvertUtil.dp2sp(dp = 10.dp),
                    color = TextGray,
                    textAlign = TextAlign.Left,
                    letterSpacing = TextUnit(-0.05f, TextUnitType.Sp),
                    overflow = TextOverflow.Clip
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "36,912,000",
                fontWeight = FontWeight.Normal,
                fontSize = ConvertUtil.dp2sp(dp = 12.dp),
                color = if (isDarkTheme) TextDark else TextLight,
                textAlign = TextAlign.Right,
                letterSpacing = TextUnit(-0.05f, TextUnitType.Sp),
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .width(70.dp)
                    .padding(top = 7.dp, end = 5.dp)
            )

            Text(
                text = "-10.00%",
                fontWeight = FontWeight.Normal,
                fontSize = ConvertUtil.dp2sp(dp = 12.dp),
                color = if (isDarkTheme) TextDark else TextLight,
                textAlign = TextAlign.Right,
                letterSpacing = TextUnit(-0.05f, TextUnitType.Sp),
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .width(60.dp)
                    .padding(top = 7.dp, end = 5.dp)
            )

            Text(
                text = "146,069백만",
                fontWeight = FontWeight.Normal,
                fontSize = ConvertUtil.dp2sp(dp = 12.dp),
                color = if (isDarkTheme) TextDark else TextLight,
                textAlign = TextAlign.Right,
                letterSpacing = TextUnit(-0.05f, TextUnitType.Sp),
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .width(100.dp)
                    .padding(top = 7.dp, end = 10.dp)
            )
        }
    }
}

@Composable
fun CoinChangeRateStick(
    changeRate: Float = 0.5f,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .width(10.dp)
            .height(30.dp)
            .background(color = if (isDarkTheme) CandleDark else CandleLight),
        contentAlignment = Alignment.TopCenter
    ) {
        if (changeRate > 0f) {
            Spacer(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = if (isDarkTheme) CoinRedDark else CoinRedLight)
            )

            val h = (changeRate * 15).toInt()
            Spacer(
                modifier = Modifier
                    .padding(top = (15 - h).dp)
                    .width(1.dp)
                    .height(h.dp)
                    .background(color = if (isDarkTheme) CoinRedDark else CoinRedLight)
            )
        } else if (changeRate < 0f) {
            Spacer(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = if (isDarkTheme) CoinBlueDark else CoinBlueLight)
            )

            val h = (-changeRate * 15).toInt()
            Spacer(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .width(1.dp)
                    .height(h.dp)
                    .background(color = if (isDarkTheme) CoinBlueDark else CoinBlueLight)
            )
        } else {
            Spacer(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = if (isDarkTheme) Color.White else TextLight)
            )
        }
    }
}

@Preview
@Composable
fun CoinListComponentPreview() {
    CoinListItem()
}