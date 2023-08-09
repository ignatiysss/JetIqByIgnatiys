package com.example.jetiq.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessageViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Message"
    }
    val text: LiveData<String> = _text
}