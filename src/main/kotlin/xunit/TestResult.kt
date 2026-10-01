package xunit

class TestResult {

    data class Failure(val testName: String, val cause: Throwable)

    private var runCount = 0
    private val failures = mutableListOf<Failure>()

    fun summary(): String {
        val counts = "$runCount run, ${failures.size} failed"
        if (failures.isEmpty()) {
            return counts
        }

        val details = failures.joinToString("\n") { failure ->
            val cause = failure.cause
            val message = cause.message?.let { ": $it" } ?: ""
            "${failure.testName}: ${cause::class.simpleName}$message"
        }
        return "$counts\n$details"
    }

    fun failures(): List<Failure> = failures.toList()

    fun testStarted() = runCount++

    fun testFailed(testName: String, cause: Throwable) {
        failures.add(Failure(testName, cause))
    }
    
}