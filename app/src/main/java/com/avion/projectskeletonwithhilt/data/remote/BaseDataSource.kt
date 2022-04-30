package com.avion.projectskeletonwithhilt.data.remote

import com.avion.projectskeletonwithhilt.data.remote.response_utils.Resource
import com.google.gson.JsonSyntaxException
import retrofit2.Response
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            } else {
                if (response.code() == 405) {
                    return error(
                        " ${response.code()} ${response.message()}",
                        exception = SocketException("Network error occurred, please try again.")
                    )
                }
                if (response.code() == 500) {
                    return error(
                        " ${response.code()} ${response.message()}",
                        exception = SocketException("Network error occurred, please try again.")
                    )
                }
            }
            return error(" ${response.code()} ${response.message()}", exception = null)
        } catch (e: Exception) {
            e.printStackTrace()
            return error(null, e)
        }
    }

    private fun <T> error(message: String?, exception: Exception?): Resource<T> {
        var customMessage: String? = null;
        exception?.let {
            if (it is UnknownHostException || it is SocketTimeoutException || it is SocketTimeoutException) {
                customMessage = "No Internet Connection."
            } else if (it is SocketException) {
                customMessage = "Network error occurred, please try again."
            } else if (it is JsonSyntaxException) {
                customMessage = "Something went wrong! Please try again later."
            }
        }
        println(customMessage ?: message ?: "Something went wrong,please try again.")

        return Resource.error(
            customMessage ?: message ?: "Something went wrong,please try again."
        )
    }
}
