package com.ijk.githubreposearch.utils.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Long.toDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd.MM.yyyy")
    return format.format(date)
}

fun Float.round10(): Float {
    val r = (this * 10).toInt()
    return r.toFloat() / 10
}

fun Long.getDay(): String {
    val date = Date(this)
    val c = Calendar.getInstance()
    c.time = date
    return when (c.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> "MON"
        Calendar.TUESDAY -> "TUE"
        Calendar.WEDNESDAY -> "WED"
        Calendar.THURSDAY -> "TH"
        Calendar.FRIDAY -> "FR"
        Calendar.SATURDAY -> "SAT"
        Calendar.SUNDAY -> "SUN"
        else -> ""
    }
}

fun Long.getDayNormal(): String {
    val date = Date(this)
    val c = Calendar.getInstance()
    c.time = date
    return when (c.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> "Monday"
        Calendar.TUESDAY -> "Tuesday"
        Calendar.WEDNESDAY -> "Wednesday"
        Calendar.THURSDAY -> "Thursday"
        Calendar.FRIDAY -> "Friday"
        Calendar.SATURDAY -> "Saturday"
        Calendar.SUNDAY -> "Sunday"
        else -> ""
    }
}