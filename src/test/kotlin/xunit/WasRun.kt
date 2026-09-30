package xunit

class WasRun(name: String) : TestCase(name) {

    lateinit var log: String

    override fun setUp() {
        log = "setUp "
    }

    fun testMethod() {
        log += "testMethod "
    }

}