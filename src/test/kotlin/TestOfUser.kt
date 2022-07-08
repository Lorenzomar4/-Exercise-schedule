import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.LocalDate

class TestOfUser : DescribeSpec({

    val lawrence = User("Lawrence", "Martinez", LocalDate.of(1998, 11, 8))
    val lawrence2 = User("Lawrence", "Martinez", LocalDate.of(1998, 11, 8))

    val maria = User("Maria", "Martinez", LocalDate.of(2010, 11, 8))

    describe("testing that age of lawrence be 23 years") {
        it("testing that age of lawrence be 23 years") {
            lawrence.age() shouldBe 23
        }
        it("thus the age of lawrence is not 24 years") {
            lawrence.age() shouldNotBe 24
        }
    }
    describe("testing frenquency cardiac at repose") {
        it("is entered one frequency incorrect thus an exception is thrown") {
            lawrence.assignPulse(20)
            shouldThrow<pulseOutOflimit> { -> lawrence.frequencyCardiacInRepose() }
        }
        it("if pulse entered is greater that 60 and less that 100 then the exception is not thrown") {
            lawrence.assignPulse(70)
            lawrence.frequencyCardiacInRepose() shouldBe 70
        }
    }
    describe("Testing the maximun frequency cardiac") {
        it("if the ageo of lawrence is 23 then his maximun frequency cardiac is 197 ") {
            lawrence.maximunFrequencyCardiac() shouldBe 197
        }
    }
    describe("testing frequency cardiac reserved of lawrence") {
        it("if pulse entered is 70 then his frequency cardiac reserved is 127 ") {
            lawrence.frequencyCardiacReserve() shouldBe 127
        }
    }
    describe(" test days") {
        it("days assign of  days of user") {
            lawrence.toAssignDaysDefault()
            lawrence.daysOfTraining[1] shouldBe 0
            lawrence.daysOfTraining[7] shouldBe 0

        }
        it("it is testing that the day enteres is incorrect") {
            shouldThrow<dayIncorrect> { -> lawrence.asssignDayMinutes(8, 60) }
        }
        it("if initially testing the minutes weekly the result is 0") {
            lawrence.getWeeklyMinutes() shouldBe 0
        }
        it("it is added minutes in the days then the minutes of the weekly should be 105") {
            lawrence.asssignDayMinutes(1, 60)
            lawrence.asssignDayMinutes(2, 45)
            lawrence.getWeeklyMinutes() shouldBe 105

        }
        it(
            "it is testing if the minutes optime for lawrence. The result should be  320" +
                    " because lawrence is older of age"
        ) {
            lawrence.weeklyMinutesOptime() shouldBe 300

        }
        it("maria isn't older thus her weekly minutes optime should be  420") {
            maria.weeklyMinutesOptime() shouldBe 420
        }
        it("it is testing that the percentage entered is incorrect. ") {
            shouldThrow<percentageIncorrect> { -> lawrence.assigPorcentOfTraining(106f) }
        }
        it("if the percentage is greater of equal  that 70 then  is vigorous") {
            lawrence.assigPorcentOfTraining(70f)
            lawrence.isVigorous() shouldBe true
        }
        it("if the percentage entered is less than 70 then isn't vigorous ") {
            lawrence.assigPorcentOfTraining(50f)
            lawrence.isVigorous() shouldBe false
        }

    }
    describe(" if takes Things Seriously testing") {
        it("lawrence is not takes seriusly the training because his minutes of training is less that minutes optime") {
            // the weekly minutes of lawrence initially is 105 and the optime Minute of training is 300 because the age
            // of lawrence is greather that 18 years. 105<300
            lawrence.takesThingsSeriously() shouldBe false
        }
        it("the minutes of day 1 is modified for that the weekly minute is greater that minute of training  optime  ") {
            // only yes => weeklyMinuteIsGreaterThanOptimeMinutes()              ||  not => (isVigorous() && verifyThatUserTrainAllDays())
            lawrence.asssignDayMinutes(1, 300)
            lawrence.takesThingsSeriously() shouldBe true
            lawrence.asssignDayMinutes(1, 60) //--
        }
        it(
            "a high percentage is assign to lawrence so that he is vigorous and now all every day have minutes ,then" +
                    "lawrence train all days"
        ) {

            lawrence.assigPorcentOfTraining(80f)
            lawrence.asssignDayMinutes(3, 10)
            lawrence.asssignDayMinutes(4, 10)
            lawrence.asssignDayMinutes(6, 10)
            lawrence.asssignDayMinutes(5, 10)
            lawrence.asssignDayMinutes(7, 10)

            lawrence.takesThingsSeriously() shouldBe true
        }
    }
    describe("Frequency cardiac of training"){
        it("if the frequency cardiac in reserve is 127" +
                "the percentage of training is 80% and" +
                "the pulse in repose is 70, the frequency cardiac of training is 171.6"){

            lawrence.frequencyCardiacOfTraining() shouldBe 171.6f
        }

    }



})