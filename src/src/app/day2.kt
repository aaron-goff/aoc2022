package app

fun main() {
    val plays = readFile("day2").map {
        it.replace("A", "rock")
            .replace("B", "paper")
            .replace("C", "scissors")
            .trim().split(" ")
    }
    var firstAttemptScore = 0
    var secondAttemptScore = 0
    fun gamePoints(result: String): Int {
        return when (result) {
            "win" -> 6
            "draw" -> 3
            else -> 0
        }
    }

    fun playPoints(play: String): Int {
        return when (play) {
            "rock" -> 1
            "paper" -> 2
            "scissors" -> 3
            else -> 0
        }
    }

    fun getResult(round: List<String>): Int {
        val opp = round[0]
        val me = round[1]
        return when (opp) {
            "rock" -> when (me) {
                "X" -> gamePoints("draw")
                "Y" -> gamePoints("win")
                else -> gamePoints("lose")
            }
            "paper" -> when (me) {
                "Y" -> gamePoints("draw")
                "Z" -> gamePoints("win")
                else -> gamePoints("lose")
            }
            "scissors" -> when (me) {
                "Z" -> gamePoints("draw")
                "X" -> gamePoints("win")
                else -> gamePoints("lose")
            }
            else -> return 0
        }
    }

    fun getPlay(round: List<String>): Int {
        val opp = round[0]
        val me = round[1]
        return when (opp) {
            "rock" -> when (me) {
                "X" -> playPoints("scissors")
                "Y" -> playPoints("rock")
                "Z" -> playPoints("paper")
                else -> 0
            }
            "paper" -> when (me) {
                "X" -> playPoints("rock")
                "Y" -> playPoints("paper")
                "Z" -> playPoints("scissors")
                else -> 0
            }
            "scissors" -> when (me) {
                "X" -> playPoints("paper")
                "Y" -> playPoints("scissors")
                "Z" -> playPoints("rock")
                else -> 0
            }
            else -> 0
        }
    }
    plays.forEach {
        firstAttemptScore += when (it[1]) {
            "X" -> playPoints("rock")
            "Y" -> playPoints("paper")
            else -> playPoints("scissors")
        }
        firstAttemptScore += getResult(it)
        secondAttemptScore += when (it[1]) {
            "Y" -> gamePoints("draw")
            "Z" -> gamePoints("win")
            else -> gamePoints("lose")
        }
        secondAttemptScore += getPlay(it)
    }
    print("Score when trying first way: $firstAttemptScore")
    print("Score when trying second way: $secondAttemptScore")
}