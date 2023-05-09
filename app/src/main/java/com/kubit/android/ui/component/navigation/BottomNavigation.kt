package com.kubit.android.ui.component.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kubit.android.data.model.navigation.BottomNavItem
import com.kubit.android.ui.screen.coinlist.CoinListScreen
import com.kubit.android.ui.screen.exchange.ExchangeScreen
import com.kubit.android.ui.screen.investment.InvestmentScreen
import com.kubit.android.ui.screen.profile.ProfileScreen
import com.kubit.android.ui.theme.PrimaryLight
import com.kubit.android.ui.util.ConvertUtil

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.CoinListTab.screenRoute
    ) {
        composable(BottomNavItem.CoinListTab.screenRoute) {
            CoinListScreen()
        }
        composable(BottomNavItem.InvestmentTab.screenRoute) {
            InvestmentScreen()
        }
        composable(BottomNavItem.ExchangeTab.screenRoute) {
            ExchangeScreen()
        }
        composable(BottomNavItem.ProfileTab.screenRoute) {
            ProfileScreen()
        }
    }
}


@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf<BottomNavItem>(
        BottomNavItem.CoinListTab,
        BottomNavItem.InvestmentTab,
        BottomNavItem.ExchangeTab,
        BottomNavItem.ProfileTab
    )

    androidx.compose.material.BottomNavigation(
        backgroundColor = PrimaryLight,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = stringResource(id = item.titleResId),
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                },
                label = {
                    Text(
                        stringResource(id = item.titleResId),
                        fontSize = ConvertUtil.dp2sp(dp = 12.dp),
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White,
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}