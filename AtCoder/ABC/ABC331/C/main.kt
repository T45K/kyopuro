package AtCoder.ABC.ABC331.C

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
        val a = n { scanner.nextInt() }.toList()

        val sumByElements = a.groupBy { it }.mapValues { (_, values) -> values.sumOf { it.toLong() } }
        val sums = Array(1_000_002) { 0L }
        for (i in 1_000_000 downTo 1) {
            sums[i] = sums[i + 1] + (sumByElements[i + 1] ?: 0)
        }

        val answer = a.map { sums[it] }.joinToString(" ")
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
