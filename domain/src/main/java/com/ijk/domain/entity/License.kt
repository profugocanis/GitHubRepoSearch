package com.ijk.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class License(
    val key: String,
    val name: String,
    val url: String,
    val spdx_id: String,
    val node_id: String,
    val html_url: String
): Parcelable