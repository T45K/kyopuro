package ABC129

import java.util.*
import kotlin.math.max

// kotlin.math は AtCoder 非対応

fun main() {
    val scanner = Scanner(System.`in`)
    val p = scanner.nextInt()
    val q = scanner.nextInt()
    val r = scanner.nextInt()
    val max = max(p, max(q, r))
    println(p + q + r - max)
}
