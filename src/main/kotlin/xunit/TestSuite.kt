package xunit

import kotlin.reflect.KClass
import kotlin.reflect.full.memberFunctions

class TestSuite : Test {

    private val tests = mutableListOf<Test>()

    companion object {

        fun from(testClass: KClass<out TestCase>): TestSuite {
            val suite = TestSuite()

            val constructor = testClass.constructors.firstOrNull { constructor ->
                constructor.parameters.size == 1 && constructor.parameters.first().type.classifier == String::class
            } ?: error("${testClass.simpleName} must have a constructor taking a String name")

            testClass.memberFunctions
                .filter { it.name.startsWith("test") }
                .forEach { function ->
                    suite.add(constructor.call(function.name))
                }

            return suite
        }

    }

    fun add(test: Test) {
        tests.add(test)
    }

    override fun run(result: TestResult) {
        for (test in tests) {
            test.run(result)
        }
    }

}