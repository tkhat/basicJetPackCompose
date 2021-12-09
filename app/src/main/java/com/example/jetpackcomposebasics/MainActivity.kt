package com.example.jetpackcomposebasics


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.ui.theme.JetpackComposebasicsTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposebasicsTheme {
                // A surface container using the 'background' color from the theme
                    Myapp()

            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinuedClicked: () -> Unit){

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement =  Arrangement.Center,
            horizontalAlignment =  Alignment.CenterHorizontally
        ) {
            Button(onClick =  onContinuedClicked , modifier = Modifier.padding(vertical = 24.dp)) {
                Text(text = "Continue")
            }
            Text(text = "Welcome to the Basics Codelab!")
        }
    }
}

@Composable
private fun Myapp(){
        var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true)}
    if (shouldShowOnBoarding){

        OnboardingScreen(onContinuedClicked = {shouldShowOnBoarding = false })

    } else Greetings()
}

@Composable
fun Greetings(names: List<String> = List(100) {"$it"}){

    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {


    var expanded by remember { mutableStateOf(false) }





    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ){

        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec =
                    spring(
                        dampingRatio = Spring
                            .DampingRatioMediumBouncy,
                        stiffness = Spring.
                        StiffnessLow))){
            Column(
                modifier = Modifier
                    .weight(1f)

            ) {
                Text(text = "Hello, ")
                Text(text = "$name!", style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
                if(expanded) Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam quis bibendum ex, ac lacinia magna. Duis non ante velit. Nam ut placerat velit. Aenean et dolor ac enim efficitur accumsan nec at odio. Donec sed finibus diam. Praesent varius convallis ligula quis faucibus. Nam aliquam in nibh posuere blandit. Fusce rutrum pellentesque elementum.")
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore, contentDescription = if (expanded) "Show less" else "Show more" )
                
            }
            
            

        }

    }
}

@Preview(showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_YES, name = "DefaultPreviewDark")
@Composable
fun DefaultPreview() {
    JetpackComposebasicsTheme {
        Myapp()
    }
}