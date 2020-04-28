import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileReader


fun parseJson(path: String): RoverData? {
    val bufferedReader = BufferedReader(FileReader(path))
    return Gson().fromJson(bufferedReader, RoverData::class.java)
}


fun readAction(action: Char): Action {
    return when (action) {
        'L' -> Action.RotateLeft
        'R' -> Action.RotateRight
        'M' -> Action.Move
        else -> Action.Unknown
    }
}

fun readDirection(direction: Char): Direction {
    return when (direction) {
        'N' -> Direction.N
        'W' -> Direction.W
        'S' -> Direction.S
        'E' -> Direction.E
        else -> Direction.Unknown
    }
}
