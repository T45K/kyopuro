@file:OptIn(ExperimentalStdlibApi::class)

package ABC.ABC332

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    D().solve()
}

// TODO: solve
private class D {
    fun solve() {
        val scanner = FastScanner(System.`in`)
        val h = scanner.nextInt()
        val w = scanner.nextInt()

        val a = h { w { scanner.nextInt() }.toList() }.toList()
        val b = h { w { scanner.nextInt() }.toList() }.toList()

        val colSortedA = a.map { it.sorted() }
        val colSortedB = b.map { it.sorted() }

        val rowSortedA = a.transposition().map { it.sorted() }
        val rowSortedB = b.transposition().map { it.sorted() }

        if (colSortedA.sortedBy { it.toString() } != colSortedB.sortedBy { it.toString() } ||
            rowSortedA.sortedBy { it.toString() } != rowSortedB.sortedBy { it.toString() }) {
            println(-1)
            return
        }

        val colCount = countBubbleSort(colSortedA.toTypedArray(), colSortedB.toTypedArray())
        val rowCount = countBubbleSort(rowSortedA.toTypedArray(), rowSortedB.toTypedArray())
        println(colCount + rowCount)
    }

    private fun <T> countBubbleSort(src: Array<T>, dest: Array<T>): Int {
        require(src.size == dest.size)
        var count = 0
        for (i in src.indices.reversed()) {
            if (src[i] == dest[i]) {
                continue
            }

            for (j in (0..<i).reversed()) {
                if (src[j] != dest[i]) {
                    continue
                }
                for (k in j..<i) {
                    src.swap(k, k + 1)
                    count++
                }
                break
            }
        }
        return count
    }

    private fun <T> Array<T>.swap(a: Int, b: Int) {
        val tmp = this[a]
        this[a] = this[b]
        this[b] = tmp
    }

    private fun <T> List<List<T>>.transposition(): List<List<T>> =
        if (this.isEmpty()) this
        else (this[0].indices).map { j ->
            (this.indices).map { i -> this[i][j] }
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
