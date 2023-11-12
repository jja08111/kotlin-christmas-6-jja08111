package christmas.model

import christmas.model.discount.SpecialDiscount
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate

class SpecialDiscountTest {
    @ParameterizedTest
    @ValueSource(ints = [3, 10, 17, 24, 25, 31])
    fun `특별 할인 일자에는 1,000원이 할인된다`(day: Int) {
        val discount = SpecialDiscount(LocalDate.of(2023, 12, day))
        val result = discount.calculate()
        assert(result.amount == 1_000)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 4, 16, 23, 26, 30])
    fun `특별 할인 일자가 아닌 경우 할인은 없다`(day: Int) {
        val discount = SpecialDiscount(LocalDate.of(2023, 12, day))
        val result = discount.calculate()
        assert(result.amount == 0)
    }
}
