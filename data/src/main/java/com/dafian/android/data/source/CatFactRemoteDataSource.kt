package com.dafian.android.data.source

import com.dafian.android.domain.model.Quote
import io.reactivex.Flowable

interface CatFactRemoteDataSource {

    fun getRandomCatFact(): Flowable<Quote>

    fun getCatFactList(): Flowable<List<Quote>>
}