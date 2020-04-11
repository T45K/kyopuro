package jsutc2020.A

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val s = scanner.nextInt()
    val l = scanner.nextInt()
    val r = scanner.nextInt()

    when {
        s >= r -> {
            println(r)
        }
        s >= l -> {
            println(s)
        }
        else -> {
            println(l)
        }
    }
}
