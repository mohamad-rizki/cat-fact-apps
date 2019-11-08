package com.dafian.android.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dafian.android.domain.model.Quote
import com.dafian.android.presentation.data.Resource
import com.dafian.android.presentation.data.ResourceState
import com.dafian.android.usecase.GetRandomCatFactUseCase
import io.reactivex.subscribers.DisposableSubscriber

class RandomCatFactViewModel(
    private val useCase: GetRandomCatFactUseCase
) : ViewModel() {

    val quote = MutableLiveData<Resource<Quote>>()

    override fun onCleared() {
        useCase.dispose()
        super.onCleared()
    }

    fun fetchRandomCatFact() {
        quote.postValue(Resource(ResourceState.LOADING, null, null))
        useCase.execute(object : DisposableSubscriber<Quote>() {
            override fun onComplete() {

            }

            override fun onNext(t: Quote?) {
                quote.postValue(Resource(ResourceState.SUCCESS, t, null))
            }

            override fun onError(t: Throwable?) {
                quote.postValue(Resource(ResourceState.ERROR, null, t))
            }
        })
    }
}