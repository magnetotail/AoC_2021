import java.io.File

fun main(args: Array<String>) {
    val commands = File("src/main/resources/data").readLines()
    //First subpart
    println(getHorizontalPosition(commands))
    println(getDepth(commands))

    //second subpart
    println(getHorizontalPosition(commands) * getDepth(commands))
    println(getAsOne(commands))
    println(getHorizontalTimesDepth(commands))

    val finalSubmarine = commands.fold(Submarine()) { submarine, command -> submarine.execute(command) }

    println("final position: $finalSubmarine")
    println(finalSubmarine.getHorizontalTimesDepth())
}

fun List<String>.totalDistance(direction: String) = filter { it.contains(direction) }
    .map { it.substringAfter(direction).trim() }
    .sumOf { it.toInt() }


fun getHorizontalTimesDepth(commands: List<String>) =
    with(commands) { totalDistance("forward") * (totalDistance("down") - totalDistance("up")) }

fun getHorizontalPosition(commands: List<String>): Int {
    return commands.filter { it.contains("forward") }
        .map { it.substring("forward ".length) }
        .sumOf { it.toInt() }
}

fun getAsOne(commands: List<String>): Int {
    return commands.filter { it.contains("forward") }
        .map { it.substring("forward ".length) }
        .sumOf { it.toInt() }
        .times(commands.filter { it.contains("down") }
            .map { it.substring("down ".length) }
            .sumOf { it.toInt() }
            .minus(commands.filter { it.contains("up") }
                .map { it.substring("up ".length) }
                .sumOf { it.toInt() }))
}

fun getDepth(commands: List<String>): Int {
    return commands.filter { it.contains("down") }
        .map { it.substring("down ".length) }
        .sumOf { it.toInt() }
        .minus(commands.filter { it.contains("up") }
            .map { it.substring("up ".length) }
            .sumOf { it.toInt() })
}

data class Submarine(val aim: Int = 0, val depth: Int = 0, val x: Int = 0) {

    fun execute(command: String): Submarine =
        when {
            command.contains("up") -> copy(aim = aim - command.substringAfter("up").trim().toInt())
            command.contains("down") -> copy(aim = aim + command.substringAfter("down").trim().toInt())
            else -> {
                val units = command.substringAfter("forward").trim().toInt()
                copy(depth = depth + aim * units, x = x + units)
            }
        }

    fun getHorizontalTimesDepth() = depth * x

    override fun toString(): String {
        return "Submarine(aim=$aim, depth=$depth, x=$x)"
    }

}