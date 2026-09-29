package org.example.xunit

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but was <$actual>")
    }
}

fun assertTrue(actual: Boolean) = assertEquals(true, actual)

fun assertFalse(actual: Boolean) = assertEquals(false, actual)