package com.example.testryc.models.data

import com.google.gson.annotations.SerializedName

data class RequesUserItem(
    @SerializedName("ChildName")
    val childName: String,
    @SerializedName("MonthOfAge")
    val monthOfAge: String,
    @SerializedName("ParentName")
    val parentName: String,
    @SerializedName("SDT")
    val sdt: String
)