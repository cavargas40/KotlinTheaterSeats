import java.math.BigDecimal

interface BookingManager {

    val version: String

    fun isSeatFree(seat: Seat): Boolean
    fun reserveSeat(seat: Seat, customerID: Long): Boolean

    fun systemStatus() = "Al Operations are Functional"
}

class UnauthorizedUserException : Throwable()

open class BasicBookingManager(authorizationKey: String) : BookingManager {

    override val version = "1.0"

    override fun isSeatFree(seat: Seat) = true
    override fun reserveSeat(seat: Seat, customerID: Long) = false

    init {
        if (authorizationKey != "12345") throw UnauthorizedUserException()
    }
}

class AdvanceBookingManager : BasicBookingManager("12345"), java.io.Closeable {

    override val version = "2.0"

    fun howManyBookings() = 10

    override fun close() {}
}

//adding function to existing class
fun String.toSentenceCase(): String =
    this[0].toUpperCase() + this.substring(1)


fun main(args: Array<String>) {
    println(AdvanceBookingManager().isSeatFree(Seat(1, 1, BigDecimal.ZERO, "")))

    val greeting = "welcome to the system"

    println(greeting.toSentenceCase())
}