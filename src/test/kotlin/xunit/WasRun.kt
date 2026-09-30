package xunit

class WasRun(name: String) : TestCase(name) {

    lateinit var log: String

    override fun setUp() {
        log = "setUp "
    }

    override fun tearDown() {
        log += "tearDown "
    }

    fun testMethod() {
        log += "testMethod "
    }

}