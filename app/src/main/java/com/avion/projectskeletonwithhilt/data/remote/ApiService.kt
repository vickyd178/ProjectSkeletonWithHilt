package com.avion.projectskeletonwithhilt.data.remote

import com.avion.projectskeletonwithhilt.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {


    @FormUrlEncoded
    @POST("signin")
    suspend fun loginUser(
        @Field("mobile_number") mobile_number: String,
        @Field("password") password: String,
        @Field("device_platform") device_platform: String,
        @Field("device_token") device_token: String,
        @Field("model") model: String,
        @Field("version") version: String,
        @Field("manufacturer") manufacturer: String,
        @Field("uuid") uuid: String,
    ): Response<LoginResponse>


}