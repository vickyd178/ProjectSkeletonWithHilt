package com.avion.projectskeletonwithhilt.data.repository

import com.avion.projectskeletonwithhilt.data.local.PreferencesManager
import com.avion.projectskeletonwithhilt.data.remote.RemoteDataSource
import com.avion.projectskeletonwithhilt.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @ApplicationScope private val scope: CoroutineScope,
    private val preferencesManager: PreferencesManager
) {
    /*fun loginUser(token: String, phone: String, password: String) = performPostOperation(
        networkCall = { remoteDataSource.loginUser(token, phone, password) },
    )*/

}