package library

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

/**
 * java.util.Scanner の高速版
 *
 * インターフェースは Scanner と同じ
 */
class FastScanner(inputStream: InputStream) {
    private val reader: BufferedReader = BufferedReader(InputStreamReader(inputStream))
    private var tokenizer: StringTokenizer = StringTokenizer(reader.readLine())

    fun next(): String =
        (tokenizer.takeIf { it.hasMoreTokens() } ?: update())
            .nextToken()

    fun nextInt(): Int = next().toInt()

    fun nextLong(): Long = next().toLong()

    fun nextDouble(): Double = next().toDouble()

    fun nextLine(): String =
        if (!tokenizer.hasMoreTokens()) {
            reader.readLine()
        } else tokenizer.nextToken("\n")

    private fun update(): StringTokenizer {
        tokenizer = StringTokenizer(reader.readLine())
        return tokenizer
    }
}
