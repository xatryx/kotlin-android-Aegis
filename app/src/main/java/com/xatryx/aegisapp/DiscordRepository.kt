package com.xatryx.aegisapp

import android.content.Context
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.coroutines.awaitStringResult
import com.github.kittinunf.result.Result
import com.xatryx.aegisapp.`interface`.IDiscordData
import com.xatryx.aegisapp.router.CDataRouter
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class DiscordRepository(context: Context) : IDiscordData, DIAware {

    override val di: DI by closestDI(context)

    private val fuelInstance: FuelManager by instance()

    override suspend fun guildDetails(guildId: String, guildToken: String): Result<String, FuelError> {
        return fuelInstance.request(CDataRouter.GuildDetailsData(guildId, guildToken)).awaitStringResult()
    }

    override suspend fun guildChannels(guildId: String): Result<String, FuelError> {
        return fuelInstance.request(CDataRouter.GuildChannelsData(guildId)).awaitStringResult()
    }

    override suspend fun channelDetails(channelId: String): Result<String, FuelError> {
        return fuelInstance.request(CDataRouter.ChannelDetailsData(channelId)).awaitStringResult()
    }

    override suspend fun channelMessages(channelId: String): Result<String, FuelError> {
        return fuelInstance.request(CDataRouter.ChannelMessagesData(channelId)).awaitStringResult()
    }

}