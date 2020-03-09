package com.scansione.mitchdagger222.models

import com.google.gson.annotations.SerializedName

data class Post(@SerializedName("userId") var userId : String,
                @SerializedName("id") var id : Int,
                @SerializedName("title") var title : String,
                @SerializedName("body") var body : String) {
}