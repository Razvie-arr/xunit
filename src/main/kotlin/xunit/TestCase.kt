package xunit

import kotlin.reflect.full.memberFunctions

open class TestCase(protected val name: String) {

    protected open fun setUp() {}

    protected open fun tearDown() {}

    fun run(): TestResult {
        val result = TestResult()
        result.testStarted()

        try {
            setUp()
            runTestAndTearDown()
        } catch (_: Throwable) {
            result.testFailed()
        }

        return result
    }

    private fun runTestAndTearDown() {
        try {
            val function = this::class.memberFunctions.firstOrNull { it.name == name }
                ?: error("Test method '$name' not found on ${this::class.simpleName}")
            function.call(this)
        } finally {
            tearDown()
        }
    }

}