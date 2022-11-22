package signature

import java.util.*

val scanner = Scanner(System.`in`)
val letters = mapOf(
    'A' to listOf("____", "|__|", "|  |"),
    'B' to listOf("___ ", "|__]", "|__]"),
    'C' to listOf("____", "|__|", "|___"),
    'D' to listOf("___ ", "|  \\", "|__/"),
    'E' to listOf("____", "|___", "|___"),
    'F' to listOf("____", "|___", "|   "),
    'G' to listOf("____", "| __", "|__]"),
    'H' to listOf("_  _", "|__|", "|  |"),
    'I' to listOf("_", "|", "|"),
    'J' to listOf(" _", " |", "_|"),
    'K' to listOf("_  _", "|_/ ", "| \\_"),
    'L' to listOf("_   ", "|   ", "|___"),
    'M' to listOf("_  _", "|\\/|", "|  |"),
    'N' to listOf("_  _", "|\\ |", "| \\|"),
    'O' to listOf("____", "|  |", "|__|"),
    'P' to listOf("___ ", "|__]", "|   "),
    'Q' to listOf("____", "|__|", "|_\\|"),
    'R' to listOf("____", "|__/", "|  \\"),
    'S' to listOf("____", "[__ ", "___]"),
    'T' to listOf("___", " | ", " | "),
    'U' to listOf("_  _", "|  |", "|__|"),
    'V' to listOf("_  _", "|  |", " \\/ "),
    'W' to listOf("_ _ _", "| | |", "|_|_|"),
    'X' to listOf("_  _", " \\/ ", "_/\\_"),
    'Y' to listOf("_   _", " \\_/ ", "  |  "),
    'Z' to listOf("___ ", "  / ", " /__")
)

class ASCIITextSignature {
    fun cmd() {
        print("Enter name and surname: ")
        val fullName = scanner.nextLine()
        print("Enter person's status: ")
        val status = scanner.nextLine()

        val tag = createTag(fullName, status)
        println(tag)
    }

    private fun createTag(fullName: String, status: String): String {
        var tag = ""
        var size = 0
        fullName.uppercase().forEach { size += if (it.isLetter()) letters[it]!![0].length else 6 }
        size += fullName.length + 1
        val pattern = "*".repeat(size + 2) + '\n'
        tag += pattern
        for (i in 0..2) tag += "*  " + fullName.uppercase().map { if (it == ' ') " ".repeat(4) else letters[it]!![i] }
            .joinToString(" ") + "  *" + '\n'
        val centerAlign = size / 2 - status.length / 2
        tag += "*" + " ".repeat(centerAlign) + status.uppercase() + " ".repeat(size - (centerAlign + status.length)) + "*\n" + pattern
        return tag
    }
}

fun main() {
    val signature = ASCIITextSignature()
    signature.cmd()
}
