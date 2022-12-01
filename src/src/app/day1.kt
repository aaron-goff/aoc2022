import app.readFile
import app.print

fun main () {
    val calsPerElf = mutableListOf<Int>()
    var elfIndex = 0
    readFile("day1").forEach {
        if (it == "") {
            // Remove whitespace lines
            elfIndex++
        } else if (elfIndex >= calsPerElf.size) {
            calsPerElf.add(it.toInt())
        } else {
            calsPerElf[elfIndex] += it.toInt()
        }
    }

    calsPerElf.sortDescending()
    print("Most cals is ${calsPerElf[0]}")
    print("Top three cals is ${calsPerElf[0] + calsPerElf[1] + calsPerElf[2]}")
}