package com.avion.projectskeletonwithhilt.activities.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.avion.projectskeletonwithhilt.data.local.PreferencesManager
import com.avion.projectskeletonwithhilt.data.model.UserDetails
import com.avion.projectskeletonwithhilt.di.ApplicationScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SplashViewModel"

@HiltViewModel
class SplashViewModel @Inject constructor(
    val preferencesManager: PreferencesManager,
    @ApplicationScope val coroutineScope: CoroutineScope
) : ViewModel() {

    private val userLivedata: LiveData<UserDetails> =
        preferencesManager.getUserDetails().asLiveData() // its called once

    fun getUserDetail(): LiveData<UserDetails> {
        Log.d(TAG, "getUserDetail: ")
        return userLivedata
    }

    fun saveUserLogin(isLogin: Boolean? = null) = coroutineScope.launch {
        preferencesManager.saveUserInfo(UserDetails(isCurrentlyLoggedIn = isLogin))
    }
}