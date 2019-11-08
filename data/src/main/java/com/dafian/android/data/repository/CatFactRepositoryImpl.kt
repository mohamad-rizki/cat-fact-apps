package com.dafian.android.data.repository

import com.dafian.android.data.source.CatFactCacheDataSource
import com.dafian.android.data.source.CatFactRemoteDataSource
import com.dafian.android.domain.model.Quote
import com.dafian.android.domain.repository.CatFactRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class CatFactRepositoryImpl(
    private val cacheDataSource: CatFactCacheDataSource,
    private val remoteDataSource: CatFactRemoteDataSource
) : CatFactRepository {

    override fun getRandomCatFact(): Flowable<Quote> {
        return cacheDataSource.isCached()
            .flatMapPublisher { isCached ->
                if (isCached && !cacheDataSource.isExpired()) {
                    cacheDataSource.getRandomCatFact()
                } else {
                    remoteDataSource.getRandomCatFact()
                }
            }
    }

    override fun getCatFactList(): Flowable<List<Quote>> {
        return cacheDataSource.isCached()
            .flatMapPublisher { isCached ->
                if (isCached && !cacheDataSource.isExpired()) {
                    cacheDataSource.getCatFactList()
                } else {
                    remoteDataSource.getCatFactList()
                }
            }
            .flatMap {
                saveCatFactList(it)
                    .toSingle { it }
                    .toFlowable()
            }
    }

    override fun saveCatFactList(catFactList: List<Quote>): Completable {
        return cacheDataSource.saveCatFactList(catFactList)
            .doOnComplete {
                cacheDataSource.setLastCacheTime(System.currentTimeMillis())
            }
    }

    override fun clearCatFactList(): Completable {
        return cacheDataSource.clearCatFactList()
    }
}