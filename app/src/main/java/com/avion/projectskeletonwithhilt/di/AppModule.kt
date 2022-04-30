package com.avion.projectskeletonwithhilt.di

import android.content.Context
import com.avion.projectskeletonwithhilt.data.local.PreferencesManager
import com.avion.projectskeletonwithhilt.data.remote.ApiService
import com.avion.projectskeletonwithhilt.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideOKHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()


    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)    //Returns ApiService for network calls


    @Singleton
    @Provides
    fun providesPreferenceManager(@ApplicationContext context: Context): PreferencesManager =
        PreferencesManager(context)


    @ApplicationScope
    @Provides
    @Singleton
    fun providesCoroutineScope() = CoroutineScope(SupervisorJob())
}


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope