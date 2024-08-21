package com.bw.testplaylist.playlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class) // will be injected into PlaylistFragment
class PlaylistModule {

    /**
    should not be fragment's responsibility to create and inject components,
    violating single responsibility of SOLID
     */

    @Provides
    fun playlistAPI(retrofit: Retrofit): PlaylistAPI =
        retrofit.create(PlaylistAPI::class.java)

    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        // default local ip, port set with mockoon
        // need to double check
        .baseUrl("http://192.168.0.121:3000/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}