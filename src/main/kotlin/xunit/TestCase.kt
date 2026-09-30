package xunit

import kotlin.reflect.full.memberFunctions

open class TestCase(protected val name: String) {

    protected open fun setUp() {}

    protected open fun tearDown() {}

    fun run() {
        setUp()

        val function = this::class.memberFunctions.firstOrNull { it.name == name }
            ?: error("Test method '$name' not found on ${this::class.simpleName}")
        function.call(this)

        tearDown()
    }

}