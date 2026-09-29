package org.example.xunit

class WasRun(name: String) : TestCase(name) {

    var wasRun: Boolean = false

    fun testMethod() {
        wasRun = true
    }

}