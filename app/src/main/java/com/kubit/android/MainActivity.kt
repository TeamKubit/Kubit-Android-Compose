package com.kubit.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kubit.android.data.model.navigation.BottomNavItem
import com.kubit.android.data.model.navigation.MainNavItem
import com.kubit.android.ui.screen.coinlist.CoinListScreen
import com.kubit.android.ui.screen.exchange.ExchangeScreen
import com.kubit.android.ui.screen.intro.IntroScreen
import com.kubit.android.ui.screen.investment.InvestmentScreen
import com.kubit.android.ui.screen.navigation.BottomNavScreen
import com.kubit.android.ui.screen.profile.ProfileScreen
import com.kubit.android.ui.theme.KubitTheme

class MainActivity : ComponentActivity() {

    private val model: MainViewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModel.Factory(application)
        )[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }

        setObserver()
        init()
    }

    private fun setObserver() {
        model.progressFlag.observe(this, Observer { progressFlag ->
            if (progressFlag) {
                showProgress()
            } else {
                dismissProgress()
            }
        })

        model.apiFailMsg.observe(this, Observer { failMsg ->
            if (failMsg.isNotEmpty()) {
                model.setProgressFlag(false)
                showToastMsg(failMsg)
            }
        })

        model.exceptionData.observe(this, Observer { exception ->
            model.setProgressFlag(false)
            showErrorMsg()
        })
    }

    private fun init() {

    }

    @Composable
    private fun MainScreen() {
        val navController = rememberNavController()

        Box {
            NavHost(
                navController = navController,
                startDestination = MainNavItem.IntroScreen.screenRouter
            ) {
                composable(MainNavItem.IntroScreen.screenRouter) {
                    IntroScreen()
                }
                composable(MainNavItem.MainScreen.screenRouter) {
                    BottomNavScreen()
                }
            }
        }
    }

    // region Util Function
    private fun showProgress(pMsg: String = "") {

    }

    private fun dismissProgress() {

    }

    private fun showToastMsg(pMsg: String) {
        Toast.makeText(this, pMsg, Toast.LENGTH_SHORT).show()
    }

    private fun showErrorMsg() {
        Toast.makeText(
            this,
            resources.getText(R.string.toast_msg_error_001),
            Toast.LENGTH_SHORT
        ).show()
    }
    // endregion Util Function

    companion object {
        private const val TAG: String = "MainActivity"
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KubitTheme {
    }
}