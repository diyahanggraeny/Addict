package com.diyahanggraeny.addict.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @ColumnInfo(name = "word") val word: String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
