package com.diyahanggraeny.addict.Models


import com.google.gson.annotations.SerializedName

data class Definition(
    val antonyms: List<Any>,
    val definition: String,
    val example: String,
    val synonyms: List<String>
)