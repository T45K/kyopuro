package ABC.ABC467

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    E().solve()
}

private class E {
    fun solve() {
        val scanner = FastScanner(System.`in`)
        val n = scanner.nextInt()
        val m = scanner.nextInt()
        val a = n { scanner.nextInt() }.toList()
        val b = (n - 1) { scanner.nextInt() }.toList()

        (0..<m).forEach {
            val aArray = a.toIntArray()
            aArray[0] += it

            val sum = (0..<n - 1).sumOf { i ->
                val ai = aArray[i]
                val ai2 = aArray[i + 1]
                val bi = b[i]
                if ((ai + ai2) % m == bi) {
                    0
                } else {
                    val mod = (m + ai + ai2 - bi) % m
                    aArray[i + 1] += m - mod
                    m - mod
                }
            } + it
            println(it)
            println(sum)
            println()
        }
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
