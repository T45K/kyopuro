@file:OptIn(ExperimentalStdlibApi::class)

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
        val h = scanner.nextInt()
        val w = scanner.nextInt()
        val table = h { scanner.next().toCharArray() }.toTypedArray()

        val directions = setOf('>', 'v', '<', '^')
        val watchableAreas = setOf('.', '$')
        (0..<h).flatMap { x -> (0..<w).map { y -> x to y } }
            .filter { (x, y) -> table[x][y] in directions }
            .forEach { (x, y) ->
                when (table[x][y]) {
                    '>' -> {
                        (y + 1..<w).asSequence()
                            .takeWhile { table[x][it] in watchableAreas }
                            .forEach { table[x][it] = '$' }
                    }

                    'v' -> {
                        (x + 1..<h).asSequence()
                            .takeWhile { table[it][y] in watchableAreas }
                            .forEach { table[it][y] = '$' }
                    }

                    '<' -> {
                        (y - 1 downTo 0).asSequence()
                            .takeWhile { table[x][it] in watchableAreas }
                            .forEach { table[x][it] = '$' }
                    }

                    '^' -> {
                        (x - 1 downTo 0).asSequence()
                            .takeWhile { table[it][y] in watchableAreas }
                            .forEach { table[it][y] = '$' }
                    }
                }
            }

        val start = (0..<h).asSequence()
            .flatMap { x -> (0..<w).asSequence().map { y -> x to y } }
            .find { (x, y) -> table[x][y] == 'S' }!!

        val calcTable = Array(h) { IntArray(w) { -1 } }
        calcTable[start.first][start.second] = 0
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.addLast(start)

        val enteralbe = setOf('.', 'G')
        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            val current = calcTable[x][y]
            if (x >= 1 && calcTable[x - 1][y] < 0 && table[x - 1][y] in enteralbe) {
                queue.addLast(x - 1 to y)
                calcTable[x - 1][y] = current + 1
            }
            if (x < h - 1 && calcTable[x + 1][y] < 0 && table[x + 1][y] in enteralbe) {
                queue.addLast(x + 1 to y)
                calcTable[x + 1][y] = current + 1
            }
            if (y >= 1 && calcTable[x][y - 1] < 0 && table[x][y - 1] in enteralbe) {
                queue.addLast(x to y - 1)
                calcTable[x][y - 1] = current + 1
            }
            if (y < w - 1 && calcTable[x][y + 1] < 0 && table[x][y + 1] in enteralbe) {
                queue.addLast(x to y + 1)
                calcTable[x][y + 1] = current + 1
            }
        }

        val goal = (0..<h).asSequence()
            .flatMap { x -> (0..<w).map { y -> x to y } }
            .find { (x, y) -> table[x][y] == 'G' }!!
        println(calcTable[goal.first][goal.second])
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

    private operator fun <T> Int.invoke(block: (Int) -> T): List<T> = (1..this).map { block(it) }
}
