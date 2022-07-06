import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TestOfExercise : DescribeSpec({

    val anActivity = Activity("unknown")
    val anExcercice = Excercise(anActivity, 80, 1, 3, 0, 10)
    val anExcersiceWithoutRepeatsAndSeries = Excercise(anActivity, 80, 3, 0, 20, 0)

    describe("Excercice whit series and repeats") {

        it("Excercise has same name  that the activity") {
            anExcercice.name() shouldBe anActivity.name
        }

        it("Exercice whit 3 series and 10 repeats has duration of 3") {
            anExcercice.duration() shouldBe 6
        }
    }
    describe("anExcersice Without Repeats And Series") {
        it("the duration is  23 minutes  ") {
            anExcersiceWithoutRepeatsAndSeries.duration() shouldBe 23
        }

    }

})
