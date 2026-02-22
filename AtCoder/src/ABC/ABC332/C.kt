package ABC.ABC332

import kotlin.math.max
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
        val m = scanner.nextInt()
        val s = scanner.next()
        var usedTShirtCount = 0
        var currentNecessaryTShirtCount = 0
        var maxNecessaryTShirtCount = 0
        for (c in s) {
            when (c) {
                '0' -> {
                    usedTShirtCount = 0
                    currentNecessaryTShirtCount = 0
                }

                '1' -> {
                    if (usedTShirtCount < m) {
                        usedTShirtCount++
                    } else {
                        currentNecessaryTShirtCount++
                    }
                }

                '2' -> {
                    currentNecessaryTShirtCount++
                }
            }
            maxNecessaryTShirtCount = max(maxNecessaryTShirtCount, currentNecessaryTShirtCount)
        }

        println(maxNecessaryTShirtCount)
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
