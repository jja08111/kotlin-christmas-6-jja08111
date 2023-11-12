package christmas.model.event

import christmas.model.Badge
import christmas.model.DiscountResult
import christmas.model.Menu

interface Event {
    fun calculateDiscount(): List<DiscountResult>
    fun calculateFreebie(): Map<Menu, Int>
    fun calculateTotalBenefitAmount(): Int
    fun calculateBadge(): List<Badge>
    fun calculatePaymentAmount(): Int
}
