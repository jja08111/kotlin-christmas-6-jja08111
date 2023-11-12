package christmas.model.freebie

import christmas.model.Drink
import christmas.model.Menu
import christmas.model.Order

class ChampagneFreebie(private val order: Order) : Freebie {

    override fun present(): Map<Menu, Int> {
        val orderAmount = order.calculateAmount()
        if (orderAmount < MIN_ORDER_AMOUNT) {
            return emptyMap()
        }
        return mapOf(Drink.Champagne to 1)
    }

    companion object {
        private const val MIN_ORDER_AMOUNT = 120_000
    }
}
