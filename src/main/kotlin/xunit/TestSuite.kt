package xunit

class TestSuite : Test {

    private val tests = mutableListOf<Test>()

    fun add(test: Test) {
        tests.add(test)
    }

    override fun run(result: TestResult) {
        for (test in tests) {
            test.run(result)
        }
    }

}