package com.example.wiproassignment.model

import com.google.gson.annotations.SerializedName

data class Facts(

    @SerializedName("title")
    val title: String?,

    @SerializedName("rows")
    val rows: ArrayList<Rows>


)


data class Rows(
    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("imageHref")
    val image: String?
)