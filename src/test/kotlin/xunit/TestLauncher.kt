package xunit

fun main() {
    val suite = TestSuite.from(TestCaseTest::class)
    val result = TestResult()
    suite.run(result)
    println(result.summary())
}