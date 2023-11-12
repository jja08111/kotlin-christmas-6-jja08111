package christmas.model

class ChristmasDayDiscount(
    private val date: Date
) : Discount {

    override val name: String = "크리스마스 디데이 할인"

    override fun calculate(): Int {
        if (date !in DISCOUNTED_DATE_RANGE) {
            return 0
        }
        val count = date.day - 1
        return DEFAULT_DISCOUNT_AMOUNT + count * DISCOUNT_INCREASE_AMOUNT
    }

    companion object {
        private const val DEFAULT_DISCOUNT_AMOUNT = 1_000
        private const val DISCOUNT_INCREASE_AMOUNT = 100
        private val DISCOUNTED_DATE_RANGE = Date(2023, 12, 1)..Date(2023, 12, 25)
    }
}
