@file:OptIn(ExperimentalStdlibApi::class)

package ABC.ABC328

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.Deque
import java.util.StringTokenizer
import java.util.function.BiFunction
import java.util.function.Consumer
import java.util.stream.IntStream
import kotlin.math.max
import kotlin.math.min

fun main() {
    E().solve()
}

private class E {
    fun solve() {
        val scanner = FastScanner(System.`in`)
        val n = scanner.nextInt()
        val m = scanner.nextInt()
        val k = scanner.nextLong()
        val edges = m {
            Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextLong())
        }.toList()

        val answer = edges.listCombination(n - 1)
            .map { Graph(n, it) }
            .filter { it.isSpanning() }
            .minOfOrNull { it.calcWeight(k) }

        println(answer)
    }

    // nCk
    private inline fun <reified T> List<T>.listCombination(k: Int): List<List<T>> {
        val n = this.size
        require(k <= n)

        val selectedElements = arrayOfNulls<T?>(k)
        val operation = object : BiFunction<Int, Int, List<List<T>>> {
            override fun apply(index: Int, count: Int): List<List<T>> =
                if (count == k - 1) {
                    (index..<n).map {
                        selectedElements[count] = this@listCombination[it]
                        selectedElements.toList().filterNotNull()
                    }
                } else {
                    val restCount = k - count
                    (index..n - restCount).flatMap {
                        selectedElements[count] = this@listCombination[it]
                        apply(it + 1, count + 1)
                    }
                }
        }
        return operation.apply(0, 0)
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

        fun nextLong(): Long {
            return next().toLong()
        }
    }

    private operator fun <T> Int.invoke(block: () -> T): Sequence<T> = (1..this).asSequence().map { block() }

    private class Graph(
        private val numOfNodes: Int,
        private val edges: List<Edge>,
    ) {
        fun isSpanning(): Boolean {
            val unionFind = UnionFindTree(numOfNodes).apply {
                edges.forEach {
                    unit(it.node1, it.node2)
                }
            }
            return (1..numOfNodes).map { unionFind.getRoot(it) }.distinct().count() == 1
        }

        fun calcWeight(mod: Long): Long = edges.fold(0L) { acc, edge -> (acc + edge.weight) % mod }
    }

    private data class Edge(
        val node1: Int,
        val node2: Int,
        val weight: Long,
    )

    private class UnionFindTree(numOfNodes: Int) {
        private val nodes: IntArray
        private val indices: Deque<Int> = ArrayDeque()

        init {
            nodes = IntStream.rangeClosed(0, numOfNodes).toArray()
        }

        /**
         * 引数のノードが属している木の根を返す．
         *
         * @param nodeNumber ノードの番号
         * @return 根，つまり属している集合の中の一番小さい値
         */
        fun getRoot(nodeNumber: Int): Int {
            val rootNode = nodes[nodeNumber]
            if (rootNode != nodeNumber) {
                indices.add(nodeNumber)
                return getRoot(rootNode)
            }
            val updateRoot = Consumer { index: Int? -> nodes[index!!] = rootNode }
            indices.forEach(updateRoot)
            indices.clear()
            return nodeNumber
        }

        /**
         * 二つのノードが同じ集合に属しているかを判定する．
         *
         * @param nodeA ノード
         * @param nodeB ノード
         * @return 二つのノードが同じ集合に属しているかの判定結果
         */
        fun isSame(nodeA: Int, nodeB: Int): Boolean {
            return getRoot(nodeA) == getRoot(nodeB)
        }

        /**
         * 引数のノードが属する集合を合体させる．
         *
         * @param nodeA ノード
         * @param nodeB ノード
         */
        fun unit(nodeA: Int, nodeB: Int) {
            val rootA = getRoot(nodeA)
            val rootB = getRoot(nodeB)
            nodes[max(rootA.toDouble(), rootB.toDouble()).toInt()] = min(rootA.toDouble(), rootB.toDouble()).toInt()
        }
    }
}
