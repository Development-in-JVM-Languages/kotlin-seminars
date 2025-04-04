package examples

import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.superclasses

sealed class UserMessages

private data class UserId(val id: String) {
    companion object {
        val ZERO = UserId("")
    }
}

internal fun interface Filter<T> {
    fun check(value: T): Boolean
}

interface I1
interface I2

open class Parent : I1 {
    val a = 12
    fun b() {}
}
class Child : Parent(), I2 {
    val c = 12
    fun d() {}
}

fun Child.e() {}


fun main() {
    println(UserMessages::class.visibility)  // and many other properties

    println(Child::class.members.map { it.name })  // [c, d, a, b, equals, hashCode, toString]
    println(Child::class.functions.map { it.name })  // [d, b, equals, hashCode, toString]
    println(Child::class.memberProperties.map { it.name })  // [c, a]

    // check declaredMemberProperties

    val p = Parent::class
    val c = Child::class

    println(p.superclasses)  // [class I1, class kotlin.Any]
    println(c.superclasses)  // [class A, class I2]
    println(p.supertypes)  // [I1, kotlin.Any]
    println(c.supertypes)  // [A, I2]
}
