package com.example.searchwidget_appbar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

    private  val _serachWidgetState:MutableState<SerachWidgetState> =
            mutableStateOf(value =SerachWidgetState.CLOSED )

    val seracWidgetstat:State<SerachWidgetState> =_serachWidgetState

    private  val _serachTextState:MutableState<String> = mutableStateOf("")
    val serachTextState:State<String> = _serachTextState

    fun setSerachState(newValue:SerachWidgetState){
        _serachWidgetState.value=newValue
    }
    fun setSerachString(newValue:String){
        _serachTextState.value=newValue
    }

}

