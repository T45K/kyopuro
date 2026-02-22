package ABC.ABC332

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    A().solve()
}

private class A {
    fun solve() {
        val scanner = FastScanner(System.`in`)
        val n = scanner.nextInt()
        val s = scanner.nextInt()
        val k = scanner.nextInt()
        val pq = n { scanner.nextLong() to scanner.nextLong() }

        val sum = pq.map { (p, q) -> p * q }.sum()
        if (sum >= s) {
            println(sum)
        } else {
            println(sum + k)
        }
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

        fun nextLong(): Long {
            return next().toLong()
        }
    }

    private operator fun <T> Int.invoke(block: () -> T): Sequence<T> = (1..this).asSequence().map { block() }
}
