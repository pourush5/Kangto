package com.pourush.kangto.homescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pourush.kangto.AppBarView
import com.pourush.kangto.R
import com.pourush.kangto.Screen

@Composable
fun HomeScreen(navController: NavController)
{
    val scaffoldState= rememberScaffoldState()
    Scaffold(
        scaffoldState=scaffoldState,
        topBar = { AppBarView(title = "Home")
        }
    )
    {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(it))
    {
        Spacer(modifier = Modifier.weight(2f))
        Image(painter= painterResource(id = R.drawable.kangto_appicon),
            contentDescription = "Kangto_logo",modifier=Modifier.size(200.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Text("Inspired by the Land of Dawn-Lit Mountains",style= MaterialTheme.typography.titleMedium,fontFamily = FontFamily.Cursive)
        Spacer(modifier = Modifier.height(10.dp))
        Text("Arunachal Pradesh",style= MaterialTheme.typography.titleLarge,fontFamily = FontFamily.Cursive)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {navController.navigate(Screen.SOSButtonScreen.route)},
            modifier= Modifier
                .width(200.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = colorResource(id = R.color.forest_essence)
            ),border = BorderStroke(3.dp, colorResource(id = R.color.light_green))
            )
        {
            Text(text = "Send SOS!", fontSize=20.sp)
        }
        Spacer(modifier=Modifier.height(30.dp))
        Button(onClick = { navController.navigate(Screen.HollongAppScreen.route)},
            modifier= Modifier
                .width(200.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = colorResource(id = R.color.forest_essence)
            ),border = BorderStroke(3.dp, colorResource(id = R.color.light_green))
            )
        {
            Text(text = "Hollong App", fontSize=20.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.a2z_nobg),
                contentDescription = "Bottom_A2Z_graphic",
                modifier= Modifier
                    .size(70.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(text = "An initiative by Pourush Pandey", fontFamily = FontFamily.Cursive, modifier=Modifier.padding(16.dp).align(Alignment.CenterVertically))
            Image(
                painter = painterResource(id = R.drawable.logo_pourush),
                contentDescription = "Pourush_logo_graphic",
                modifier= Modifier
                    .size(100.dp)
                    .align(Alignment.CenterVertically)
            )
        }

    }
}
}