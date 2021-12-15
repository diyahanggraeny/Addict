package com.diyahanggraeny.addict.Models


import com.google.gson.annotations.SerializedName

data class APIResponseItem(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<Phonetic>,
    val word: String
)