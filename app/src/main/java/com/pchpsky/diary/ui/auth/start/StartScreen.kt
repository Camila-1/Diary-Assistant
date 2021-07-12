package com.pchpsky.diary.ui.auth.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.R
import com.pchpsky.diary.navigation.AuthRoute
import com.pchpsky.diary.ui.theme.green
import com.pchpsky.diary.ui.theme.lightGreen

@Composable
fun StartScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.width(250.dp)
        ) {
            logoGroup()
            loginButton(
                text = "Login",
                color = lightGreen,
                onClick = { navController.navigate(AuthRoute.LOGIN.route) })
            loginButton(
                text = "Signup",
                color = green,
                onClick = { navController.navigate(AuthRoute.SIGNUP.route) })
        }
    }

}

@Composable
fun loginButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        enabled = true,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            disabledBackgroundColor = color
        ),
        onClick = {
            onClick.invoke()
                  },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .height(40.dp),
        shape = RoundedCornerShape(50)
    ) {
        Text(text)
    }
}

@Composable
fun logoGroup() {
    val image = painterResource(R.drawable.ic_logo)
    Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(bottom = 40.dp)) {
        Image(painter = image, contentDescription = null, modifier = Modifier.width(83.dp))
        Text(
            text = "Diary",
            color = Color.White,
            fontSize = 50.sp,
            modifier = Modifier.padding(bottom = 10.dp, start = 15.dp)
        )
    }
}

@Preview
@Composable
fun defaultPreview() {
    StartScreen(rememberNavController())
}
