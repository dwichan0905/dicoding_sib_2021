// main function
fun main() {
    val officeOpen = 7
    val now = 20
    val office: String = if (now > officeOpen) {
        "Office already open"
    } else {
        "Office is closed"
    }

    print(office)
}