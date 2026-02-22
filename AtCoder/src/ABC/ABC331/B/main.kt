package ABC.ABC331.B

import kotlin.math.min
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    B().solve()
}

private class B {
    private companion object {
        private const val S = 6
        private const val M = 8
        private const val L = 12
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun solve() {
        val scanner = FastScanner(System.`in`)
        val n = scanner.nextInt()
        val s = scanner.nextInt()
        val m = scanner.nextInt()
        val l = scanner.nextInt()

        val array = Array(200) {
            when (it) {
                S -> s
                M -> m
                L -> l
                else -> Int.MAX_VALUE
            }
        }
        for (i in array.indices) {
            if (array[i] == Int.MAX_VALUE) {
                continue
            }

            if (i + S < array.size) {
                array[i + S] = min(array[i] + s, array[i + S])
            }
            if (i + M < array.size) {
                array[i + M] = min(array[i] + m, array[i + M])
            }
            if (i + L < array.size) {
                array[i + L] = min(array[i] + l, array[i + L])
            }
        }

        val answer = array.slice(n..<array.size).min()
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
