package com.mouhsinbourqaiba.sample.compose_example_kotlin

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.ui.core.Text
import androidx.compose.*
import androidx.ui.core.CurrentTextStyleProvider
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.ExpandedHeight
import androidx.ui.layout.Spacing
import androidx.ui.material.Button
import androidx.ui.material.Divider
import androidx.ui.material.MaterialColors
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview


class AppState(val counterState: CounterState = CounterState())

@Model
class CounterState(var count: Int = 0)

class MainActivity : Activity() {

    val customGreen = Color(0xFF1EB980.toInt())
    val customSurface = Color(0xFF26282F.toInt())
    private val themeColors = MaterialColors(
        primary = customGreen,
        surface = customSurface,
        onSurface = Color.Black
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                    MyScreenContent()
            }
        }
    }


    @Composable
    fun CustomTheme(children: @Composable() () -> Unit) {
        MaterialTheme(colors = themeColors) {
            val textStyle = TextStyle(color = Color.Red)
            CurrentTextStyleProvider(value = textStyle) {
                children()
            }
        }
    }

    @Composable
    fun MyApp(child: @Composable() () -> Unit) {
        MaterialTheme {
            Surface(color = Color.Yellow) {
                child()
            }
        }
    }

    @Composable
    fun MyScreenContent(appState: AppState = AppState()) {
        Column(modifier = ExpandedHeight, crossAxisAlignment = CrossAxisAlignment.Center) {
            Column(modifier = Flexible(1f), crossAxisAlignment = CrossAxisAlignment.Center) {
                CustomTheme{
                    Greeting("Android")
                }
                Divider(color = Color.Black)

                CustomTheme{
                    Greeting("there")
                }
            }
            Counter(appState.counterState)
        }
    }

    @Composable
    fun Counter(state: CounterState) {
        Button(text = "I've been clicked ${state.count} times",
            onClick = {
                state.count++
            }
        )
    }

    @Composable
    fun Greeting(name: String) {
        Text(
            text = "Hello $name!",
            modifier = Spacing(24.dp)
        )
    }

    @Preview("MyScreen preview")
    @Composable
    fun DefaultPreview() {
        MyApp {
            MyScreenContent()
        }
    }
}
