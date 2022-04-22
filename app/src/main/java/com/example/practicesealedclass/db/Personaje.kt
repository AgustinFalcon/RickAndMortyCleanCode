package com.example.practicesealedclass.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_personaje")
class Personaje() {

    @ColumnInfo(name = "name")
    val name: String = ""

    @ColumnInfo(name = "status")
    val status: String = ""

    @ColumnInfo(name = "name")
    val species: String = ""

    @ColumnInfo(name = "name")
    val gender: String = ""

    @ColumnInfo(name = "name")
    val image: String = ""

    @PrimaryKey()
    val id: Int = 0

}