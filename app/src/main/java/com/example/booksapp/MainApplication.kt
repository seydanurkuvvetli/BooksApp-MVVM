package com.example.booksapp

import android.app.Application
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.booksapp.common.Constans.BASE_URL
import com.example.booksapp.data.source.remote.BookService
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


    @HiltAndroidApp
    class MainApplication : Application()
