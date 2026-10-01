package xunit

class TestCaseTest(name: String) : TestCase(name) {

    private lateinit var test: WasRun
    private lateinit var result: TestResult

    override fun setUp() {
        result = TestResult()
    }

    fun testTemplateMethod() {
        test = WasRun("testMethod")
        test.run(result)
        assertEquals("setUp testMethod tearDown ", test.log)
    }

    fun testResult() {
        test = WasRun("testMethod")
        test.run(result)
        assertEquals("1 run, 0 failed", result.summary())
    }

    fun testFailedResult() {
        test = WasRun("testBrokenMethod")
        test.run(result)
        assertEquals("1 run, 1 failed", result.summary())
    }

    fun testFailedResultFormatting() {
        result.testStarted()
        result.testFailed()
        assertEquals("1 run, 1 failed", result.summary())
    }

    fun testSetUpFailure() {
        val test = WasRun("testMethod")
        test.failSetUp = true
        test.run(result)
        assertEquals("1 run, 1 failed", result.summary())
    }

    fun testSuite() {
        val suite = TestSuite()
        suite.add(WasRun("testMethod"))
        suite.add(WasRun("testBrokenMethod"))
        val result = TestResult()
        suite.run(result)
        assertEquals("2 run, 1 failed", result.summary())
    }

    fun testNestedSuite() {
        val innerSuite = TestSuite()
        innerSuite.add(WasRun("testMethod"))

        val outerSuite = TestSuite()
        outerSuite.add(innerSuite)
        outerSuite.add(WasRun("testBrokenMethod"))

        val result = TestResult()
        outerSuite.run(result)
        assertEquals("2 run, 1 failed", result.summary())
    }

}