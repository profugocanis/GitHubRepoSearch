package com.ijk.githubreposearch.utils.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
fun String.decoding(): String {
    val decodedBytes = Base64.getDecoder().decode(this)
    return String(decodedBytes)
}