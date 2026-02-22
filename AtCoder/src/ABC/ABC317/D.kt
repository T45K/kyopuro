import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.min
import kotlin.math.sign

fun main() {
    D().solve()
}

private class D {
    fun solve() {
        val scanner = FastScanner(System.`in`)
        val n = scanner.nextInt()
        val constituencies = n {
            val a = scanner.nextInt()
            val b = scanner.nextInt()
            val c = scanner.nextInt()
            Constituency(a, b, c)
        }.toList()
        val sum = constituencies.sumOf { it.seat }

        val dp = Array(n + 1) { LongArray(sum + 1) { Long.MAX_VALUE / 2 } }
        dp[0][0] = 0
        for (i in 1..n) {
            val constituency = constituencies[i - 1]
            if (constituency.isTakahashiWin()) {
                for (j in 0..sum - constituency.seat) {
                    dp[i][j + constituency.seat] = min(dp[i][j + constituency.seat], dp[i - 1][j])
                }
            } else {
                for (j in 0..sum - constituency.seat) {
                    dp[i][j] = min(dp[i][j], dp[i - 1][j])
                    dp[i][j + constituency.seat] = min(dp[i][j + constituency.seat], dp[i - 1][j] + constituency.calcNeedMembers())
                }
            }
        }

        val answer = dp[n].copyOfRange(dp[n].size / 2, dp[n].size).min()
        println(answer)
    }

    private data class Constituency(val takahashi: Int, val aoki: Int, val seat: Int) {
        fun isTakahashiWin(): Boolean = takahashi > aoki

        fun calcNeedMembers(): Int = (takahashi + aoki + 1) / 2 - takahashi
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
