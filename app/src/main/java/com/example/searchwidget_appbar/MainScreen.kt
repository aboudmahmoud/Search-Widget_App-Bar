package com.example.searchwidget_appbar

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search

import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val searchWidgetStat by mainViewModel.seracWidgetstat
    val searchWidgetTextStat by mainViewModel.serachTextState
    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetStat = searchWidgetStat ,
                searchWidgetTextStat = searchWidgetTextStat,
                onTextChange = {
                    mainViewModel.setSerachString(it)
                },
                onCloseClicke = { mainViewModel.setSerachState(SerachWidgetState.CLOSED) },
                onSerachCliced = {
                    Log.d("Aboud", "MainScreen: ${it}")
                },
                onSerachTrigried={
                    mainViewModel.setSerachState(SerachWidgetState.OPEND)
                }
            )
        }
    ) {

    }

}

@Composable
fun MainAppBar(
    searchWidgetStat:SerachWidgetState,
    searchWidgetTextStat:String,
    onTextChange: (String) -> Unit,
    onCloseClicke: () -> Unit,
    onSerachCliced: (String) -> Unit,
    onSerachTrigried: () -> Unit,
) {
when(searchWidgetStat){
   SerachWidgetState.CLOSED->{
       DefaultAbar ( onSerachCliced=onSerachTrigried)
   }
    SerachWidgetState.OPEND->{
        SerachBar(text = searchWidgetTextStat,
            onTextChange =onTextChange ,
            onCloseClicke = onCloseClicke,
            onSerachCliced = onSerachCliced)
    }
}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAbar(
    onSerachCliced: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = "Homea")

        },
        actions = {
            IconButton(onClick = onSerachCliced) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Searc Icon", tint = Color.Black
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SerachBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicke: () -> Unit,
    onSerachCliced: (String) -> Unit,
) {

    val AlphaAware = 1.0f
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shadowElevation = 1.dp,
        color = MaterialTheme.colorScheme.surface

    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(alpha = AlphaAware),
                    text = "Serach Here",
                    color = Color.Black
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        onTextChange("")
                    } else {
                        onCloseClicke()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }
            },
            trailingIcon = {
                IconButton(modifier = Modifier.alpha(alpha = AlphaAware), onClick = {onCloseClicke()} ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Black
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,

            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSerachCliced(text)
                }

            ),

            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = 0.1f),

            )
            )
    }
}

@Preview
@Composable
fun PrivewSerch() {
    DefaultAbar(
        {

        }
    )
}

@Preview
@Composable
fun PreiveSerchbar() {
    SerachBar(text ="RandomText" , onTextChange ={} , onCloseClicke = { /*TODO*/ }, onSerachCliced ={} )
}
