package ABC.ABC467

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    D().solve()
}

private class D {
    fun solve() {
        val scanner = FastScanner(System.`in`)
        val t = scanner.nextInt()
        val answer = t {
            val px = scanner.nextLong()
            val py = scanner.nextLong()
            val qx = scanner.nextLong()
            val qy = scanner.nextLong()
            val rx = scanner.nextLong()
            val ry = scanner.nextLong()
            val sx = scanner.nextLong()
            val sy = scanner.nextLong()

            val isSameSlop = (qx - px) * (sy - ry) == (sx - rx) * (qy - py)
            if (!isSameSlop) {
                "Yes"
            } else {
                // 2倍
                val c1x = px + qx
                val c1y = py + qy
                val c2x = rx + sx
                val c2y = ry + sy
                if ((qy - py) * (c2y - c1y) == -1 * (qx - px) * (c2x - c1x)) {
                    "Yes"
                } else {
                    "No"
                }
            }
        }.joinToString("\n")

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
