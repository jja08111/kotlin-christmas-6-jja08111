package christmas.model

import christmas.model.discount.WeekdayDiscount
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate

class WeekdayDiscountTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 8, 9, 29, 30])
    fun `금요일, 토요일은 할인되지 않는다`(day: Int) {
        val discount = WeekdayDiscount(
            date = LocalDate.of(2023, 12, day),
            order = Order(menuAndCounts = listOf(Dessert.ChocolateCake to 1))
        )
        val result = discount.calculate()
        assert(result.amount == 0)
    }

    @Test
    fun `디저트 이외에는 할인되지 않는다`() {
        val order = Order(
            menuAndCounts = listOf(
                Appetizer.Tapas to 2,
                Main.ChristmasPasta to 1,
                Drink.ZeroCola to 1,
            )
        )
        val discount = WeekdayDiscount(
            date = LocalDate.of(2023, 12, 5),
            order = order
        )
        val result = discount.calculate()
        assert(result.amount == 0)
    }

    @Test
    fun `디저트는 개수와 2,023원을 곱한만큼 할인된다`() {
        val order = Order(menuAndCounts = listOf(Dessert.IceCream to 3, Dessert.ChocolateCake to 2))
        val discount = WeekdayDiscount(
            date = LocalDate.of(2023, 12, 5),
            order = order
        )
        val result = discount.calculate()
        assert(result.amount == 5 * 2_023)
    }
}
