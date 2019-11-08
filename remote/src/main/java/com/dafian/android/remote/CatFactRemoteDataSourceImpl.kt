package com.dafian.android.remote

import com.dafian.android.data.source.CatFactRemoteDataSource
import com.dafian.android.domain.model.Quote
import com.dafian.android.remote.service.CatFactService
import io.reactivex.Flowable

class CatFactRemoteDataSourceImpl(
    private val service: CatFactService
) : CatFactRemoteDataSource {

    override fun getRandomCatFact(): Flowable<Quote> {
        return service.getRandomCatFact()
            .map { Quote(it.id, it.type, it.text) }
    }

    override fun getCatFactList(): Flowable<List<Quote>> {
        return service.getCatFactList()
            .map { quoteDTOList ->
                quoteDTOList.map {
                    Quote(it.id, it.type, it.text)
                }
            }
    }
}