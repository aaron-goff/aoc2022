package app

fun main() {
    val pairs = readFile("day4").map {
        it.split(",").map { range ->
            range.split("-")[0].toInt()..range.split("-")[1].toInt()
        }
    }
    var contained = 0
    var overlap = 0
    pairs.forEach {
        val first = it[0]
        val second = it[1]
        if ((first.contains(second.first) && first.contains(second.last)) ||
            (second.contains(first.first) && second.contains(first.last))
        ) {
            contained++
        }
        if (first.intersect(second).isNotEmpty()) {
            overlap++
        }
    }
    print("Number of completely contained pairs: $contained")
    print("Number of overlapping pairs: $overlap")
}