package com.mahmoudashraf.linkdevtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mahmoudashraf.core.views.viewBinding
import com.mahmoudashraf.linkdevtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}