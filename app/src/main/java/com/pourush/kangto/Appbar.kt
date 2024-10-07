package com.pourush.kangto

import android.app.Activity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AppBarView(
    title: String,
    onBackNavClicked:()->Unit={}
)
{
    val activity = (LocalContext.current as? Activity)
    val navigationIcon: (@Composable () -> Unit)?=
        {
            if(!title.contains("Home"))
            {
                IconButton(onClick = {onBackNavClicked()})
                {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = "Back navigation button")
                }
            }
            else
            {
                null
            }

        }

    androidx.compose.material.TopAppBar(title = { Text(text = title, color = Color.White,
        modifier = Modifier
            .padding(4.dp)
            .heightIn(max = 24.dp),style= androidx.compose.material3.MaterialTheme.typography.titleMedium)
    },
        elevation=3.dp,
        backgroundColor=colorResource(id=R.color.forest_essence),
        navigationIcon = navigationIcon,
        actions =
        {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    activity?.finish()
                })
                {
                    Icon(
                        painter = painterResource(id = R.drawable.exit_app),
                        tint = Color.White,
                        contentDescription = "Exit"
                    )

                }
            }
        }

    )



}