package com.avion.projectskeletonwithhilt.activities.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avion.projectskeletonwithhilt.activities.signup.SignUpActivity
import com.avion.projectskeletonwithhilt.databinding.ActivityLoginBinding
import com.avion.projectskeletonwithhilt.utils.showPassword
import com.avion.projectskeletonwithhilt.utils.value
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.username.value = "vickyd178@gmail.com"

        binding.checkBoxShowPassword.setOnCheckedChangeListener { _, isChecked ->
            binding.password.showPassword(isChecked)
        }

        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

    }
}