package com.example.cinema.data.db

import com.example.cinema.data.db.daos.Daos
import org.koin.java.KoinJavaComponent.inject

class AppDatabase {

    companion object {
        val daos: Daos by inject(Daos::class.java)
    }
}