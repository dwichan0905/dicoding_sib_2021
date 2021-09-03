fun main() {
    val sequenceNumber = generateSequence(1) { it + 2 }
    sequenceNumber.take(5).forEach { print("$it ") }
}