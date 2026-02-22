@file:OptIn(ExperimentalStdlibApi::class)

package ABC.ABC446.B

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    B().solve()
}

private class B {
    fun solve() {
        val scanner = FastScanner(System.`in`)
        val n = scanner.nextInt()
        val m = scanner.nextInt()
        val isRetrieved = Array(m + 1) { false }
        val answer = n {
            val l = scanner.nextInt()
            val x = l { scanner.nextInt() }.toList()
            val retrieved = x.firstOrNull { !isRetrieved[it] } ?: 0
            isRetrieved[retrieved] = true
            retrieved
        }.toList().joinToString("\n")
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
