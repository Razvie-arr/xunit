package xunit

fun main() {
    println(TestCaseTest("testTemplateMethod").run().summary())
    println(TestCaseTest("testResult").run().summary())
    println(TestCaseTest("testFailedResult").run().summary())
    println(TestCaseTest("testFailedResultFormatting").run().summary())
}