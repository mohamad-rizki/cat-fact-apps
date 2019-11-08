package com.dafian.android.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dafian.android.domain.model.Quote
import com.dafian.android.presentation.data.Resource
import com.dafian.android.presentation.data.ResourceState
import com.dafian.android.usecase.GetCatFactListUseCase
import io.reactivex.subscribers.DisposableSubscriber

class ListCatFactViewModel(
    private val useCase: GetCatFactListUseCase
) : ViewModel() {

    val quoteList = MutableLiveData<Resource<List<Quote>>>()

    override fun onCleared() {
        useCase.dispose()
        super.onCleared()
    }

    fun fetchCatFactList() {
        quoteList.postValue(Resource(ResourceState.LOADING, null, null))
        useCase.execute(object : DisposableSubscriber<List<Quote>>() {
            override fun onComplete() {

            }

            override fun onNext(t: List<Quote>?) {
                quoteList.postValue(Resource(ResourceState.SUCCESS, t, null))
            }

            override fun onError(t: Throwable?) {
                quoteList.postValue(Resource(ResourceState.ERROR, null, t))
            }
        })
    }
}