package com.xatryx.aegisapp.application

import android.app.Application
import com.xatryx.aegisapp.util.OFuelInstance
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bind
import org.kodein.di.singleton

class AppCore : Application(), DIAware {
    override val di: DI by DI.lazy {
        import(androidXModule(this@AppCore))

        bind { singleton { OFuelInstance.init() } }
    }
}