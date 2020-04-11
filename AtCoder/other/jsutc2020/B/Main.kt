package jsutc2020.B

import java.util.PriorityQueue
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()

    val redQueue = PriorityQueue<Int>()
    val blueQueue = PriorityQueue<Int>()
    for (i in 1..n) {
        val x = scanner.nextInt()
        val c = scanner.next()
        if (c == "R") {
            redQueue.add(x)
        } else {
            blueQueue.add(x)
        }
    }

    while (redQueue.isNotEmpty()) {
        println(redQueue.poll())
    }

    while (blueQueue.isNotEmpty()) {
        println(blueQueue.poll())
    }
}
