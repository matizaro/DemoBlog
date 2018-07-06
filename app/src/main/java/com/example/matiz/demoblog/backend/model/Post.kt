package com.example.matiz.demoblog.backend.model

import android.os.Parcel
import android.os.Parcelable
import io.mironov.smuggler.AutoParcelable


data class Post(val userId : Int = 0,
                val id : Int = 0,
                val title :String = "",
                val body : String = "") : AutoParcelable