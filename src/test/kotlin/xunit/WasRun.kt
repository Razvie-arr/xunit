package xunit

class WasRun(name: String) : TestCase(name) {

    lateinit var log: String
    var failSetUp = false

    override fun setUp() {
        if (failSetUp) {
            throw RuntimeException("setUp failed")
        }
        log = "setUp "
    }

    override fun tearDown() {
        log += "tearDown "
    }

    fun testMethod() {
        log += "testMethod "
    }

    fun testBrokenMethod() {
        throw Exception()
    }

}