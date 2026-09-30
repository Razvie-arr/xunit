package xunit

class WasRun(name: String) : TestCase(name) {

    var wasRun: Boolean = false
    var wasSetUp: Boolean = false

    override fun setUp() {
        wasRun = false
        wasSetUp = true
    }

    fun testMethod() {
        wasRun = true
    }

}