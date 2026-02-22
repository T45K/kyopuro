package ABC.ABC331.A

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
        val m = scanner.nextInt()
        val d = scanner.nextInt()
        val year = scanner.nextInt()
        val month = scanner.nextInt()
        val date = scanner.nextInt()

        when {
            date == d && month == m -> println("${year + 1} 1 1")
            date == d -> println("$year ${month + 1} 1")
            else -> println("$year $month ${date + 1}")
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
    }

    private operator fun <T> Int.invoke(block: () -> T): Sequence<T> = (1..this).asSequence().map { block() }
}
