package com.felpster.userslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.felpster.userslits.R
import com.felpster.userslits.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this)).apply { setContentView(this.root) }
    }

    override fun onStart() {
        super.onStart()
        setSupportActionBar(binding.toolbar)

        NavigationUI.setupActionBarWithNavController(
            this,
            findNavController(binding.appNavHostFragment.id),
            AppBarConfiguration(setOf(R.id.homeFragment)),
        )
    }
}
