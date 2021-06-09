package com.xatryx.aegisapp.model

import com.google.gson.annotations.SerializedName

data class GuildChannel(
    @SerializedName("channel_id")
    var channelId: String
)
