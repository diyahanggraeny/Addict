package com.diyahanggraeny.addict.Models


import com.google.gson.annotations.SerializedName

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)