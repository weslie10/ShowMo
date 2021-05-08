package com.weslie10.showmo.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.weslie10.showmo.R
import com.weslie10.showmo.databinding.ActivitySplashScreenBinding
import com.weslie10.showmo.ui.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            FULL_SCREEN_FLAG,
            FULL_SCREEN_FLAG
        )
        supportActionBar?.hide()

        animation()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, TIME)
    }

    private fun animation() {
        val upfade = AnimationUtils.loadAnimation(this, R.anim.upfade)
        binding.appName.startAnimation(upfade)
        lifecycleScope.launch(Dispatchers.Default) {
            delay(1200L)
            withContext(Dispatchers.Main) {
                binding.animationView.playAnimation()
            }
        }
    }

    companion object {
        private const val TIME = 3000L
        private const val FULL_SCREEN_FLAG = 1024
    }
}