package com.example.composeapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.R
import com.example.composeapp.navigation.Screen
import com.example.composeapp.ui.theme.ComposeAppTheme
import org.w3c.dom.Text

@Composable
fun TopBarHomeScreen(
    name: String,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp)
            .statusBarsPadding()
    ){
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.Profile.route)
                        }
                        .clip(RoundedCornerShape(180.dp))
                        .width(50.dp)
                        .height(50.dp),
                    painter = painterResource(id = R.drawable.cat),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Profile Pictore"
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(

                ) {
                    Text(
                        text = "Welcome",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        fontStyle = FontStyle.Italic,
                        lineHeight = 22.sp,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                    Text(
                        text =  name,
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight(500),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TopBarPrev() {
    val navController = rememberNavController()
    ComposeAppTheme {
        TopBarHomeScreen(
            name = "Daffa",
            navController = navController
        )
    }

}