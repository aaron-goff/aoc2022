package app

import kotlin.math.abs

val cycles = mutableListOf(1)

fun getSignalStrength(cycles: MutableList<Int>, index: Int): Int {
    return index * cycles[index - 1]
}

fun main () {
    val lines = readFile("day10").map { it.split(" ")}
    getCycles(lines)
    val indices = listOf(20, 60, 100, 140, 180, 220)
    var signalStrengthTotal = 0
    indices.forEach {
        val strength = getSignalStrength(cycles, it)
        signalStrengthTotal += strength
    }
    print("Total Signal Strength is $signalStrengthTotal")
    repeat(6) { row ->
        var printString = ""
        repeat(40) { col ->
            val lit = abs(col - cycles[row * 40 + col]) <= 1
            printString += if (lit) "#" else "."
        }
        println(printString)
    }
}

fun getCycles(instructions: List<List<String>>) {
    repeat(240) { index ->
        instructions.forEach {
            completeCycle(it)
        }
    }
}

fun completeCycle(command: List<String>) {
    cycles.add(cycles.last())
    if (command[0] == "addx") {
        cycles.add(cycles.last() + command[1].toInt())
    }
}