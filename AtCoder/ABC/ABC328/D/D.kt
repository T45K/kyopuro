@file:OptIn(ExperimentalStdlibApi::class)

package AtCoder.ABC.ABC328.D;

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
        val s = scanner.next()
        val array = Array(s.length) { "" }
        var index = 0
        for (c in s) {
            if (index >= 2 && array[index - 2] == "A" && array[index - 1] == "B" && c == 'C') {
                array[index - 2] = ""
                array[index - 1] = ""
                index -= 2
            } else {
                array[index++] = c.toString()
            }
        }
        println(array.joinToString(""))
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
    }

    private operator fun <T> Int.invoke(block: () -> T): Sequence<T> = (1..this).asSequence().map { block() }
}
