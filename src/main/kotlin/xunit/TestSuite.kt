package xunit

class TestSuite {

    private val tests = mutableListOf<TestCase>()

    fun add(test: TestCase) {
        tests.add(test)
    }

    fun run(result: TestResult) {
        for (test in tests) {
            test.run(result)
        }
    }

}