package com.avion.projectskeletonwithhilt.ui.dashboard

import androidx.lifecycle.ViewModel
import com.avion.projectskeletonwithhilt.data.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    val repository: DashboardRepository
) : ViewModel() {

    fun getDashboardData() = repository
}