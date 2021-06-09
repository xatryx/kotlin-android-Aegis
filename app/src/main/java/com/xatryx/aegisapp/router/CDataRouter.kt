package com.xatryx.aegisapp.router

import com.github.kittinunf.fuel.core.HeaderValues
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.util.FuelRouting

sealed class CDataRouter : FuelRouting {

    override val basePath: String
        get() {
            return "https://backend-aegisapi-uzsndhtaiq-et.a.run.app"
        }

    class GuildDetailsData(val guildId: String, val guildToken: String) : CDataRouter()
    class GuildChannelsData(val guildId: String) : CDataRouter()
    class ChannelDetailsData(val channelId: String) : CDataRouter()
    class ChannelMessagesData(val channelId: String) : CDataRouter()

    override val method: Method
        get() {
            return Method.GET
        }

    override val params: Parameters?
        get() {
            return when(this) {
                is GuildDetailsData -> listOf("token" to this.guildToken)
                is GuildChannelsData -> listOf()
                is ChannelDetailsData -> listOf()
                is ChannelMessagesData -> listOf()
            }
        }

    override val body: String?
        get() {
            return null
        }

    override val path: String
        get() {
            return when(this) {
                is GuildDetailsData -> "/guild/${this.guildId}"
                is GuildChannelsData -> "/guild/${this.guildId}/channel"
                is ChannelDetailsData -> "/channel/${this.channelId}"
                is ChannelMessagesData -> "/channel/${this.channelId}/message"
            }
        }

    override val headers: Map<String, HeaderValues>?
        get() {
            return Headers().set(Headers.CONTENT_TYPE, "application/json")
        }

    override val bytes: ByteArray?
        get() {
            return null
        }
}
