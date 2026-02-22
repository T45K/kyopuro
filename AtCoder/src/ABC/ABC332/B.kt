package ABC.ABC332

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    B().solve()
}

private class B {
    fun solve() {
        val scanner = FastScanner(System.`in`)
        val k = scanner.nextInt()
        val g = scanner.nextInt()
        val m = scanner.nextInt()

        val (glass, mug) = k.fold(0 to 0) { (currentGlass, currentMug) ->
            when {
                currentGlass == g -> 0 to currentMug
                currentMug == 0 -> currentGlass to m
                else ->
                    if (currentGlass + currentMug <= g) (currentGlass + currentMug) to 0
                    else g to (currentMug - (g - currentGlass))
            }
        }

        println("$glass $mug")
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
    private fun <R> Int.fold(initial: R, operation: (R) -> R) = (1..this).fold(initial) { acc, _ -> operation(acc) }
}
