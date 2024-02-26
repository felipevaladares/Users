package com.felpster.userslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.felpster.userslist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this)).apply { setContentView(this.root) }
    }

//    override fun onStart() {
//        super.onStart()
//        setSupportActionBar(binding.toolbar)
//
//        NavigationUI.setupActionBarWithNavController(
//            this,
//            findNavController(binding.appNavHostFragment.id),
//            AppBarConfiguration(setOf(R.id.homeFragment)),
//        )
//    }
}
