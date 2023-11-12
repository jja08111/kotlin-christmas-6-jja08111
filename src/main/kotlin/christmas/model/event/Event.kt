package christmas.model.event

import christmas.model.DiscountResult
import christmas.model.Menu

interface Event {
    fun calculateDiscount(): List<DiscountResult>
    fun calculateFreebie(): List<Menu>
}
