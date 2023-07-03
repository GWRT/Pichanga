package com.example.pichanga

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pichanga.ui.theme.*

@Composable
fun WelcomeScreen(navController: NavController){

    val screeWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayGeneral_WelcomeScreen),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screeWidth)
                .padding(16.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = MaterialTheme.shapes.medium,
                    clip = true
                )

        ){
            Image(
                painter = painterResource(id = R.drawable.gato),
                contentDescription = "gato",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .blur(
                        radiusX = 1.dp,
                        radiusY = 2.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(1.dp))
                    )
            )
        }
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = "Bienvenido a\nLIGHT LIVES",
            color = titleText_WelcomeScreen,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 15.dp)
        )
        Text(
            text = "Prepárate para experimentar\nel futuro de la iluminación inteligente.",
            color = subTitleText_WelcomeScreen,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(50.dp))
        Button(
            onClick = {
                navController.navigate("calendar")
            },
            modifier = Modifier
                .width(screeWidth / 2),
            colors = ButtonDefaults.buttonColors(buttonColor_WelcomeScreen),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = "Get started",
                color = textButtonColor_WelcomeScreen,
                style = MaterialTheme.typography.h6
            )
        }
    }

}