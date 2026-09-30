package xunit

class TestCaseTest(name: String) : TestCase(name) {

    private lateinit var test: WasRun

    fun testTemplateMethod() {
        test = WasRun("testMethod")
        test.run()
        assertEquals("setUp testMethod ", test.log)
    }

}