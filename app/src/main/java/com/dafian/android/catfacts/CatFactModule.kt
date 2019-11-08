package com.dafian.android.catfacts

import com.dafian.android.catfacts.executor.UiThread
import com.dafian.android.data.executor.JobExecutor
import com.dafian.android.data.repository.CatFactRepositoryImpl
import com.dafian.android.data.source.CatFactRemoteDataSource
import com.dafian.android.domain.executor.PostExecutionThread
import com.dafian.android.domain.executor.ThreadExecutor
import com.dafian.android.domain.repository.CatFactRepository
import com.dafian.android.presentation.viewmodel.ListCatFactViewModel
import com.dafian.android.presentation.viewmodel.RandomCatFactViewModel
import com.dafian.android.remote.CatFactRemoteDataSourceImpl
import com.dafian.android.remote.service.CatFactServiceFactory
import com.dafian.android.usecase.GetCatFactListUseCase
import com.dafian.android.usecase.GetRandomCatFactUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

}

val cacheModule = module {

}

val dataModule = module {

    factory<CatFactRepository> { CatFactRepositoryImpl(get(), get()) }
}

val domainModule = module {

}

val presentationModule = module {

    viewModel { RandomCatFactViewModel(get()) }

    viewModel { ListCatFactViewModel(get()) }
}

val remoteModule = module {

    single { CatFactServiceFactory.makeCatFactService(BuildConfig.DEBUG) }

    factory<CatFactRemoteDataSource> { CatFactRemoteDataSourceImpl(get()) }
}

val useCaseModule = module {

    single { GetRandomCatFactUseCase(get(), get(), get()) }

    single { GetCatFactListUseCase(get(), get(), get()) }
}

val mobileUiModule = module {

    single { JobExecutor() as ThreadExecutor }

    single { UiThread() as PostExecutionThread }
}