@file:OptIn(ExperimentalStdlibApi::class)

package ABC.ABC446.C

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    C().solve()
}

private class C {
    fun solve() {
        val scanner = FastScanner(System.`in`)
        val t = scanner.nextInt()
        val answer = t {
            val n = scanner.nextInt()
            val d = scanner.nextInt()
            val a = n { scanner.nextInt() }.toList()
            val b = n { scanner.nextInt() }.toList()
            val eggs = IntArray(n)
            var index = 0
            for (i in 0 until n) {
                eggs[i] += a[i]
                index = moveIndex(eggs, index, b[i])
                if (index <= i - d) {
                    index = i - d + 1
                }
            }
            (index..<n).sumOf { eggs[it] }
        }.joinToString("\n")
        println(answer)
    }

    private fun moveIndex(eggs: IntArray, index: Int, rest: Int): Int {
        return when {
            eggs[index] > rest -> {
                eggs[index] -= rest
                index
            }

            eggs[index] == rest -> {
                index + 1
            }

            else -> {
                moveIndex(eggs, index + 1, rest - eggs[index])
            }
        }
    }

    private class FastScanner(`in`: InputStream) {
        private val reader: BufferedReader = BufferedReader(InputStreamReader(`in`))
        private lateinit var tokenizer: StringTokenizer

        operator fun next(): String {
            if (!::tokenizer.isInitialized || !tokenizer.hasMoreTokens()) {
                tokenizer = StringTokenizer(reader.readLine())
            }
            return tokenizer.nextToken()
        }

        fun nextInt(): Int {
            return next().toInt()
        }

        fun nextLong(): Long {
            return next().toLong()
        }

        fun nextDouble(): Double {
            return next().toDouble()
        }

        fun nextLine(): String {
            return if (!tokenizer.hasMoreTokens()) reader.readLine()
            else tokenizer.nextToken("\n")
        }
    }

    private operator fun <T> Int.invoke(block: () -> T): Sequence<T> = (1..this).asSequence().map { block() }
}
