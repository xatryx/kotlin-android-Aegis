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

        viewModel.getGuildChannels().observe(this, { channels ->

            var collection: ArrayList<com.xatryx.aegisapp.model.Message> = arrayListOf()

            for (channel in channels) {
                runBlocking {
                    viewModel.queryChannelMessages(channel.channelId)

                    launch {
                        viewModel.getChannelMessages().observe(this@MainActivity, { res ->
                            collection.addAll(res)
                        })
                    }
                }
            }

            Log.i("NetworkState", "mate is at ${collection.toString()}")

            binding.tvNormalChat.text = "${collection.size}"
        })
    }
}