package com.sulistyo.github.user.core.di

import androidx.room.Room
import com.sulistyo.github.user.BuildConfig
import com.sulistyo.github.user.core.data.DataRepository
import com.sulistyo.github.user.core.data.local.LocalDataSource
import com.sulistyo.github.user.core.data.local.room.GithubUserDatabase
import com.sulistyo.github.user.core.data.remote.ApiService
import com.sulistyo.github.user.core.data.remote.RemoteDataSource
import com.sulistyo.github.user.core.domain.repository.IDataRepository
import com.sulistyo.github.user.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<GithubUserDatabase>().userDao }
    single {
        Room.databaseBuilder(androidContext(), GithubUserDatabase::class.java, "github_user.db")
            .fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IDataRepository> { DataRepository(get(), get(), get()) }

}