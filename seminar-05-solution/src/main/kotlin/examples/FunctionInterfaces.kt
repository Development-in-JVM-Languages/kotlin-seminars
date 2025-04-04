package examples

import kotlin.reflect.*

fun printABC() {
    println("ABC")
}

fun double(i: Int): Int = i * 2

fun sum(a: Int, b: Int) = a + b

class A {
    fun methodA() {
        println("some method")
    }
}

inline infix operator fun String.times(times: Int) =
    this.repeat(times)

fun main() {
    val f1 = ::printABC  // u can see the type
    val f2 = ::double
    val f3 = ::sum

    val a = A()
    val f4 = a::methodA
    val f5 = A::methodA  // how can I call f5?

    println(f3(5, 10))  // 15

    // Basic methods
    val f: KFunction<String> = String::times
    println(f.isInline)  // true
    println(f.isExternal)  // false
    println(f.isOperator)  // true
    println(f.isInfix)  // true
    println(f.isSuspend)  // false

    println(f.name)  // times
    println(f.parameters.map { it.name })  // [null, times]
    println(f.returnType)  // kotlin.String
    println(f.typeParameters)  // []
    println(f.visibility)  // PUBLIC
    println(f.isFinal)  // true
    println(f.isOpen)  // false
    println(f.isAbstract)  // false
    println(f.isSuspend)  // false
}
