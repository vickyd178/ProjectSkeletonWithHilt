package com.avion.projectskeletonwithhilt.data.remote.response_utils


import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.avion.projectskeletonwithhilt.data.remote.response_utils.Resource.Status.ERROR
import com.avion.projectskeletonwithhilt.data.remote.response_utils.Resource.Status.SUCCESS
import kotlinx.coroutines.Dispatchers

fun <T> performPostOperation(
    networkCall: suspend () -> Resource<T>,
): LiveData<Resource<T>> = liveData(Dispatchers.IO) {
    emit(Resource.loading())
    val response = networkCall.invoke()

    if (response.status == ERROR) {
        emit(Resource.error(response.message!!))
    } else if (response.status == SUCCESS) {
        emit(response)
    }
}