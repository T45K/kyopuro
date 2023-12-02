package AtCoder.ABC.ABC331.E

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
        val l = scanner.nextInt()
        val a = n { scanner.nextInt() }.toList()
        val b = m { scanner.nextInt() }.toList()
        val c = l { scanner.nextInt() to scanner.nextInt() }.toList()

        val mainAmountByIndex = (1..n).associateWith { a[it - 1] }
        val subAmountByIndexSortedByAmount = (1..m).map { it to b[it - 1] }.sortedBy { -it.second }.toMap()

        val prohibitedSubIndexesByMainIndex =
            c.groupBy { it.first }.mapValues { (_, values) -> values.map { it.second }.toSet() }

        val answer = mainAmountByIndex.maxOf { (index, mainAmount) ->
            val prohibitedSubIndexes = prohibitedSubIndexesByMainIndex[index] ?: emptySet()
            subAmountByIndexSortedByAmount.asSequence().firstOrNull { (index, _) -> index !in prohibitedSubIndexes }
                ?.let { mainAmount + it.value }
                ?: 0
        }
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
