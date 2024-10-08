package com.bw.testplaylist.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bw.testplaylist.R
import com.bw.testplaylist.databinding.ActivityMainBinding
import com.bw.testplaylist.playlist.PlaylistFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // enable member injection
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, PlaylistFragment.newInstance())
                .commit()
        }
    }
}