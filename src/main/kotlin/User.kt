import java.time.LocalDate
import java.time.Period

class User(
    val name: String,
    val surname: String,
    val dateOfBirth: LocalDate,
) {
    //Testeado
    fun age() = Period.between(dateOfBirth, LocalDate.now()).years


    fun frequencyCardiacInRepose(pulse: Int): Int {
        validatePulseEntered(pulse)
        return pulse

    }

    fun validatePulseEntered(pulse: Int) {
        if (pulse < 60 || pulse > 100) {
            throw pulseOutOflimit("Out of range")
        }
    }

    fun maximunFrequencyCardiac() = 220 - age()
    //Testeado

    fun frequencyCardiacReserve(pulse: Int) = maximunFrequencyCardiac() - frequencyCardiacInRepose(pulse)

}
typealias pulseOutOflimit = java.lang.RuntimeException