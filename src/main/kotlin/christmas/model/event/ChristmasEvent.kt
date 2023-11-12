package christmas.model.event

import christmas.model.Badge
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

    private val orderAmount = order.calculateAmount()
    private val discounts: List<Discount> = listOf(
        ChristmasDayDiscount(date),
        SpecialDiscount(date),
        WeekdayDiscount(date, order),
        WeekendDiscount(date, order)
    )
    private val freebies: List<Freebie> = listOf(
        ChampagneFreebie(order)
    )

    private val canGiveEvent
        get() = orderAmount >= MIN_ORDER_AMOUNT

    override fun calculateDiscount(): List<DiscountResult> {
        if (!canGiveEvent) {
            return emptyList()
        }
        return discounts.mapNotNull {
            val discountResult = it.calculate()
            if (discountResult.amount > 0) discountResult else null
        }
    }

    override fun calculateFreebie(): Map<Menu, Int> {
        if (!canGiveEvent) {
            return emptyMap()
        }
        var result = mapOf<Menu, Int>()
        freebies.forEach { freebie ->
            val countByMenu = freebie.present()
            result = result.sum(countByMenu)
        }
        return result
    }

    private fun <K> Map<K, Int>.sum(other: Map<K, Int>): Map<K, Int> {
        return (this.toList() + other.toList())
            .groupBy(keySelector = { it.first }, valueTransform = { it.second })
            .map { (key, values) -> key to values.sum() }
            .toMap()
    }

    override fun calculateTotalBenefitAmount(): Int {
        val discount = calculateDiscount()
        val freebie = calculateFreebie()
        var amount = discount.sumOf { it.amount }
        freebie.forEach { (menu, count) ->
            amount += menu.price * count
        }
        return amount
    }

    override fun calculateBadge(): List<Badge> {
        val totalBenefitAmount = calculateTotalBenefitAmount()
        val badges = Badge.entries
        val badge = badges.lastOrNull {
            it.requiredBenefitAmount <= totalBenefitAmount
        } ?: return emptyList()
        return listOf(badge)
    }

    override fun calculatePaymentAmount(): Int {
        val discountResults = calculateDiscount()
        return orderAmount - discountResults.sumOf { it.amount }
    }

    companion object {
        private const val MIN_ORDER_AMOUNT = 10_000
    }
}
