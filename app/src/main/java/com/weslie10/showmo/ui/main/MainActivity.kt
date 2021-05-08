package com.weslie10.showmo.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.weslie10.showmo.R
import com.weslie10.showmo.databinding.ActivityMainBinding
import com.weslie10.showmo.ui.main.about.AboutFragment
import com.weslie10.showmo.ui.main.favorite.FavoriteFragment
import com.weslie10.showmo.ui.main.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateFragment(HomeFragment())
        viewModel.getFragment().observe(this, { fragment ->
            when (fragment) {
                "favorite" -> navigateFragment(FavoriteFragment())
                "about" -> navigateFragment(AboutFragment())
                else -> navigateFragment(HomeFragment())
            }
            Log.d("onCreate", fragment)
        })

        binding.bottomNavigation.setNavigationChangeListener { _, position ->
            when (position) {
                0 -> {
                    viewModel.setFragment("home")
                    navigateFragment(HomeFragment())
                }
                1 -> {
                    viewModel.setFragment("favorite")
                    navigateFragment(FavoriteFragment())
                }
                2 -> {
                    viewModel.setFragment("about")
                    navigateFragment(AboutFragment())
                }
            }
        }
    }

    private fun navigateFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.host_frame, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}