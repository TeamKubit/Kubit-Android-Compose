package com.kubit.android.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kubit.android.KubitViewModel
import com.kubit.android.R
import com.kubit.android.data.model.coin.CoinSnapshotData
import com.kubit.android.data.util.DLog
import com.kubit.android.ui.component.CoinListItem
import com.kubit.android.ui.theme.KubitTheme
import com.kubit.android.ui.theme.PrimaryDark
import com.kubit.android.ui.theme.TextDark
import com.kubit.android.ui.util.ConvertUtil

const val TAG: String = "CoinListScreen"

@Composable
fun CoinListScreen(
    kubitViewModel: KubitViewModel,
    onCoinItemClick: (CoinSnapshotData) -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    val coinSnapshotDataList = kubitViewModel.coinSnapshotDataList.collectAsState()

    KubitTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isDarkTheme) PrimaryDark else Color.White)
        ) {
            Text(
                text = stringResource(id = R.string.title_coinList),
                color = if (isDarkTheme) TextDark else Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = ConvertUtil.dp2sp(dp = 22.dp),
                modifier = Modifier.padding(start = 25.dp, top = 20.dp, end = 25.dp, bottom = 5.dp)
            )

            LazyColumn {
                for (snapshot in coinSnapshotDataList.value) {
                    item {
                        CoinListItem(
                            coinSnapshotData = snapshot,
                            onClickListener = { coinSnapshotData ->
                                DLog.d(TAG, "clicked item = $coinSnapshotData")
                                onCoinItemClick(coinSnapshotData)
                            }
                        )
                    }
                }
            }
        }
    }
}