package xunit

class TestResult {

    private var runCount = 0

    fun summary() = "$runCount run, 0 failed"

    fun testStarted() = runCount++

}