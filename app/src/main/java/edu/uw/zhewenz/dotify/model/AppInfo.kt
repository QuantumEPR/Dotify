package edu.uw.zhewenz.dotify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class AppInfo (
        val devName: String,
        val version: String,
        val github: String
): Parcelable