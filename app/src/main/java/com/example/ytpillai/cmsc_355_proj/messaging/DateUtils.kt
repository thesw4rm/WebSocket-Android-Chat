package com.example.ytpillai.cmsc_355_proj.messaging

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun fromMillisToTimeString(millis: Long) : String {
        val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return format.format(millis)
    }
}