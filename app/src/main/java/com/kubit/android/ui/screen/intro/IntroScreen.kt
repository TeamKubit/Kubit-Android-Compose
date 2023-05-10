package com.kubit.android.ui.screen.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kubit.android.R
import com.kubit.android.ui.theme.KubitTheme
import com.kubit.android.ui.theme.TextDark
import com.kubit.android.ui.util.ConvertUtil

@Composable
fun IntroScreen(
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    KubitTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                color = if (isDarkTheme) TextDark else Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = ConvertUtil.dp2sp(dp = 48.dp)
            )
            Text(
                text = stringResource(id = R.string.app_description),
                color = if (isDarkTheme) TextDark else Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = ConvertUtil.dp2sp(dp = 18.dp)
            )
        }
    }
}