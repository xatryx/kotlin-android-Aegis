package com.xatryx.aegisapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.xatryx.aegisapp.DiscordRepository
import com.xatryx.aegisapp.model.ChannelDetails
import com.xatryx.aegisapp.model.GuildChannel
import com.xatryx.aegisapp.model.GuildDetails
import com.xatryx.aegisapp.model.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class CommonDiscordViewModel(app: Application) : AndroidViewModel(app), DIAware {

    override val di: DI by closestDI(app)

    private val discordRepository: DiscordRepository by instance()

    private val currentGuildDetails = MutableLiveData<GuildDetails>()
    private val currentGuildChannels =  MutableLiveData<ArrayList<GuildChannel>>()
    private val currentChannelDetails = MutableLiveData<ChannelDetails>()
    private val currentChannelMessages = MutableLiveData<ArrayList<Message>>()

    fun getGuildDetails() : LiveData<GuildDetails> = currentGuildDetails
    fun getGuildChannels() : LiveData<ArrayList<GuildChannel>> = currentGuildChannels
    fun getChannelDetails() : LiveData<ChannelDetails> = currentChannelDetails
    fun getChannelMessages() : LiveData<ArrayList<Message>> = currentChannelMessages

    fun queryGuildDetails(guildId: String, guildToken: String) = viewModelScope.launch(Dispatchers.IO) {
        discordRepository.guildDetails(guildId, guildToken).fold(
            {
                currentGuildDetails.postValue(Gson().fromJson(it, GuildDetails::class.java))
            },
            {

            }
        )
    }

    fun queryGuildChannels(guildId: String) = viewModelScope.launch(Dispatchers.IO) {
        discordRepository.guildChannels(guildId).fold(
            {
                with(ArrayList<GuildChannel>()){
                    this.addAll(Gson().fromJson(it, Array<GuildChannel>::class.java))
                    currentGuildChannels.postValue(this)
                }
            },
            {

            }
        )
    }

    fun queryChannelDetails(channelId: String) = viewModelScope.launch(Dispatchers.IO) {
        discordRepository.channelDetails(channelId).fold(
            {
                currentChannelDetails.postValue(Gson().fromJson(it, ChannelDetails::class.java))
            },
            {

            }
        )
    }

    fun queryChannelMessages(channelId: String) = viewModelScope.launch(Dispatchers.IO) {
        discordRepository.channelMessages(channelId).fold(
            {
                with(ArrayList<Message>()){
                    this.addAll(Gson().fromJson(it, Array<Message>::class.java))
                    currentChannelMessages.postValue(this)
                }
            },
            {

            }
        )
    }
}