package app

fun main() {
    val input = readFile("day6")[0]
    fun checkMarker(index: Int, uniqueLength: Int): Int {
        val list = mutableListOf<Char>()
        for (i in 0 until uniqueLength) {
            list.add(input[index + i])
        }
        return if (list.distinctBy { it }.size == uniqueLength) {
            index + uniqueLength
        } else {
            0
        }
    }

    fun getStart(uniqueLength: Int): Int {
        var result = 0
        run check@ {
            for (i in 0 until input.length - (uniqueLength - 1)) {
                result = checkMarker(i, uniqueLength)
                if (result != 0) {
                    return@check
                }
            }
        }
        return result
    }

    print("Start of packet is at ${getStart(4)}")
    print("Start of message is at ${getStart(14)}")

}