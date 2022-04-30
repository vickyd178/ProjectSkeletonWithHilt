package com.avion.projectskeletonwithhilt.data.repository

import com.avion.projectskeletonwithhilt.data.remote.RemoteDataSource
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) {

}