package com.pourush.kangto.hollong

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForestFireSafetyTipsScreen() {
    val tips = listOf(
        "1. Try to maintain FOREST BLOCKS to prevent day litter from forests during summer season.",
        "2. Try to put the fire out by digging or circle around it by water, if not possible to call a Fire bridge.",
        "3. Move farm animals & movable goods to safer places.",
        "4. During fire listen regularly to radio for advance information & obey the instructions cum advice.",
        "5. Teach the causes and harm of fire to your family and others. Make people aware about forest fire safety.",
        "6. Do not be scared when a sudden fire occur in the forest, be calm & encourage others & community to overcome the problem patiently.",
        "7. Do apply seasonal mitigation measures i.e., fuel reduction etc.",
        "8. Don’t throw smoldering cigarette butts or bidi in the forests.",
        "9. Don’t leave the burning wood sticks in or near the forest.",
        "10. Don’t enter the forest during the fire.",
        "11. Discourage community to use Slash & Burn method."
    )
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    val annotatedText = buildAnnotatedString {
        append("Source: ")
        pushStringAnnotation(tag = "URL", annotation = "https://sdma-arunachal.in/forest-fire/")
        withStyle(style = SpanStyle(color = Color.Blue)) {
            append("https://sdma-arunachal.in/forest-fire/")
        }
        pop()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Forest Fire Safety Tips", fontSize = 24.sp, fontWeight = FontWeight.Bold) },
                backgroundColor = Color(0xFF2E7D32),
                contentColor = Color.White
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(listOf(Color(0xFFB2FF59), Color(0xFF76FF03))))
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item{
                    Text(
                        text = annotatedText,
                        modifier = Modifier.clickable {
                            uriHandler.openUri("https://sdma-arunachal.in/forest-fire/")  // Opens the URL when clicked
                        },
                        fontSize = 18.sp
                    )
                    Text("State Disaster Management Authority Government of Arunachal Pradesh ",style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center),modifier=Modifier.padding(10.dp).align(Alignment.Center))
                }
                item {
                    Text(
                        "Forest Fire Safety Tips",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(10.dp)
                    )
                }
                items(tips) { tip ->
                    TipCard(tip = tip)
                }
            }
        }
    }
}

@Composable
fun TipCard(tip: String) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        elevation=10.dp
    ) {
        Text(
            text = tip,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                color = Color.Black
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}