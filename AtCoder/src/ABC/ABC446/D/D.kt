@file:OptIn(ExperimentalStdlibApi::class)

package ABC.ABC446.D

import kotlin.math.max
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    D().solve()
}

private class D {
    fun solve() {
        val scanner = FastScanner(System.`in`)
        val n = scanner.nextInt()
        val a = n { scanner.nextInt() }.toList()
        val seq = mutableMapOf<Int, Int>()
        for (i in 0 until n) {
            val target = a[i]
            val prev = seq[target - 1] ?: 0
            seq[target] = max(prev + 1, seq[target] ?: 0)
        }
        val answer = seq.values.max()
        println(answer)
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
