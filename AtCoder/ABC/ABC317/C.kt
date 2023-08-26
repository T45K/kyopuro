import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

// TODO
fun main() {
    C().solve()
}

private class C {
    fun solve() {
        val scanner = FastScanner(System.`in`)
        val n = scanner.nextInt()
        val m = scanner.nextInt()
        val graph = mutableMapOf<Int, Path>()
        for (i in 0..n) {
            val a = scanner.nextInt()
            val b = scanner.nextInt()
            val c = scanner.nextInt()
            graph[a] = Path(a, b, c)
            graph[b] = Path(b, a, c)
        }

        var max = 0
        for (i in 0..n) {
        }
    }

//    private fun

    private data class Path(val from: Int, val to: Int, val distance: Int)

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

        fun nextDouble(): Double {
            return next().toDouble()
        }

        fun nextLine(): String {
            return if (!tokenizer.hasMoreTokens()) reader.readLine()
            else tokenizer.nextToken("\n")
        }
    }
}
