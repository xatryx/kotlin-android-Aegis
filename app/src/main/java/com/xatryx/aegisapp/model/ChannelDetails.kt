package com.xatryx.aegisapp.model

import com.google.gson.annotations.SerializedName

data class ChannelDetails(
    @SerializedName("channel_id")
    var channelId: String,

    @SerializedName("channel_name")
    var channelName: String
)
