package xunit.core

import xunit.annotations.Test
import kotlin.reflect.KClass
import kotlin.reflect.full.memberFunctions

class TestSuite : Testable {

    private val testables = mutableListOf<Testable>()

    companion object {

        fun from(testClass: KClass<out TestCase>): TestSuite {
            val suite = TestSuite()

            val constructor = testClass.constructors.firstOrNull { constructor ->
                constructor.parameters.size == 1 && constructor.parameters.first().type.classifier == String::class
            } ?: error("${testClass.simpleName} must have a constructor taking a String name")

            testClass.memberFunctions
                .filter { function ->
                    function.annotations.any { it.annotationClass == Test::class }
                }
                .forEach { function ->
                    suite.add(constructor.call(function.name))
                }

            return suite
        }

    }

    fun add(testable: Testable) {
        testables.add(testable)
    }

    override fun run(result: TestResult) {
        for (testable in testables) {
            testable.run(result)
        }
    }

}