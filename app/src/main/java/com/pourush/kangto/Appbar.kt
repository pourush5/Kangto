package com.pourush.kangto

import android.app.Activity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class) // Required for Material 3 TopAppBar
@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {}
) {
    val activity = (LocalContext.current as? Activity)
    val navigationIconComposable: @Composable () -> Unit =
        if (!title.contains("Home")) {
            {
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = "Back navigation button"
                    )
                }
            }
        } else {
            {} // Provide an empty composable if no icon is needed
        }

    TopAppBar( // Use androidx.compose.material3.TopAppBar
        title = {
            Text(
                text = title,
                color = Color.White,
                modifier = Modifier
                    .padding(4.dp)
                    .heightIn(max = 24.dp),
                style = MaterialTheme.typography.titleMedium
            )
        },
        colors = TopAppBarDefaults.topAppBarColors( // Set colors here
            containerColor = colorResource(id = R.color.forest_essence),
            titleContentColor = Color.White, // Ensure title text color is white
            navigationIconContentColor = Color.White, // Ensure nav icon color is white
            actionIconContentColor = Color.White // Ensure action icon color is white
        ),
        navigationIcon = navigationIconComposable,
        actions = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    activity?.finish()
                }) {
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