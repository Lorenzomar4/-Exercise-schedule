import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class TestRuotine : DescribeSpec({
    val anActivity = Activity("unknown").apply {
        addMuscle(Muscle.ABDOMEN)
        addMuscle(Muscle.ARMS)
        addMuscle(Muscle.LEG)
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
        conformanceCriterion = TheConformist()
    }

    val anRoutine = Routine("anRutine", lawrence, "unknown")
    val carmen = User("Carmen", "Jonhson", LocalDate.of(1998, 11, 8)).apply {
        assignPulse(70)
        conformanceCriterion = TheNegate()

    }
    val romina = User("Romina", "Jonhson", LocalDate.of(1998, 11, 8)).apply {
        assignPulse(70)

    }
    val facundo = User("Facundo", "Jonhson", LocalDate.of(1998, 11, 8)).apply {
        assignPulse(70)

    }

    describe("test rutine") {
        it("duration of rutine without excercice") {
            anRoutine.duration() shouldBe 0
        }
        it("if varius exercise are added in the routine the duration is ") {
            anRoutine.addExcercise(anExcercice)
            anRoutine.addExcercise(anExcercice2)
            anRoutine.addExcercise(anExcersiceWithoutRepeatsAndSeries)

            anRoutine.duration() shouldBe 35

        }
        it("if we added three excercice a the rutine the muscle are : ARM ,LEG ,ABDOMEN  ") {
            anRoutine.muscleGroups() shouldBe setOf(Muscle.LEG, Muscle.ARMS, Muscle.ABDOMEN, Muscle.CHEST)
        }
        it("the frequency cardiac base is ") {
            anRoutine.frequencyCardiacBase() shouldBe 80
        }
        it("frequency base reached by lawrence is ") {
            anRoutine.frequencyBaseReachedByUser(lawrence) shouldBe (80 + 127) / 2
        }
    }

    describe("The routine have the criterion friendly") {
        anRoutine.apply {
            criterionOfEdition = CriterionFriendly()
        }
        it("carmen is added a the list of friends of lawrence thus she can then the routine") {
            lawrence.addFriend(carmen)
            anRoutine.canBeEdityBy(carmen) shouldBe true
        }
        it("Lawrence can edit the routine because is the creator of the same") {
            anRoutine.canBeEdityBy(lawrence) shouldBe true
        }
        it("Romina can not edit the routine because she is not friend of lawrence") {
            anRoutine.canBeEdityBy(romina) shouldBe false
        }
    }
    describe("The routine have the criterion Social") {
        anRoutine.apply {
            criterionOfEdition = CriterionSocial()
            addFollower(carmen)
        }
        it("Carmen can edit the routine  because she is a follower") {
            anRoutine.canBeEdityBy(carmen) shouldBe true
        }
        it("but it can not be edit by romina because she is not a follower") {
            anRoutine.canBeEdityBy(romina) shouldBe false
        }
    }
    describe("The routine have the criterion Free") {
        anRoutine.apply {
            criterionOfEdition = CriterionFree()
        }

        it("carmen , lawrece and romina are can editing the routine because the criterion of the same is Free ") {
            anRoutine.canBeEdityBy(carmen) shouldBe true
            anRoutine.canBeEdityBy(lawrence) shouldBe true
            anRoutine.canBeEdityBy(romina) shouldBe true
        }
    }
    describe("The routine have the criterio Fortuitous") {
        anRoutine.apply {
            name = "abc"
            criterionOfEdition = CriterionFortuitous()
        }
        it(
            "the routine cant be edited by carmen because the first letter of her name " +
                    " not start with r j or f and the name of the routine no have a lengh greather that 6"
        ) {
            anRoutine.canBeEdityBy(carmen) shouldBe false
        }
        it("but can be edited by Facundo because his first char of the names is F which is in the list of chars") {
            anRoutine.canBeEdityBy(facundo) shouldBe true
        }
        it("if the routine has more  6 letters then can edited by carmen") {
            anRoutine.name = "GreatherThatSixLetters"
            anRoutine.canBeEdityBy(facundo) shouldBe true

        }
    }
    describe("Testing the method isCompletyFor of routine ") {
        anRoutine.removeAllExcercie()
        anRoutine.addExcercise(anExcercice)

        it("the exercise added include alls muscles thus return true") {
            anRoutine.listOfExcercise.size shouldBe 1
            anRoutine.isCompletyFor(carmen) shouldBe true
        }
        it(
            "but if added exercise  whithout all muscles basic then return false because all exercise must" +
                    "training the three basic muscle and the criterion of she is TheNegate"
        ) {
            anRoutine.addExcercise(anExcercice2)
            anRoutine.isCompletyFor(carmen) shouldBe false
        }
    }
    describe("Testing the method isHealthy of routina") {
        it("is not healthy") { //192-127
            lawrence.assigPorcentOfTraining(1f)
            anRoutine.isHealthy(lawrence) shouldBe false

        }
        it("is healthy ") {
            lawrence.assigPorcentOfTraining(90f)
            anRoutine.isHealthy(lawrence) shouldBe true
            anRoutine.isCompletyFor(lawrence) shouldBe true
        }

    }

})