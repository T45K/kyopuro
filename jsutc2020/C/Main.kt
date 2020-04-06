package jsutc2020.C

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val a1 = scanner.nextInt()
    val a2 = scanner.nextInt()
    val a3 = scanner.nextInt()

    val n = a1 + a2 + a3
    val array = Array(n) { 0 }
    array[0] = 1
    val isUsed = Array(n) { false }
    isUsed[0] = true

    val permutation = mutableListOf<Array<Int>>()
    nextPermutation(n, 1, isUsed, array, permutation)
    var sum = 0
    for (candidate in permutation) {
        if (check(candidate, a1, a2, n)) {
            sum++
        }
    }

    println(sum)
}

private fun check(array: Array<Int>, a1: Int, a2: Int, n: Int): Boolean {
    for (i in 1 until a1) {
        if (array[i] < array[i - 1]) {
            return false
        }
    }
    for (i in a1 until a1 + a2) {
        if (array[i] < array[i - a1] || i > a1 && array[i] < array[i - 1]) {
            return false
        }
    }
    for (i in a1 + a2 until n) {
        if (array[i] < array[i - a2] || i > a1 + a2 && array[i] < array[i - 1]) {
            return false
        }
    }
    return true
}

private fun nextPermutation(n: Int, current: Int, isUsed: Array<Boolean>, array: Array<Int>, list: MutableList<Array<Int>>) {
    if (current == n) {
        list.add(array.copyOf())
        return
    }

    for (i in isUsed.indices) {
        if (isUsed[i]) {
            continue
        }

        array[current] = i + 1
        isUsed[i] = true
        nextPermutation(n, current + 1, isUsed, array, list)
        isUsed[i] = false
    }
}
