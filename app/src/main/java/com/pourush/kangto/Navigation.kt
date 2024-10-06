package com.pourush.kangto

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pourush.kangto.hollong.ForestFireSafetyTipsScreen
import com.pourush.kangto.hollong.HollongApp
import com.pourush.kangto.sosapp.SOSButtonScreen

@Composable
fun Navigation(navController: NavHostController =rememberNavController())
{
 // Retrieving the application context
 val application = LocalContext.current.applicationContext as Application

 // Creating the ViewModel using the factory
 val sosViewModel: SOSViewModel = viewModel(factory = SOSViewModelFactory(application))
 NavHost(navController = navController, startDestination = Screen.SOSButtonScreen.route)
 {
  composable(Screen.HollongAppScreen.route)
  {
   HollongApp(navController)
  }
  composable(Screen.ForestFireTipScreen.route)
  {
   ForestFireSafetyTipsScreen(navController)
  }

  composable(Screen.SOSButtonScreen.route)
  {
   SOSButtonScreen(sosViewModel= sosViewModel,navController)
  }
 }

}
