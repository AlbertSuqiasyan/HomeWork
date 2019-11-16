import java.lang.Exception

fun main(args: Array<String>) {
    val homeWork = HomeWork()
    homeWork.gameMenu()
}

class HomeWork {
    var numberToGuess: Int = 0
    var userGuess: Int? = 0
    var steps: Int = 0
    var restart: String? = null
    var userDifChoice: String? = null
    var gameStatus: Boolean = true
    var playerWon: Boolean = false
    var isUserGuessNum: Boolean = false

    fun gameMenu() {
        println("Welcome to the GuessGame.Please choose Difficulty by typing one of the following - Easy - Medium - Hard")
        userDifChoice = readLine()
        numberToGuess = (1..100).random()
        when (userDifChoice?.toLowerCase()) {
            "easy" -> {
                gamePlayInitiator(7)
            }
            "medium" -> {
                gamePlayInitiator(5)
            }
            "hard" -> {
                gamePlayInitiator(3)
            }
            else -> {
                println("Please Type a valid value ")
                gameMenu()
            }
        }
    }

    fun gamePlayInitiator(stepsAmount: Int) {
        steps = stepsAmount
        while (userGuess != numberToGuess && steps > 0) {
            steps -= 1
            gamePlay()
        }
        if (steps == 0) {
            playAgain()
        }
    }

    fun gamePlay() {
        isUserGuessNum = false
        while (!isUserGuessNum) {
            println("Please type a number between 1 and 100")
            isUserGuessNum = try {
                userGuess = 0
                userGuess = readLine()?.toInt()
                true
            } catch (e: Exception) {
                println("Not numeric value , Please type Numeric value")
                false
            }
        }
        when (userGuess) {
            numberToGuess -> {
                gameStatus = false
                playerWon = true
                playAgain()
            }
            null -> println("Please Insert a number")
            in 1..100 -> isUserGuessNum = if (userGuess!! > numberToGuess) {
                println("The number you guessed ($userGuess) is higher than the Number that you have to guess, number of steps left $steps")
                false
            } else {
                println("The number you guessed ($userGuess) is lower than the Number that you have to guess, number of steps left $steps")
                false
            }
            else -> {
                println("Please type a valid Value")
            }
        }
    }

    fun playAgain() {
        when (playerWon) {
            true -> {
                println("You guessed the number Right!!! If you want to restart type Y")
                restart()
            }
            else -> {
                println("You ran out of Steps, choose to restart or stop playing via typing Y")
                restart()
            }
        }
    }

    fun restart() {
        restart = readLine()?.toLowerCase()
        if (restart == "y") {
            isUserGuessNum = false
            gameMenu()
        } else {
            println("Good Bye!!!")
        }
    }
}
