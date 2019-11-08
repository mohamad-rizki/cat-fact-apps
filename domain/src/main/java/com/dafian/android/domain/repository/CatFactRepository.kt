package com.dafian.android.domain.repository

import com.dafian.android.domain.model.Quote
import io.reactivex.Completable
import io.reactivex.Flowable

interface CatFactRepository {

    fun getRandomCatFact(): Flowable<Quote>

    fun getCatFactList(): Flowable<List<Quote>>

    fun saveCatFactList(catFactList: List<Quote>): Completable

    fun clearCatFactList(): Completable
}