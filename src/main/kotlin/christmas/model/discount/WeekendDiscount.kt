package christmas.model.discount

import christmas.model.DiscountResult
import christmas.model.Order
import java.time.DayOfWeek
import java.time.LocalDate

class WeekendDiscount(
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
        val mainCount = order.countMain()
        val amount = DISCOUNT_AMOUNT * mainCount
        return DiscountResult(
            name = NAME,
            amount = amount
        )
    }

    companion object {
        private const val NAME: String = "주말 할인"
        private val EMPTY_RESULT = DiscountResult(name = NAME, amount = 0)
        private const val DISCOUNT_AMOUNT = 2_023
        private val DISCOUNTED_DATE_RANGE = LocalDate.of(2023, 12, 1)..LocalDate.of(2023, 12, 31)
        private val DISCOUNTED_WEEKDAYS = listOf(
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
        )
    }
}
