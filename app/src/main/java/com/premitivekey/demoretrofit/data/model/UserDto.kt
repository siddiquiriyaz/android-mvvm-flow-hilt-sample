package com.premitivekey.demoretrofit.data.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    @field:SerializedName("company")
    val company: Company? = null,
)
data class Company(

    @field:SerializedName("bs")
    val bs: String? = null,

    @field:SerializedName("catchPhrase")
    val catchPhrase: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)