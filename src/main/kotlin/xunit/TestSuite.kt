package xunit

import kotlin.reflect.KClass
import kotlin.reflect.full.memberFunctions

class TestSuite : Test {

    private val tests = mutableListOf<Test>()

    constructor()

    constructor(testClass: KClass<out TestCase>) : this() {
        populateTestsFromClass(testClass)
    }

    fun add(test: Test) {
        tests.add(test)
    }

    override fun run(result: TestResult) {
        for (test in tests) {
            test.run(result)
        }
    }

    private fun populateTestsFromClass(testClass: KClass<out TestCase>) {
        val constructor = testClass.constructors.firstOrNull { constructor ->
            constructor.parameters.size == 1 && constructor.parameters.first().type.classifier == String::class
        } ?: error("${testClass.simpleName} must have a constructor taking a String name")

        testClass.memberFunctions
            .filter { it.name.startsWith("test") }
            .forEach { function ->
                add(constructor.call(function.name))
            }
    }

}