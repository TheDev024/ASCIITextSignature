package signature

import java.io.File
import java.util.*

val scanner = Scanner(System.`in`)

class ASCIITextSignature {

    private var mediumFont = mapOf<Char, List<String>>()
    private var romanFont = mapOf<Char, List<String>>()

    init {
        val mediumFile = File("medium.txt")
        val romanFile = File("roman.txt")

        val mediumList = mediumFile.readLines()
        val romanList = romanFile.readLines()

        var i = 1

        while (i < romanList.size) {
            romanFont = romanFont.plus(mapOf(romanList[i][0] to romanList.subList(i + 1, i + 11)))
            i += 11
        }

        i = 1

        while (i < mediumList.size) {
            mediumFont = mediumFont.plus(mapOf(mediumList[i][0] to mediumList.subList(i + 1, i + 4)))
            i += 4
        }

        mediumFont = mediumFont.plus(mapOf(' ' to List(3){ " ".repeat(5) }))
        romanFont = romanFont.plus(mapOf(' ' to List(10){ " ".repeat(10) }))
    }

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
        var fullNameSize = 0
        fullName.forEach { fullNameSize += romanFont[it]!![0].length }
        var statusSize = 0
        status.forEach { statusSize += mediumFont[it]!![0].length }
        val size = maxOf(fullNameSize, statusSize)
        val fullNameAlign = (size - fullNameSize) / 2
        val statusAlign = (size - statusSize) / 2
        val pattern = "8".repeat(size + 8)
        tag += pattern + '\n'
        repeat(10) { j ->
            tag += "88  " + " ".repeat(fullNameAlign) + fullName.map { romanFont[it]!![j] }.joinToString("") + " ".repeat(
                size - (fullNameAlign + fullNameSize)
            ) + "  88" + '\n'
        }
        // tag += ("88  " + " ".repeat(size) + "  88" + '\n').repeat(3)
        repeat(3) { j ->
            tag += "88  " + " ".repeat(statusAlign) + status.map { mediumFont[it]!![j] }.joinToString("") + " ".repeat(
                size - (statusAlign + statusSize)
            ) + "  88" + '\n'
        }
        tag += pattern
        return tag
    }
}

fun main() {
    val signature = ASCIITextSignature()
    signature.cmd()
}
