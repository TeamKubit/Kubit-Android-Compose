package com.kubit.android.data.model.navigation

import com.kubit.android.R


sealed class BottomNavItem(val titleResId: Int, val iconResId: Int, val screenRoute: String) {
    object CoinListTab :
        BottomNavItem(
            R.string.bottom_nav_coinList,
            R.drawable.icon_tab_home,
            BottomNavRouter.HOME
        )

    object InvestmentTab :
        BottomNavItem(
            R.string.bottom_nav_investment,
            R.drawable.icon_tab_investment,
            BottomNavRouter.INVESTMENT
        )

    object ExchangeTab :
        BottomNavItem(
            R.string.bottom_nav_exchange,
            R.drawable.icon_tab_exchange,
            BottomNavRouter.EXCHANGE
        )

    object ProfileTab :
        BottomNavItem(
            R.string.bottom_nav_profile,
            R.drawable.icon_tab_profile,
            BottomNavRouter.PROFILE
        )
}
