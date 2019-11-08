package com.dafian.android.usecase

import com.dafian.android.domain.base.FlowableUseCase
import com.dafian.android.domain.executor.PostExecutionThread
import com.dafian.android.domain.executor.ThreadExecutor
import com.dafian.android.domain.model.Quote
import com.dafian.android.domain.repository.CatFactRepository
import io.reactivex.Flowable

open class GetCatFactListUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val repository: CatFactRepository
) : FlowableUseCase<List<Quote>, Void?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Flowable<List<Quote>> {
        return repository.getCatFactList()
    }
}