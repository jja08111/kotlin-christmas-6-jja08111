package christmas.model.discount

import christmas.model.DiscountResult
import java.time.LocalDate

class ChristmasDayDiscount(
    private val date: LocalDate
) : Discount {

    override fun calculate(): DiscountResult {
        if (date !in DISCOUNTED_DATE_RANGE) {
            return EMPTY_RESULT
        }
        val count = date.dayOfMonth - 1
        val amount = DEFAULT_DISCOUNT_AMOUNT + count * DISCOUNT_INCREASE_AMOUNT
        return DiscountResult(
            name = NAME,
            amount = amount
        )
    }

    companion object {
        private const val NAME: String = "크리스마스 디데이 할인"
        private val EMPTY_RESULT = DiscountResult(name = NAME, amount = 0)
        private const val DEFAULT_DISCOUNT_AMOUNT = 1_000
        private const val DISCOUNT_INCREASE_AMOUNT = 100
        private val DISCOUNTED_DATE_RANGE = LocalDate.of(2023, 12, 1)..LocalDate.of(2023, 12, 25)
    }
}
