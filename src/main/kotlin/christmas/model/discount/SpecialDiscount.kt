package christmas.model.discount

import java.time.LocalDate

class SpecialDiscount(
    private val date: LocalDate
): Discount {
    override val name: String = "특별 할인"

    override fun calculate(): Int {
        if (!DISCOUNT_DATES.contains(date)) {
            return 0
        }
        return DISCOUNT_AMOUNT
    }

    companion object {
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
