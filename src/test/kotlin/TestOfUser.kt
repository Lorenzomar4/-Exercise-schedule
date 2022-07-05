import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeOneOf
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe
import java.time.LocalDate
import java.time.LocalTime

class TestOfUser : DescribeSpec({

    val lawrence = User("Lawrence", "Martinez", LocalDate.of(1998, 11, 8))

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
            shouldThrow<pulseOutOflimit> { -> lawrence.frequencyCardiacInRepose(20) }
        }
        it("if pulse entered is greater that 60 and less that 100 then the exception is not thrown") {
            lawrence.frequencyCardiacInRepose(70) shouldBe 70
        }
    }
    describe("Testing the maximun frequency cardiac"){
        it("if the ageo of lawrence is 23 then his maximun frequency cardiac is 197 "){
            lawrence.maximunFrequencyCardiac() shouldBe 197
        }
    }
    describe("testing frequency cardiac reserved of lawrence"){
        it("if pulse entered is 70 then his frequency cardiac reserved is 127 "){
            lawrence.frequencyCardiacReserve(70) shouldBe 127
        }
    }

})