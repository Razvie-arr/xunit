package xunit

class TestCaseTest(name: String) : TestCase(name) {

    private lateinit var test: WasRun

    override fun setUp() {
        super.setUp()
        test = WasRun("testMethod")
    }

    fun testRunning() {
        test.run()
        assertTrue(test.wasRun)
    }

    fun testSetUp() {
        test.run()
        assertTrue(test.wasSetUp)
    }

}