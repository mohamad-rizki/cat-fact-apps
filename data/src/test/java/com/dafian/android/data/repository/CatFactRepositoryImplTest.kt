package com.dafian.android.data.repository

import com.dafian.android.data.source.CatFactCacheDataSource
import com.dafian.android.data.source.CatFactRemoteDataSource
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CatFactRepositoryImplTest {

    private lateinit var mockCacheDataSource: CatFactCacheDataSource
    private lateinit var mockRemoteDataSource: CatFactRemoteDataSource

    private lateinit var repository: CatFactRepositoryImpl

    @Before
    fun setUp() {

        mockCacheDataSource = mock()
        mockRemoteDataSource = mock()

        repository = CatFactRepositoryImpl(
            mockCacheDataSource,
            mockRemoteDataSource
        )
    }

    @Test
    fun clearCatFactList() {

        whenever(mockCacheDataSource.clearCatFactList())
            .thenReturn(Completable.complete())

        val testObserver = repository.clearCatFactList().test()

        testObserver.assertComplete()
    }
}