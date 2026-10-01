package xunit

import xunit.core.TestResult
import xunit.core.TestSuite

fun main() {
    val suite = TestSuite.from(TestCaseTest::class)
    val result = TestResult()
    suite.run(result)
    println(result.summary())
}