package christmas.model.event

import christmas.model.DiscountResult
import christmas.model.Menu
import christmas.model.Order
import christmas.model.discount.ChristmasDayDiscount
import christmas.model.discount.Discount
import christmas.model.discount.SpecialDiscount
import christmas.model.discount.WeekdayDiscount
import christmas.model.discount.WeekendDiscount
import christmas.model.freebie.ChampagneFreebie
import christmas.model.freebie.Freebie
import java.time.LocalDate

class ChristmasEvent(order: Order, date: LocalDate) : Event {

    private val canGiveEvent = order.calculateAmount() >= MIN_ORDER_AMOUNT
    private val discounts: List<Discount> = listOf(
        ChristmasDayDiscount(date),
        SpecialDiscount(date),
        WeekdayDiscount(date, order),
        WeekendDiscount(date, order)
    )
    private val freebies: List<Freebie> = listOf(
        ChampagneFreebie(order)
    )

    override fun calculateDiscount(): List<DiscountResult> {
        if (!canGiveEvent) {
            return emptyList()
        }
        return discounts.mapNotNull {
            val discountResult = it.calculate()
            if (discountResult.amount != 0) discountResult else null
        }
    }

    override fun calculateFreebie(): List<Menu> {
        if (!canGiveEvent) {
            return emptyList()
        }
        return freebies.flatMap { it.present() }
    }

    companion object {
        private const val MIN_ORDER_AMOUNT = 10_000
    }
}
