package org.example.xunit

class TestCaseTest(name: String) : TestCase(name) {

    fun testRunning() {
        val test = WasRun("testMethod")
        check(!test.wasRun)
        test.run()
        check(test.wasRun)
        println("Test running test passed!")
    }

}

fun main() {
    TestCaseTest("testRunning").run()
}