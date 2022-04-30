package com.avion.projectskeletonwithhilt.data.remote

import com.avion.projectskeletonwithhilt.utils.Constants
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : BaseDataSource() {

    suspend fun loginUser(token: String, phone: String, password: String) =
        getResult {
            apiService.loginUser(
                phone, password, device_platform = "android",
                device_token = token,
                Constants.DEVICE_MODEL,
                Constants.DEVICE_VERSION.toString(),
                Constants.DEVICE_MANUFACTURER,
                Constants.DEVICE_UUID
            )
        }
}