package examples

import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty0


val lock: Any = Any()
var str: String = "ABC"

class Box(
    var value: Int = 0
)

fun main() {
    val p1: KProperty0<Any> = ::lock
    println(p1)  // val lock: kotlin.Any
    val p2: KMutableProperty0<String> = ::str
    println(p2)  // var str: kotlin.String
    val p3: KMutableProperty1<Box, Int> = Box::value
    println(p3)  // var Box.value: kotlin.Int

    val box = Box()
    println(p3.get(box))  // 0
    p3.set(box, 999)
    println(p3.get(box))
}
