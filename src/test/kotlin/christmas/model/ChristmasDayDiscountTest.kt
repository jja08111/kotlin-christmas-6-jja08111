package christmas.model

import christmas.model.discount.ChristmasDayDiscount
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate
import java.util.stream.Stream

class ChristmasDayDiscountTest {
    @Test
    fun `12월이 아닌 경우 할인 금액은 0원이다`() {
        val discount = ChristmasDayDiscount(date = LocalDate.of(2023, 11, 25))
        val result = discount.calculate()
        assert(result.amount == 0)
    }

    @Test
    fun `12월이 25일이 지난 경우 할인 금액은 0원이다`() {
        val discount = ChristmasDayDiscount(date = LocalDate.of(2023, 12, 26))
        val result = discount.calculate()
        assert(result.amount == 0)
    }

    @ParameterizedTest
    @MethodSource("generateDiscountAmountTestArguments")
    fun `12월이 1일에서 25일 사이는 할인을 받는다`(day: Int, expectedAmount: Int) {
        val discount = ChristmasDayDiscount(date = LocalDate.of(2023, 12, day))
        val result = discount.calculate()
        assert(expectedAmount == result.amount)
    }

    companion object {
        @JvmStatic
        private fun generateDiscountAmountTestArguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(1, 1_000),
                Arguments.of(2, 1_100),
                Arguments.of(24, 3_300),
                Arguments.of(25, 3_400)
            )
        }
    }
}
