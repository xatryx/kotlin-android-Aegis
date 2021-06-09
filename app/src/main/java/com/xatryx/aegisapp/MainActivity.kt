package com.xatryx.aegisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import com.bumptech.glide.Glide
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

        viewModel.getGuildDetails().observe(this, { guild ->
            binding.apply {
                TVGuildName.text = guild.guildName
                Glide.with(root).load(guild.guildIcon).into(IVIcon)
            }
        })

        viewModel.getGuildChannels().observe(this, { channels ->
            viewModel.queryChannelMessages(channels)
        })

        viewModel.getChannelMessages().observe(this@MainActivity, { res ->
            binding.tvTotalhat.text = "${res.size}"
            binding.tvNormalChat.text = "${res.filter { it.messageNeutral > it.messageAbusive && it.messageNeutral > it.messageHate }.size}"
            binding.tvAbusiveChat.text = "${res.filter { it.messageAbusive > it.messageNeutral && it.messageAbusive > it.messageHate }.size}"
            binding.tvHateChat.text = "${res.filter { it.messageHate > it.messageNeutral && it.messageHate > it.messageAbusive }.size}"
        })
    }
}