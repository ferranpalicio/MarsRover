import java.lang.Exception

fun main() {

    val roverData: RoverData? = try {
        parseJson("rover_instructions.json")
    } catch (e: Exception) {
        println(e.message)
        null
    }
    val roverDestination: String = roverData?.let { instructions ->
        println(roverData)

        var currentRoverDirection: Direction = readDirection(instructions.roverDirection)
        var currentRoverPosition = instructions.roverPosition

        instructions.movements.forEach {
            val (directionUpdated, positionUpdated) = updateRoverPosition(
                currentRoverDirection,
                currentRoverPosition,
                readAction(it),
                roverData.topRightCorner
            )
            currentRoverDirection = directionUpdated
            currentRoverPosition = positionUpdated
        }
        "${currentRoverPosition.x} ${currentRoverPosition.y} ${currentRoverDirection.value}"

    } ?: "No data received ¯\\_(ツ)_/¯"

    println(roverDestination)

}

private fun updateRoverPosition(
    roverDirection: Direction,
    roverPosition: Coordinate,
    action: Action,
    plateauDimensions: Coordinate
): Pair<Direction, Coordinate> {

    var directionUpdate = roverDirection
    var positionUpdate = Coordinate(roverPosition.x, roverPosition.y)

    when (action) {
        Action.RotateLeft -> {
            directionUpdate = when (roverDirection) {
                Direction.N -> Direction.W
                Direction.E -> Direction.N
                Direction.S -> Direction.E
                Direction.W -> Direction.S
                Direction.Unknown -> {
                    println("Unknown rover direction")
                    directionUpdate
                }
            }
        }

        Action.RotateRight -> {
            directionUpdate = when (roverDirection) {
                Direction.N -> Direction.E
                Direction.E -> Direction.S
                Direction.S -> Direction.W
                Direction.W -> Direction.N
                Direction.Unknown -> {
                    println("Unknown rover direction")
                    directionUpdate
                }
            }
        }

        Action.Move -> {
            when (roverDirection) {
                Direction.N -> if (roverPosition.y < plateauDimensions.y) {
                    positionUpdate = Coordinate(roverPosition.x, roverPosition.y + 1)
                }
                Direction.E -> if (roverPosition.x < plateauDimensions.x) {
                    positionUpdate = Coordinate(roverPosition.x + 1, roverPosition.y)
                }
                Direction.S -> if (roverPosition.y > 0) {
                    positionUpdate = Coordinate(roverPosition.x, roverPosition.y - 1)
                }
                Direction.W -> if (roverPosition.x > 0) {
                    positionUpdate = Coordinate(roverPosition.x - 1, roverPosition.y)
                }
                Direction.Unknown -> println("Unknown rover direction")
            }
        }

        Action.Unknown -> println("Unknown action: ${action.value}")
    }

    return Pair(directionUpdate, positionUpdate)

}