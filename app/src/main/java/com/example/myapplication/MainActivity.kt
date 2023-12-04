package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GuessAnimalScreen()
                }
            }
        }
    }
}

@Composable
fun GuessAnimalScreen() {
    Scaffold(
        topBar = { TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) }
        )
        },
        content = { padding -> ScreenContent(Modifier.padding(padding)) },
    )
}

@Composable
fun ScreenContent(modifier: Modifier) {
    Column(
        Modifier
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = stringResource(R.string.animal_question),
            style = MaterialTheme.typography.h6,
        )
        Image(
            painter = painterResource(id = R.drawable.giraffe),
            contentDescription = "", // decorative element
            modifier = Modifier
                .padding(all = 16.dp)
                .width(250.dp)
                .height(250.dp)
        )
        InputSegment()
    }
}

@Composable
fun InputSegment() {
    // Context is needed for displaying a Toast message.
    val context = LocalContext.current

    // Local variable to hold and save the value of the text entered by the user.
    var answerText by remember { mutableStateOf(String()) }
    Row(
    ) {
        OutlinedTextField(
            value = answerText,
            // Below line is used to add placeholder ("hint") for our text field.
            placeholder = { Text(text = stringResource(id = R.string.animal_question)) },
            onValueChange = {
                answerText = it
            },
            label = { Text(stringResource(R.string.answer_label)) }
        )
        Button(
            modifier = Modifier.padding(start = 24.dp, top = 12.dp),
            onClick = { verifyAnswer(context, answerText) }) {
            Icon(Icons.Filled.Send, "Process user input")
        }
    }
}
fun verifyAnswer(context: Context, answerText: String) {
    var toastText = "\"" + answerText + "\""
    // Don't mind uppercase or lowercase entered, transfer all to uppercase.
    toastText += if (answerText.uppercase() == context.getString(R.string.giraffe_upper)) {
        context.getString(R.string.correct)
    } else {
        context.getString(R.string.incorrect)
    }
    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GuessAnimalScreen()
    }

    @Composable
    fun ScreenContent(modifier: Modifier) {
        //TODO: we add more code here later

        // Context is needed for displaying a Toast message.
        val context = LocalContext.current
        Column(
            Modifier
                .fillMaxHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "It works! - remove this text later"
            )
        }
    }



}
