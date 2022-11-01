package com.conamobile.maxsecurityapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.conamobile.maxsecurityapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.textView.text = BuildConfig.API_KEY
    }
}