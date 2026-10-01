package xunit

import java.lang.reflect.InvocationTargetException
import kotlin.reflect.full.memberFunctions

open class TestCase(protected val name: String) : Testable {

    protected open fun setUp() {}

    protected open fun tearDown() {}

    override fun run(result: TestResult) {
        result.testStarted()

        try {
            setUp()
            runTestAndTearDown()
        } catch (t: Throwable) {
            val rootCause = if (t is InvocationTargetException) t.targetException ?: t else t
            result.testFailed(name, rootCause)
        }
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