package com.xatryx.aegisapp.application

import android.app.Application
import com.xatryx.aegisapp.repository.DiscordRepository
import com.xatryx.aegisapp.util.OFuelInstance
import com.xatryx.aegisapp.viewmodel.CommonDiscordViewModel
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

class AppCore : Application(), DIAware {
    override val di: DI by DI.lazy {
        import(androidXModule(this@AppCore))

        bind { singleton { OFuelInstance.init() } }
        bind { singleton { DiscordRepository(instance()) } }
        bind { singleton { CommonDiscordViewModel(instance()) } }
    }
}