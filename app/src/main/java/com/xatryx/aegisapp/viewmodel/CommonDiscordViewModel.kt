package com.xatryx.aegisapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.xatryx.aegisapp.DiscordRepository
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class CommonDiscordViewModel(app: Application) : AndroidViewModel(app), DIAware {

    override val di: DI by closestDI(app)

    private val discordRepository: DiscordRepository by instance()
}