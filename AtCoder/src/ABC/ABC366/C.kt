@file:OptIn(ExperimentalStdlibApi::class)

package ABC.ABC366.C

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
        val q = scanner.nextInt()

        val balls = Array(1_000_001) { 0 }
        var numberOfKinds = 0
        for (i in 0..<q) {
            when (scanner.nextInt()) {
                1 -> {
                    val x = scanner.nextInt()
                    balls[x]++
                    if (balls[x] == 1) {
                        numberOfKinds++
                    }
                }

                2 -> {
                    val x = scanner.nextInt()
                    balls[x]--
                    if (balls[x] == 0) {
                        numberOfKinds--
                    }
                }

                3 -> {
                    println(numberOfKinds)
                }
            }
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
