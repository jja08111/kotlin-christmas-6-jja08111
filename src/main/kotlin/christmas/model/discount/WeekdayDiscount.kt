package christmas.model.discount

import christmas.model.DiscountResult
import christmas.model.Order
import java.time.DayOfWeek
import java.time.LocalDate

class WeekdayDiscount(
    private val date: LocalDate,
    private val order: Order
) : Discount {

    override fun calculate(): DiscountResult {
        if (date !in DISCOUNTED_DATE_RANGE) {
            return EMPTY_RESULT
        }
        if (!DISCOUNTED_WEEKDAYS.contains(date.dayOfWeek)) {
            return EMPTY_RESULT
        }
        val dessertCount = order.countDessert()
        val amount = DISCOUNT_AMOUNT * dessertCount
        return DiscountResult(
            name = NAME,
            amount = amount
        )
    }

    companion object {
        private const val NAME: String = "평일 할인"
        private val EMPTY_RESULT = DiscountResult(name = NAME, amount = 0)
        private const val DISCOUNT_AMOUNT = 2_023
        private val DISCOUNTED_DATE_RANGE = LocalDate.of(2023, 12, 1)..LocalDate.of(2023, 12, 31)
        private val DISCOUNTED_WEEKDAYS = listOf(
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY
        )
    }
}
