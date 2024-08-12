@file:OptIn(ExperimentalStdlibApi::class)

package AtCoder.ABC.ABC366.B

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
        val s = n { scanner.next() }.toList()
        val m = s.maxOf { it.length }
        val answersWithTailingAsterisk = (0..<m).map { i ->
            (0..<n).reversed().map { j ->
                if (i < s[j].length) {
                    s[j][i]
                } else {
                    '*'
                }
            }.joinToString("")
        }
        answersWithTailingAsterisk.map { removeSuffixAsterisk(it) }
            .forEach { println(it) }
    }

    private tailrec fun removeSuffixAsterisk(s: String): String =
        if (s.last() == '*') removeSuffixAsterisk(s.substring(0, s.length - 1))
        else s

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
    }

    private operator fun <T> Int.invoke(block: () -> T): Sequence<T> = (1..this).asSequence().map { block() }
}
