package app

fun main () {
    val lines = readFile("day3")
    val rucksacks = lines.map {
        it.split("").filter{ a -> a != ""}
    }
    val rucksacksCompartments = lines.map {
        listOf(it.substring(0, it.length / 2).split("").filter { a -> a != ""},
        it.substring(it.length / 2).split("").filter{ a -> a != ""})
    }

    fun getPriority (item: Char): Int {
        return if (item.isUpperCase()) {
            item - "A".toCharArray()[0] + 27
        } else {
            item - "a".toCharArray()[0] + 1
        }
    }

    var compartmentPriority = 0
    rucksacksCompartments.forEach { compartments ->
        run lit@ {
            compartments[0].forEach { item ->
                if (compartments[1].contains(item)) {
                    compartmentPriority += getPriority(item.toCharArray()[0])
                    return@lit
                }
            }
        }
    }
    var groupPriority = 0
    rucksacks.forEachIndexed { index, rucksack ->
        run lit@ {
            rucksack.forEach { item ->
                if (index % 3 != 0) {
                    return@lit
                }
                if (rucksacks[index + 1].contains(item) && rucksacks[index + 2].contains(item)) {
                    groupPriority += getPriority(item.toCharArray()[0])
                    return@lit
                }
            }
        }
    }

    print(compartmentPriority)
    print(groupPriority)
}