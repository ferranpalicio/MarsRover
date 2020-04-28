data class RoverData(
    val movements: String,
    val roverDirection: Char,
    val roverPosition: Coordinate,
    val topRightCorner: Coordinate
)

data class Coordinate(
    val x: Int,
    val y: Int
)

sealed class Action(var value: Char) {
    object RotateLeft : Action('L')
    object RotateRight : Action('R')
    object Move : Action('M')
    object Unknown : Action('?')
}

sealed class Direction(var value: Char) {
    object N : Direction('N')
    object E : Direction('E')
    object S : Direction('S')
    object W : Direction('W')
    object Unknown : Direction('?')
}