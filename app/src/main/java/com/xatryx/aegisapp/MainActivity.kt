package com.xatryx.aegisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import com.xatryx.aegisapp.databinding.ActivityMainBinding
import com.xatryx.aegisapp.viewmodel.CommonDiscordViewModel
import kotlinx.coroutines.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class MainActivity : AppCompatActivity(), DIAware {

    override val di: DI by closestDI()

    private val viewModel: CommonDiscordViewModel by instance()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        viewModel.getGuildChannels().observe(this, { channels ->
            viewModel.queryChannelMessages(channels)
        })

        viewModel.getChannelMessages().observe(this@MainActivity, { res ->
            binding.tvTotalhat.text = "${res.size}"
        })
    }
}