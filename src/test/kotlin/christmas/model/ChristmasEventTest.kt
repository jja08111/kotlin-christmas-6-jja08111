package christmas.model

import christmas.model.event.ChristmasEvent
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ChristmasEventTest {
    @Test
    fun `총주문 금액이 10,000원 미만이면 이벤트가 적용되지 않는다`() {
        val date = LocalDate.of(2023, 12, 25)
        val order = Order(
            menuAndCounts = listOf(Appetizer.ButtonMushroomSoup to 1, Drink.ZeroCola to 1)
        )
        val christmasEvent = ChristmasEvent(order = order, date = date)

        val discountResult = christmasEvent.calculateDiscount()

        assert(discountResult.isEmpty())
    }

    @Test
    fun `총주문 금액이 10,000원 이상이면 이벤트가 적용된다`() {
        val date = LocalDate.of(2023, 12, 25)
        val order = Order(menuAndCounts = listOf(Dessert.IceCream to 2))
        val christmasEvent = ChristmasEvent(order = order, date = date)

        val discountResult = christmasEvent.calculateDiscount()

        assert(discountResult.isNotEmpty())
    }
}
