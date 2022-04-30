package com.avion.projectskeletonwithhilt.activities.login

import androidx.lifecycle.ViewModel
import com.avion.projectskeletonwithhilt.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

}