package com.example.rmaapp.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.toTimeStamp(): String {
    return SimpleDateFormat("dd.MM.yyyy. HH:mm", Locale.getDefault()).format(time)
}