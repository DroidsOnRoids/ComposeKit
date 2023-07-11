package utils

import org.slf4j.LoggerFactory

object Logger {

    private val logger = LoggerFactory.getLogger("some-logger")

    fun i(msg: String) = logger.info(msg)
    fun d(msg: String) = logger.debug(msg)
}