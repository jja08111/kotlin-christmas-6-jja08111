package christmas.model.discount

import christmas.model.Order
import java.time.DayOfWeek
import java.time.LocalDate

class WeekdayDiscount(
    private val date: LocalDate,
    private val order: Order
) : Discount {

    override val name: String = "평일 할인"

    override fun calculate(): Int {
        if (date !in DISCOUNTED_DATE_RANGE) {
            return 0
        }
        if (!DISCOUNTED_WEEKDAYS.contains(date.dayOfWeek)) {
            return 0
        }
        val dessertCount = order.countDessert()
        return DISCOUNT_AMOUNT * dessertCount
    }

    companion object {
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
