package xunit

class TestResult {

    private var runCount = 0
    private var errorCount = 0

    fun summary() = "$runCount run, $errorCount failed"

    fun testStarted() = runCount++

    fun testFailed() = errorCount++

}