@file:OptIn(ExperimentalStdlibApi::class)

package ABC.ABC328.C;

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
        val n = scanner.nextInt()
        val q = scanner.nextInt()
        val s = scanner.next()
        val array = Array(n) { 0 }
        for (i in 1..<n) {
            array[i] = array[i - 1]
            if (s[i - 1] == s[i]) {
                array[i]++
            }
        }
        val answer = q {
            val l = scanner.nextInt()
            val r = scanner.nextInt()
            array[r - 1] - array[l - 1]
        }.joinToString("\n")

        println(answer)
    }

    private class FastScanner(`in`: InputStream) {
        private val reader: BufferedReader
        private lateinit var tokenizer: StringTokenizer

        init {
            reader = BufferedReader(InputStreamReader(`in`))
        }

        operator fun next(): String {
            if (!::tokenizer.isInitialized || !tokenizer.hasMoreTokens()) {
                tokenizer = StringTokenizer(reader.readLine())
            }
            return tokenizer.nextToken()
        }

        fun nextInt(): Int {
            return next().toInt()
        }
    }

    private operator fun <T> Int.invoke(block: () -> T): Sequence<T> = (1..this).asSequence().map { block() }
}
