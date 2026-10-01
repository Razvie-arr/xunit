package xunit

class TestResult {

    data class Failure(val testName: String, val cause: Throwable)

    private var runCount = 0
    private val failures = mutableListOf<Failure>()

    fun summary() = "$runCount run, ${failures.size} failed"

    fun failures(): List<Failure> = failures.toList()

    fun testStarted() = runCount++

    fun testFailed(testName: String, cause: Throwable) {
        failures.add(Failure(testName, cause))
    }
    
}