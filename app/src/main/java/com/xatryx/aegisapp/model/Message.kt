package com.xatryx.aegisapp.model

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("message_id")
    var messageId: String,

    @SerializedName("created")
    var messageCreated: String,

    @SerializedName("message_neutral_score")
    var messageNeutral: Float,

    @SerializedName("message_abusive_score")
    var messageAbusive: Float,

    @SerializedName("message_hate_score")
    var messageHate: Float
)
