package christmas.model

import christmas.model.discount.WeekendDiscount
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate

class WeekendDiscountTest {
    @ParameterizedTest
    @ValueSource(ints = [3, 4, 5, 7, 31])
    fun `일요일에서 목요일은 할인되지 않는다`(day: Int) {
        val discount = WeekendDiscount(
            date = LocalDate.of(2023, 12, day),
            order = Order(menuAndCounts = listOf(Main.ChristmasPasta to 1))
        )
        val discountAmount = discount.calculate()
        assert(discountAmount == 0)
    }

    @Test
    fun `메인 메뉴 이외에는 할인되지 않는다`() {
        val order = Order(
            menuAndCounts = listOf(
                Appetizer.Tapas to 2,
                Dessert.IceCream to 1,
                Drink.ZeroCola to 1,
            )
        )
        val discount = WeekendDiscount(
            date = LocalDate.of(2023, 12, 2),
            order = order
        )
        val discountAmount = discount.calculate()
        assert(discountAmount == 0)
    }

    @Test
    fun `메인 메뉴 개수와 2,023원을 곱한만큼 할인된다`() {
        val order = Order(menuAndCounts = listOf(Main.BarbecueRips to 3, Main.ChristmasPasta to 2))
        val discount = WeekendDiscount(
            date = LocalDate.of(2023, 12, 2),
            order = order
        )
        val discountAmount = discount.calculate()
        assert(discountAmount == 5 * 2_023)
    }
}
