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

    @Test
    fun testMethod() {
        log += "testMethod "
    }

    @Test
    fun testBrokenMethod() {
        throw Exception()
    }

}