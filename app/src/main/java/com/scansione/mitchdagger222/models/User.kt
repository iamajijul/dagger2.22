package com.scansione.mitchdagger222.models

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("id") var id : Int,
                @SerializedName("username") var name : String,
                @SerializedName("email") var email : String,
                @SerializedName("website") var website : String) {
}