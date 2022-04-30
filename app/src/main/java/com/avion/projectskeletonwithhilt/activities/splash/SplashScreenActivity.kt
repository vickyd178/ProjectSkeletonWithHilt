package com.avion.projectskeletonwithhilt.activities.splash

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.avion.projectskeletonwithhilt.activities.MainActivity
import com.avion.projectskeletonwithhilt.activities.login.LoginActivity
import com.avion.projectskeletonwithhilt.data.local.PreferencesManager
import com.avion.projectskeletonwithhilt.databinding.ActivitySplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "SplashScreenActivity"

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    @Inject
    lateinit var preferencesManager: PreferencesManager

    private val viewModel: SplashViewModel by viewModels()

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

        }
        setContentView(binding.root)
        window.navigationBarColor = Color.TRANSPARENT
        viewModel.saveUserLogin(isLogin = false)
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getUserDetail().observe(this) { userSnapShot ->
                if (userSnapShot.isCurrentlyLoggedIn == true) {
                    Log.d(TAG, "redirecting to main")
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
                }
                finish()
            }
        }, 3000)

    }
}