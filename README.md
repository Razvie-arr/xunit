# xUnit

This project implements a lightweight unit testing framework in Kotlin,
inspired by Part 2 of Kent Beck's *Test-Driven Development: By Example*.

The original example in the book was written in Python. This version translates
those dynamic ideas into statically typed Kotlin using runtime reflection, while
extending the example with annotation-based discovery, lifecycle hooks,
hierarchical suites, and detailed failure reporting.

## Features

- Annotation-based test discovery via `@Test`
- Per-test lifecycle hooks with `@BeforeEach` and `@AfterEach`
- Hierarchical composite test suites
- Detailed failure tracking with test names, exception types, and messages
- Readable execution summaries

## Project structure

The framework is organized into three focused packages:

- `xunit.annotations`: `@Test`, `@BeforeEach`, and `@AfterEach`
- `xunit.core`: `TestCase`, `TestSuite`, `TestResult`, and `Testable`
- `xunit`: assertion functions such as `assertEquals`, `assertTrue`

## Writing tests

For example, a calculator can be tested like this:

```kotlin
class Calculator {
    fun add(left: Int, right: Int) = left + right
}

class CalculatorTest(name: String) : TestCase(name) {

    private lateinit var calculator: Calculator

    @BeforeEach
    fun beforeEach() {
        calculator = Calculator()
    }

    @Test
    fun addsTwoNumbers() {
        assertEquals(5, calculator.add(2, 3))
    }

    @Test
    fun addsNegativeNumbers() {
        assertEquals(-5, calculator.add(-2, -3))
    }
}
```

### Running One Test

Pass the target method name to instantiate and run an isolated test case:

```kotlin
fun main() {
    val result = TestResult()
    CalculatorTest("addsTwoNumbers").run(result)
    println(result.summary())
}
```

### Running All Tests

Use `TestSuite.from(...)` to automatically discover and execute all methods
annotated with `@Test`:

```kotlin
fun main() {
    val suite = TestSuite.from(CalculatorTest::class)
    val result = TestResult()
    suite.run(result)
    println(result.summary())
}
```

### Test Lifecycle

Methods annotated with `@BeforeEach` run before each test method, while
`@AfterEach` methods run after each test, including when setup or the test
method fails. Use them to prepare and clean up the test fixture.

### Test Summary

`TestResult.summary()` produces diagnostic output:

```text
2 run, 1 failed
addsNegativeNumbers: AssertionError: Expected <-1> but was <-5>
```

The output includes the total run and failure counts, followed by the test
name, exception type, and failure message for each failure.
