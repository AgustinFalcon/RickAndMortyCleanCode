package com.example.practicesealedclass

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicesealedclass.db.Personaje
import com.example.practicesealedclass.db.PersonajeDAO


@Database(entities = [Personaje::class], version = 1)
abstract class PersonajeDataBase : RoomDatabase() {

    abstract fun personajeDao(): PersonajeDAO

}