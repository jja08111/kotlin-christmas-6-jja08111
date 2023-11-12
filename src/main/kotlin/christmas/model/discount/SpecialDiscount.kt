package christmas.model.discount

import christmas.model.DiscountResult
import java.time.LocalDate

class SpecialDiscount(
    private val date: LocalDate
) : Discount {

    override fun calculate(): DiscountResult {
        if (!DISCOUNT_DATES.contains(date)) {
            return EMPTY_RESULT
        }
        return DiscountResult(amount = DISCOUNT_AMOUNT, name = NAME)
    }

    companion object {
        private const val NAME: String = "특별 할인"
        private val EMPTY_RESULT = DiscountResult(name = NAME, amount = 0)
        private const val DISCOUNT_AMOUNT = 1_000
        private val DISCOUNT_DATES = listOf(
            LocalDate.of(2023, 12, 3),
            LocalDate.of(2023, 12, 10),
            LocalDate.of(2023, 12, 17),
            LocalDate.of(2023, 12, 24),
            LocalDate.of(2023, 12, 25),
            LocalDate.of(2023, 12, 31)
        )
    }
}
