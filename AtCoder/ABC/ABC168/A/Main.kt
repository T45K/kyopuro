package AtCoder.ABC.ABC168.A

import java.util.Scanner

fun main() {
    Scanner(System.`in`)
        .run {
            when (this.nextInt() % 10) {
                3 -> "bon"
                0, 1, 6, 8 -> "pon"
                else -> "hon"
            }.apply(::println)
        }
}
