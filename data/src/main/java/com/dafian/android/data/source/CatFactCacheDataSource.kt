package com.dafian.android.data.source

import com.dafian.android.domain.model.Quote
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CatFactCacheDataSource {

    fun getRandomCatFact(): Flowable<Quote>

    fun getCatFactList(): Flowable<List<Quote>>

    fun saveCatFactList(catFactList: List<Quote>): Completable

    fun clearCatFactList(): Completable

    fun isCached(): Single<Boolean>

    fun setLastCacheTime(lastCacheTime: Long)

    fun isExpired(): Boolean
}