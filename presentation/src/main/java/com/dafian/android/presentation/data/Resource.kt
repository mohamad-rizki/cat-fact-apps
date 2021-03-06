package com.dafian.android.presentation.data

open class Resource<out T> constructor(
    val status: ResourceState,
    val data: T?,
    val throwable: Throwable?
) {

    fun <T> loading(): Resource<T> {
        return Resource(ResourceState.LOADING, null, null)
    }

    fun <T> success(data: T): Resource<T> {
        return Resource(ResourceState.SUCCESS, data, null)
    }

    fun <T> error(throwable: Throwable?): Resource<T> {
        return Resource(ResourceState.ERROR, null, throwable)
    }

}