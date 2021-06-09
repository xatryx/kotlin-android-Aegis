package com.xatryx.aegisapp.`interface`

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result

interface IDiscordData {

    suspend fun guildDetails(guildId: String, guildToken: String) : Result<String, FuelError>

    suspend fun guildChannels(guildId: String) : Result<String, FuelError>

    suspend fun channelDetails(channelId: String) : Result<String, FuelError>

    suspend fun channelMessages(channelId: String) : Result<String, FuelError>

}