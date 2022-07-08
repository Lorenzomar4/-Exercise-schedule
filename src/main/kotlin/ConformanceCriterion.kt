import java.time.LocalDate

interface ConformanceCriterion {
    fun canBeFulfilled(user: User, anRoutine: Routine): Boolean
}

class Timer : ConformanceCriterion {

    override fun canBeFulfilled(user: User, anRoutine: Routine) =
        user.daysOfTraining.values.any { anRoutine.equalsOrMoreThatTimeOfRoutine(it) } ||
                anRoutine.duration() >= user.weeklyMinutesOptime()

}

class TheNegate : ConformanceCriterion {
    override fun canBeFulfilled(user: User, anRoutine: Routine) = false

}

class TheConformist : ConformanceCriterion {


    override fun canBeFulfilled(user: User, anRoutine: Routine) =
        user.listOfMuscleTrain.all { anRoutine.trainingTheMuscle(it) }



}
open class TheUndecided : ConformanceCriterion{

    override fun canBeFulfilled(user: User, anRoutine: Routine)=returnCriterion().canBeFulfilled(user,anRoutine)

    fun returnCriterion() =if (numberOftheDay()%2==0) TheConformist() else TheNegate()

    open fun numberOftheDay() = LocalDate.now().dayOfWeek.value

}
class StubTheUndecided (var day : Int)  : TheUndecided(){
    override fun numberOftheDay() = day
}

