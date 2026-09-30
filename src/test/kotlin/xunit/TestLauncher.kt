package xunit

fun main() {
    val tests = listOf(
        "testTemplateMethod",
        "testResult",
        "testFailedResult",
        "testFailedResultFormatting",
        "testSetUpFailure"
    )

    for (testName in tests) {
        val result = TestCaseTest(testName).run()
        println("$testName: ${result.summary()}")
        assertEquals("1 run, 0 failed", result.summary())
    }
}