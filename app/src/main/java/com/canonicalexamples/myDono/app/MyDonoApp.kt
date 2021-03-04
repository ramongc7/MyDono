package com.canonicalexamples.myDono.app

import android.app.Application
import com.canonicalexamples.myDono.model.Ong
import com.canonicalexamples.myDono.model.ONGDatabase
import com.canonicalexamples.myDono.model.DonationsService
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyDonoApp: Application() {
    val database by lazy { ONGDatabase.getInstance(this) }
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(DonationsService::class.java)
    }
    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            database.clearAllTables()
            database.ongDAO.apply {
                this.create(ong = Ong(id = 0, name = "Unicef", rating = 1))
                this.create(ong = Ong(id = 1, name = "Cruz Roja", rating = 1))
                this.create(ong = Ong(id = 2, name = "Caritas", rating = 1))
            }
        }
    }
}
