package christmas.model.discount

import christmas.model.DiscountResult

interface Discount {
    fun calculate(): DiscountResult
}
