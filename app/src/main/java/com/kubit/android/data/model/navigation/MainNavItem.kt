package com.kubit.android.data.model.navigation

sealed class MainNavItem(val screenRouter: String) {
    object IntroScreen : MainNavItem(MainNavRouter.INTRO)
    object MainScreen : MainNavItem(MainNavRouter.MAIN)
    object TransactionScreen : MainNavItem(MainNavRouter.TRANSACTION)
}