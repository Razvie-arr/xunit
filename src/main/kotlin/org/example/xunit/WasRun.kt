package org.example.xunit

import kotlin.reflect.full.memberFunctions

class WasRun(val name: String) {

    var wasRun: Boolean = false

    fun run() {
        val function = this::class.memberFunctions.firstOrNull { it.name == name }
            ?: error("Test method '$name' not found on ${this::class.simpleName}")
        function.call(this)
    }

    fun testMethod() {
        wasRun = true
    }

}