package com.rellidev.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rellidev.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Lemonade()
                }
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)
)
{
    var data by remember {
        mutableStateOf<Array<Int>>(
            arrayOf(0, R.drawable.lemon_tree, R.string.lemon_tree_desc, R.string.lemon_tree_text))
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { data = navigate(data[0]) },
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = androidx.compose.ui.graphics.Color.Cyan)
        ) {
            Image(
            painter = painterResource(id = data[1]),
            contentDescription = stringResource(id = data[2]),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(32.dp)
            )
        }

        Text(
            text = stringResource(id = data[3]),
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 18.sp
        )
    }
}

val squeezesToMakeLemonade = (1..3).random()
var squeezes = 0

fun makeLemonade(): Array<Int> {
    return if (squeezesToMakeLemonade == squeezes) {
        squeezes = 0
        arrayOf<Int>(2, R.drawable.lemon_drink, R.string.glass_lemon_desc, R.string.tap_lemon_text)
    } else {
        squeezes++
        arrayOf<Int>(1, R.drawable.lemon_squeeze, R.string.lemon_desc, R.string.squeeze_lemon_text)
    }
}

fun navigate(clicks: Int): Array<Int> {
   return  when(clicks) {
        0 -> arrayOf<Int>(1, R.drawable.lemon_squeeze, R.string.lemon_desc, R.string.squeeze_lemon_text)
        1 -> makeLemonade()
        2 -> arrayOf<Int>(3, R.drawable.lemon_restart, R.string.empty_glass_desc, R.string.tap_glass_text)
        else -> arrayOf<Int>(0, R.drawable.lemon_tree, R.string.lemon_tree_desc, R.string.lemon_tree_text)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview()
{
    LemonadeTheme {
        Lemonade()
    }
}