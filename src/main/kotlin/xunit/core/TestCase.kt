package xunit.core

import java.lang.reflect.InvocationTargetException
import xunit.annotations.AfterEach
import xunit.annotations.BeforeEach
import kotlin.reflect.KClass
import kotlin.reflect.full.memberFunctions

open class TestCase(protected val name: String) : Testable {

    override fun run(result: TestResult) {
        result.testStarted()

        val testFailure = captureFailure {
            invokeLifecycle(BeforeEach::class)
            invokeTest()
        }
        val afterEachFailure = captureFailure {
            invokeLifecycle(AfterEach::class)
        }

        (testFailure ?: afterEachFailure)?.let { result.testFailed(name, it) }
    }

    private fun invokeLifecycle(annotationClass: KClass<out Annotation>) {
        this::class.memberFunctions
            .filter { function ->
                function.annotations.any { it.annotationClass == annotationClass }
            }
            .onEach { function ->
                require(function.parameters.size == 1) {
                    "@${annotationClass.simpleName} method '${function.name}' must not declare parameters"
                }
            }
            .forEach { it.call(this) }
    }

    private fun invokeTest() {
        val function = this::class.memberFunctions.firstOrNull { it.name == name }
            ?: error("Test method '$name' not found on ${this::class.simpleName}")
        function.call(this)
    }

    private fun captureFailure(block: () -> Unit): Throwable? {
        return try {
            block()
            null
        } catch (throwable: Throwable) {
            if (throwable is InvocationTargetException) {
                throwable.targetException ?: throwable
            } else {
                throwable
            }
        }
    }

}