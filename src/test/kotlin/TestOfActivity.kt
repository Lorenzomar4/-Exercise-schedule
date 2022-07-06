import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TestOfActivity : DescribeSpec({
    val anActivity = Activity("unknown")
    describe("Testing of Activity") {
        it("initially not training the basic group because mising muscles") {
            anActivity.trainingBasicGroup() shouldBe false
        }
        it("if leg, arms and abdomen are added in the list ,then the activity train basic groups ") {
            anActivity.addMuscle(Muscle.LEG)
            anActivity.addMuscle(Muscle.ARMS)
            anActivity.addMuscle(Muscle.ABDOMEN)

            anActivity.trainingBasicGroup() shouldBe true
        }
    }
})