import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class GlobalTest : DescribeSpec({
    val lawrence = User("Lawrence", "Martinez", LocalDate.of(2001, 11, 8))

    val anActivity = Activity("unknown")
    val anExcercice = Excercise(anActivity, 80, 1, 3, 0, 10)
    val anExcercice2 = Excercise(anActivity, 200, 1, 3, 0, 10)
    val anExcercice3 = Excercise(anActivity, 400, 1, 3, 0, 10)

    val anRoutine = Routine("anRutine", lawrence, "unknown")

    describe("Frequency cardiac of training") {
        lawrence.assigPorcentOfTraining(70f)
        it("Fc reserve is 80") {
            lawrence.assignPulse(80)
            lawrence.frequencyCardiacInRepose() shouldBe 80
        }

        it("Freserve is 120") {
            lawrence.maximunFrequencyCardiac() shouldBe 200
            lawrence.frequencyCardiacReserve() shouldBe 120
        }
        it("finally the frequency cardiac of training is 164") {
            lawrence.frequencyCardiacOfTraining() shouldBe 164
        }


    }
    describe("it is sastified") {
        lawrence.enterMuscleForTraining(Muscle.ARMS)
        lawrence.enterMuscleForTraining(Muscle.LEG)
        lawrence.conformanceCriterion = TheConformist()

        it("Test is false because the criterion  return negative ") {
            lawrence.isSatisfied(anRoutine) shouldBe false
            anRoutine.addExcercise(anExcercice)
            lawrence.isSatisfied(anRoutine) shouldBe false

        }
        it("now lawrence is satisfied ") {
            anActivity.addMuscle(Muscle.LEG)
            anActivity.addMuscle(Muscle.ARMS)
            lawrence.isSatisfied(anRoutine) shouldBe true

        }
        it(" the routine is complete for lawrence") {
            anRoutine.isCompletyFor(lawrence) shouldBe true
        }

    }
    describe("The routine is complete and healthy for lawrence") {
        it("frequency reached by lawrence") {

            anRoutine.frequencyCardiacBase() shouldBe 80
            lawrence.frequencyCardiacReserve() shouldBe 120
            anRoutine.frequencyBaseReachedByUser(lawrence) shouldBe 100
        }

        it("the routine is complete for lawrence") {
            anRoutine.isCompletyFor(lawrence) shouldBe true
        }
        it("The routine is not  healthy for lawrence") {
            //FTrining IS includE in the invalter   100 In [120,200]
            anRoutine.isHealthy(lawrence) shouldBe false
        }
        it("The routine is not  complete and healthy for lawrence") {
            anRoutine.isCompleteAndHealthy(lawrence) shouldBe false
        }
        it(
            "if added an excerice whit 200 of frequency base ,the frenquency base reached by lawrence is 130" +
                    "then 130 is  beetwen [120,200] thus anRoutine is complete and  healthy for lawrence"
        ) {
            anRoutine.addExcercise(anExcercice2)
            anRoutine.frequencyBaseReachedByUser(lawrence) shouldBe 130
            anRoutine.isHealthy(lawrence) shouldBe true
            anRoutine.isCompleteAndHealthy(lawrence) shouldBe true
        }
        it("then lawrence can do the routine") {
            lawrence.canDoTheRoutine(anRoutine) shouldBe true
        }
    }
    describe("Lawrence have the TheAthlete criterion") {
        lawrence.conformanceCriterion = TheAthlete()
        it("the frequency base reached by lawrence  is not in the range of 10% of the frequency training") {
            //130 in [147.6,180.4] is false

            lawrence.isSatisfied(anRoutine) shouldBe false
        }
        it("now the frequency base reached by lawrence  is  in the range of 10% of the frequency training"){
            //173 in [147.6,180.4] is true
            anRoutine.addExcercise(anExcercice3)
            lawrence.isSatisfied(anRoutine) shouldBe true

        }

    }
})