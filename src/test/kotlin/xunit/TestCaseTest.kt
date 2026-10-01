package xunit

class TestCaseTest(name: String) : TestCase(name) {

    private lateinit var test: WasRun
    private lateinit var result: TestResult

    override fun setUp() {
        result = TestResult()
    }

    @Test
    fun testTemplateMethod() {
        test = WasRun("testMethod")

        test.run(result)

        assertEquals("setUp testMethod tearDown ", test.log)
    }

    @Test
    fun testResult() {
        test = WasRun("testMethod")

        test.run(result)

        assertEquals("1 run, 0 failed", result.summary())
    }

    @Test
    fun testFailedResult() {
        test = WasRun("testBrokenMethod")

        test.run(result)

        assertEquals(
            """
            1 run, 1 failed
            testBrokenMethod: RuntimeException
            """.trimIndent(),
            result.summary(),
        )
    }

    @Test
    fun testFailedResultFormatting() {
        result.testStarted()
        result.testFailed("dummyTest", RuntimeException("dummy"))

        assertEquals(
            """
            1 run, 1 failed
            dummyTest: RuntimeException: dummy
            """.trimIndent(),
            result.summary(),
        )
    }

    @Test
    fun testSetUpFailure() {
        val test = WasRun("testMethod")
        test.failSetUp = true

        test.run(result)

        assertEquals(
            """
            1 run, 1 failed
            testMethod: RuntimeException: setUp failed
            """.trimIndent(),
            result.summary(),
        )
    }

    @Test
    fun testSuite() {
        val suite = TestSuite()
        suite.add(WasRun("testMethod"))
        suite.add(WasRun("testBrokenMethod"))
        val result = TestResult()

        suite.run(result)

        assertEquals(
            """
            2 run, 1 failed
            testBrokenMethod: RuntimeException
            """.trimIndent(),
            result.summary(),
        )
    }

    @Test
    fun testNestedSuite() {
        val innerSuite = TestSuite()
        innerSuite.add(WasRun("testMethod"))

        val outerSuite = TestSuite()
        outerSuite.add(innerSuite)
        outerSuite.add(WasRun("testBrokenMethod"))

        val result = TestResult()
        outerSuite.run(result)
        assertEquals(
            """
            2 run, 1 failed
            testBrokenMethod: RuntimeException
            """.trimIndent(),
            result.summary(),
        )
    }

    @Test
    fun testCreateAndRunSuiteFromClass() {
        val suite = TestSuite.from(WasRun::class)
        val result = TestResult()

        suite.run(result)

        assertEquals(
            """
            2 run, 1 failed
            testBrokenMethod: RuntimeException
            """.trimIndent(),
            result.summary(),
        )
    }

    @Test
    fun testFailedTestResults() {
        test = WasRun("testBrokenMethod")

        test.run(result)

        assertEquals(1, result.failures().size)
        val failure = result.failures().first()
        assertEquals("testBrokenMethod", failure.testName)
        assertTrue(failure.cause is RuntimeException)
        assertTrue(result.summary().contains("testBrokenMethod: RuntimeException"))
    }

}