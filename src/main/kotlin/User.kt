import java.time.LocalDate
import java.time.Period

class User(
    val name: String,
    val surname: String,
    val dateOfBirth: LocalDate,
) {
    val listOfFriends: MutableList<User> = mutableListOf()
    var pulse = 0
    var daysOfTraining: MutableMap<Int, Int> = mutableMapOf()
    var percentageOfTraining = 0f
    val listOfMuscleTrain = mutableListOf<Muscle>()

    fun toAssignDaysDefault() {
        (1..7).forEachIndexed { index, value -> daysOfTraining[value] = 0 }
    }

    fun enterMuscleForTraining(anMuscle: Muscle) {
        listOfMuscleTrain.add(anMuscle)
    }

    fun assigPorcentOfTraining(percentage: Float) {
        validatePorcentEntered(percentage)
        percentageOfTraining = percentage
    }

    fun isVigorous() = percentageOfTraining >= 70
    fun validatePorcentEntered(percentage: Float) {
        val range = (1..100)
        if (percentage.toInt() !in range) {
            throw percentageIncorrect("the percentage is incorrect")
        }
    }

    private fun isOlder() = age() >= 18

    fun weeklyMinutesOptime() = if (isOlder()) 300 else 420

    fun asssignDayMinutes(day: Int, minute: Int) {
        validateDay(day)
        daysOfTraining[day] = minute
    }

    fun validateDay(day: Int) {
        val range = (1..7)
        if (day !in range) {
            throw dayIncorrect("the day entered is incorrect")
        }
    }

    fun getWeeklyMinutes() = daysOfTraining.values.sum()
    fun age() = Period.between(dateOfBirth, LocalDate.now()).years

    fun addFriend(user: User) {
        listOfFriends.add(user)
    }

    fun frequencyCardiacInRepose(): Int {
        validatePulseEntered(pulse)
        return pulse

    }

    fun assignPulse(anPulse: Int) {
        pulse = anPulse
    }

    fun validatePulseEntered(pulse: Int) {
        if (pulse < 60 || pulse > 100) {
            throw pulseOutOflimit("Out of range")
        }
    }

    fun maximunFrequencyCardiac() = 220 - age()
    //tested

    fun frequencyCardiacReserve() = maximunFrequencyCardiac() - frequencyCardiacInRepose()

    fun isFriendOf(user: User) = listOfFriends.contains(user) // is not testing individually

    fun takesThingsSeriously() =
        weeklyMinuteIsGreaterThanOptimeMinutes() || (isVigorous() && verifyThatUserTrainAllDays())


    private fun verifyThatUserTrainAllDays() = daysOfTraining.all { it.value != 0 }

    private fun weeklyMinuteIsGreaterThanOptimeMinutes() = getWeeklyMinutes() > weeklyMinutesOptime()

    fun frequencyCardiacOfTraining()=frequencyCardiacReserve()*(percentageOfTraining/100)  + frequencyCardiacInRepose()

}
typealias pulseOutOflimit = java.lang.RuntimeException
typealias dayIncorrect = java.lang.RuntimeException
typealias percentageIncorrect = java.lang.RuntimeException