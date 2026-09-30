package xunit

class TestCaseTest(name: String) : TestCase(name) {

    private lateinit var test: WasRun

    fun testTemplateMethod() {
        test = WasRun("testMethod")
        test.run()
        assertEquals("setUp testMethod tearDown ", test.log)
    }

    fun testResult() {
        test = WasRun("testMethod")
        val result = test.run()
        assertEquals("1 run, 0 failed", result.summary())
    }

    fun testFailedResult() {
        test = WasRun("testBrokenMethod")
        val result = test.run()
        assertEquals("1 run, 1 failed", result.summary())
    }

}