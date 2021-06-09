package com.xatryx.aegisapp.model

import com.google.gson.annotations.SerializedName

data class GuildDetails (
    @SerializedName("guild_id")
    var guildId: String,

    @SerializedName("guild_admin_token")
    var guildToken: String,

    @SerializedName("guild_name")
    var guildName: String,

    @SerializedName("guild_icon_url")
    var guildIcon: String
)