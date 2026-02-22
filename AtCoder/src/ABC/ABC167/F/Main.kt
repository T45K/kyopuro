package ABC.ABC167.F

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
文字列 括弧列の気持ちになる
釣り合っていない括弧列は，その釣り合っている括弧を取り除いて考えても良い
 */
fun main() {
    val scanner = FastScanner(System.`in`)
    val n: Int = scanner.nextInt()
    val list: List<Pair<Int, Int>> = (1..n).map { scanner.next() }
        .map { it.simplify() }
        .filterNot { it.first == 0 && it.second == 0 }
        .toList()

    val former: List<Pair<Int, Int>> = list.filter { it.first >= it.second }
        .sortedBy { it.second }
        .toList()

    val latter: List<Pair<Int, Int>> = list.filter { it.second > it.first }
        .sortedBy { -it.first }
        .toList()

    var left = 0
    var right = 0
    (former + latter)
        .forEach {
            if (it.second < left) {
                left -= it.second
            } else {
                right += it.second - left
                left = 0
            }
            left += it.first
        }

    if (left == 0 && right == 0) {
        println("Yes")
    } else {
        println("No")
    }
}

private fun String.simplify(): Pair<Int, Int> {
    var left = 0
    var right = 0

    this.toCharArray()
        .forEach {
            if (it == '(') {
                left++
            } else {
                if (left > 0) {
                    left--
                } else {
                    right++
                }
            }
        }

    return left to right
}

private class FastScanner(`in`: InputStream) {
    private val reader: BufferedReader = BufferedReader(InputStreamReader(`in`))
    private var tokenizer: StringTokenizer? = null
    operator fun next(): String {
        if (tokenizer == null || !tokenizer!!.hasMoreTokens()) {
            tokenizer = StringTokenizer(reader.readLine())
        }
        return tokenizer!!.nextToken()
    }

    fun nextInt(): Int {
        return next().toInt()
    }
}
