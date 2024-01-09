package pl.droidsonroids.composekit.utils

import org.slf4j.LoggerFactory

internal object Logger {

    private val logger = LoggerFactory.getLogger("some-logger")

    fun i(msg: String) = logger.info(msg)
    fun d(msg: String) = logger.debug(msg)
}