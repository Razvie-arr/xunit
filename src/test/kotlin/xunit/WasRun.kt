package xunit

import xunit.annotations.AfterEach
import xunit.annotations.BeforeEach
import xunit.annotations.Test
import xunit.core.TestCase

class WasRun(name: String) : TestCase(name) {

    lateinit var log: String
    var failSetUp = false

    @BeforeEach
    fun beforeEach() {
        if (failSetUp) {
            throw RuntimeException("setUp failed")
        }
        log = "setUp "
    }

    @AfterEach
    fun afterEach() {
        log += "tearDown "
    }

    @Test
    fun testMethod() {
        log += "testMethod "
    }

    @Test
    fun testBrokenMethod() {
        throw RuntimeException()
    }

}