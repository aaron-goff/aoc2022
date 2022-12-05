package app

enum class StepInstruction { AMT, FROM, TO }
typealias Step = Map<String, Int>

fun main() {
    val lines = readFile("day5")
    val rawStacks = lines.filter { it.contains("[") }
    val stacksLength = lines.filter { !it.contains("move") && !it.contains("[") && it != "" }[0].last().digitToInt()
    val stacks = MutableList(stacksLength) { mutableListOf<String>() }
    rawStacks.forEach {
        for (i in 0 until stacksLength) {
            if ((i * 4) + 1 < it.length && it[(i * 4) + 1].isLetter()) {
                stacks[i].add(it[(i * 4) + 1].toString())
            }
        }
    }
    val steps: List<Step> = lines.filter { it.contains("move") }.map {
        val splitLine = it.split(" ").filter { item -> item.toIntOrNull() != null }.map { item -> item.toInt() }
        mapOf(
            StepInstruction.AMT.name to splitLine[0],
            StepInstruction.FROM.name to splitLine[1] - 1,
            StepInstruction.TO.name to splitLine[2] - 1
        )
    }
    // deep copy to avoid references to the original stacks variable
    val part1Stacks = stacks.map { it.toMutableList() }
    val part2Stacks = stacks.map { it.toMutableList() }
    steps.forEach {
        for (i in 0 until it[StepInstruction.AMT.name]!!) {
            val toMove = part1Stacks[it[StepInstruction.FROM.name]!!].removeFirst()
            part1Stacks[it[StepInstruction.TO.name]!!].add(0, toMove)
            val toMoveStack = part2Stacks[it[StepInstruction.FROM.name]!!].removeFirst()
            part2Stacks[it[StepInstruction.TO.name]!!].add(i, toMoveStack)
        }
    }
    var part1Tops = ""
    part1Stacks.forEach {
        part1Tops += it[0]
    }
    var part2Tops = ""
    part2Stacks.forEach {
        part2Tops += it[0]
    }
    print("Part 1 Top of the stacks is: $part1Tops")
    print("Part 2 Top of the stacks is: $part2Tops")
}