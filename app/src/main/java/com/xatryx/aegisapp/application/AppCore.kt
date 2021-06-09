package com.xatryx.aegisapp.application

import android.app.Application
import com.xatryx.aegisapp.DiscordRepository
import com.xatryx.aegisapp.util.OFuelInstance
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

class AppCore : Application(), DIAware {
    override val di: DI by DI.lazy {
        import(androidXModule(this@AppCore))

        bind { singleton { OFuelInstance.init() } }
        bind { singleton { DiscordRepository(instance()) } }
    }
}