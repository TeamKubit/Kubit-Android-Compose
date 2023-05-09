package com.kubit.android.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

object ConvertUtil {

    @Composable
    fun dp2sp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

}