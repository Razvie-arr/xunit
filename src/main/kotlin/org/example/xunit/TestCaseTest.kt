package org.example.xunit

fun main() {
    val test = WasRun("testMethod")
    println(test.wasRun)
    test.run()
    println(test.wasRun)
}