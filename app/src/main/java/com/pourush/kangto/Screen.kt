package com.pourush.kangto

//Creating a Screen sealed class to facilitate navigation through route.
sealed class Screen (val route:String)
{
    object SOSButtonScreen:Screen("sos_screen")
    object ForestFireTipScreen:Screen("forest_tip_screen")
    object HollongAppScreen:Screen("hollong_app")

    object HomeScreen:Screen("home_screen")

}