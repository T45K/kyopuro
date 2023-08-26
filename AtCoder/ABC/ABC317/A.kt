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
        val h = scanner.nextInt()
        val x = scanner.nextInt()
        n { it to scanner.nextInt() }

        val answer = n { it to scanner.nextInt() }
            .find { it.second + h >= x }!!
            .first
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

    private operator fun <T> Int.invoke(block: (Int) -> T): Sequence<T> = (1..this).asSequence().map { block(it) }
}
