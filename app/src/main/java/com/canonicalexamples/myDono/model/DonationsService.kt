package com.canonicalexamples.myDono.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface DonationsService {
    @GET("/donations/{id}")
    fun getDonations(@Path(value = "id") id: Int): Call<Donations>
}
