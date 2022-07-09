import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class CriterionOfConformanceTest : DescribeSpec({
    val anActivity = Activity("unknown").apply {
        addMuscle(Muscle.ABDOMEN)
       // addMuscle(Muscle.ARMS)
        //addMuscle(Muscle.LEG)
    }
    val anActivity2 = Activity("unknown").apply {
        addMuscle(Muscle.CHEST)

    }
    val anExcercice = Excercise(anActivity, 80, 1, 3, 0, 10)
    val anExcercice2 = Excercise(anActivity2, 80, 1, 3, 0, 10)

    val anExcersiceWithoutRepeatsAndSeries = Excercise(
        anActivity2, 80, 3, 0, 20,
        0
    )
    val lawrence = User("Lawrence", "Martinez", LocalDate.of(1998, 11, 8)).apply {
        assignPulse(70)
        conformanceCriterion = Timer()
        toAssignDaysDefault()


    }
    val anRoutine = Routine("anRutine", lawrence, "unknown").apply {
        addExcercise(anExcercice)
        addExcercise(anExcercice2)
        addExcercise(anExcersiceWithoutRepeatsAndSeries)
    }

    val carmen = User("Carmen", "Jonhson", LocalDate.of(1998, 11, 8)).apply {
        assignPulse(70)
    }

    describe("it is testing the criterion of the conformance Timer ") {
        it(
            "initially lawrence have the timer objetive nevertheless cant fulfill it because none day of his  training" +
                    "have a timer equal or  greater than routine"
        ) {
            lawrence.isSatisfied(anRoutine) shouldBe false
        }
        it("if added 36 minutes to a day ,example the day 1 then lawrence can fulfill his objetive "){

            //the time of routine is 35 minutes . 36 >35
            lawrence.asssignDayMinutes(1,36)
            lawrence.isSatisfied(anRoutine) shouldBe true

            //more cases test
            lawrence.asssignDayMinutes(1,35)
            lawrence.isSatisfied(anRoutine) shouldBe true

            lawrence.asssignDayMinutes(1,2)
            lawrence.isSatisfied(anRoutine) shouldBe false

            lawrence.asssignDayMinutes(1,10)
            lawrence.asssignDayMinutes(4,25)
            lawrence.isSatisfied(anRoutine) shouldBe true

            lawrence.toAssignDaysDefault()
            lawrence.isSatisfied(anRoutine) shouldBe false
        }
        it("lawrence haven't days of his training with a time equal or " +
                "greater than routine nevertheless the his time of routine is" +
                " greater than the weekly time optime of lawrence"){
            anExcercice.minuteOfRest=300
            lawrence.isSatisfied(anRoutine) shouldBe true
        }
        it("The routine have time less than weekly time optimo of lawrence thus lawrence cannot fulfill his objective"){
            anExcercice.minuteOfRest=1
            lawrence.isSatisfied(anRoutine) shouldBe false
        }

    }
    describe(" Lawrence have criterion of conformance  negative "){
           lawrence.conformanceCriterion = TheNegate()
            it("if lawrence have criterion of conformance negative then never is isSatisfied "){
                lawrence.isSatisfied(anRoutine) shouldBe  false
            }
    }
    describe("lawrence have the criterion  Conformist"){
            lawrence.conformanceCriterion = TheConformist()
            lawrence.enterMuscleForTraining(Muscle.ARMS)
            lawrence.enterMuscleForTraining(Muscle.LEG)
            it("if the criterion of conformance of lawrence is conformist then initally is not isSatisfied " +
                    "because the routine not training all muscles that interested a lawrence"){
                lawrence.isSatisfied(anRoutine) shouldBe false
            }
            it("if the routine have the muscle that interested a lawrence then he is isSatisfied"){
                anActivity.addMuscle(Muscle.LEG)
                anActivity.addMuscle(Muscle.ARMS)
                lawrence.isSatisfied(anRoutine) shouldBe true
            }

    }
    describe("Lawrence whit criterion undecided"){

        it("The day isn't divisible for two then lawrence isn`t satisfied because the criterion is TheNegate"){
            lawrence.conformanceCriterion = StubTheUndecided(3)
            lawrence.isSatisfied(anRoutine) shouldBe false
        }
        it("The days is divisible for two then lawrence is satisfied because the criterion is Conformist and" +
                " this criterion have all muscles that interested a lawrence"){
            lawrence.conformanceCriterion = StubTheUndecided(4)
            lawrence.isSatisfied(anRoutine) shouldBe true
        }
        it("The day is divisible for two thus his criterion is The conformist nevertheless lawrence" +
                "is not satisfied because the routine no have all muscles that interested a lawrence"){
            anActivity.removeMuscle(Muscle.LEG)
            lawrence.isSatisfied(anRoutine) shouldBe false
        }
    }
    //The TheAthlete test is in the testComplete archive

})