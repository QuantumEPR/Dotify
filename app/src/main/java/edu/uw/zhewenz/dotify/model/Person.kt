package edu.uw.zhewenz.dotify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person (
        val profilePic: Int,
        val name: String,
        val age: Int,
        val email: String,
        val date: String,
        val height: String
): Parcelable