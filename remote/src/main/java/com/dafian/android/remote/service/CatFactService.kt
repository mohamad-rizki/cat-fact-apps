package com.dafian.android.remote.service

import com.dafian.android.remote.model.QuoteDTO
import io.reactivex.Flowable
import retrofit2.http.GET

interface CatFactService {

    @GET("facts/random")
    fun getRandomCatFact(): Flowable<QuoteDTO>

    @GET("facts/random?animal_type=cat&amount=10")
    fun getCatFactList(): Flowable<List<QuoteDTO>>
}