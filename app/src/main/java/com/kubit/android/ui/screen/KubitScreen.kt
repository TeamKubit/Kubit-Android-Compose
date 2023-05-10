package com.kubit.android.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kubit.android.KubitViewModel
import com.kubit.android.ui.screen.intro.IntroScreen
import com.kubit.android.ui.screen.main.MainScreen
import com.kubit.android.ui.theme.KubitTheme

enum class KubitScreen {
    Intro,
    Main,
    Transaction,
    Login
}

@Composable
fun KubitApp(
    kubitViewModel: KubitViewModel,
    navController: NavHostController = rememberNavController()
) {
    KubitTheme {
        NavHost(
            navController = navController,
            startDestination = KubitScreen.Intro.name
        ) {
            composable(route = KubitScreen.Intro.name) {
                IntroScreen()
            }
            composable(route = KubitScreen.Main.name) {
                MainScreen(
                    kubitViewModel = kubitViewModel,
                    appNavController = navController
                )
            }
            composable(route = KubitScreen.Transaction.name) {

            }
            composable(route = KubitScreen.Login.name) {

            }
        }

        val isLoading = kubitViewModel.isLoading.collectAsState()
        if (!isLoading.value) {
            navController.navigate(route = KubitScreen.Main.name) {
                popUpTo(route = KubitScreen.Intro.name) {
                    inclusive = true
                }
            }
        }
    }
}