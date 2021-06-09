package com.xatryx.aegisapp.util

import com.github.kittinunf.fuel.core.FuelManager

object OFuelInstance {
    internal fun init(): FuelManager = FuelManager()
}