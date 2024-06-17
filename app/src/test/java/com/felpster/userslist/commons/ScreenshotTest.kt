package com.felpster.userslist.commons

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule

open class ScreenshotTest(
    deviceConfig: DeviceConfig = DeviceConfig.PIXEL_6,
    maxPercentDifference: Double = 0.05,
) {
    companion object {
        private fun createPaparazziDevice(
            deviceConfig: DeviceConfig,
            maxPercentDifference: Double,
        ): Paparazzi = Paparazzi(
            maxPercentDifference = maxPercentDifference,
            deviceConfig = deviceConfig.copy(softButtons = false),
        )
    }

    @get:Rule
    val paparazzi: Paparazzi = createPaparazziDevice(
        deviceConfig = deviceConfig,
        maxPercentDifference = maxPercentDifference,
    )
}
