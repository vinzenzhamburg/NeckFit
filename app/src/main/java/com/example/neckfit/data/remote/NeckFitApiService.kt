package com.example.neckfit.data.remote

import com.example.neckfit.data.datamodel.ThemeList
import com.example.neckfit.data.datamodel.Uebung
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


// muss geändert werden
const val BASE_URL = "https://public.syntax-institut.de/apps/batch4/Vinzenz/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// name muss geändert werden
interface NeckFitApiService {

    // muss angepasst werden
    @GET("categories.json")
    suspend fun getThemes(): ThemeList
    @GET("exercises.json")
    suspend fun getExercises(): List<Uebung>

}

// namen müssen geändert werden
object NeckFitApi {
    val retrofitService: NeckFitApiService by lazy { retrofit.create(NeckFitApiService::class.java) }
}