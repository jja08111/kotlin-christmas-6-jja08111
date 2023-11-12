package christmas.model.freebie

import christmas.model.Drink
import christmas.model.Menu
import christmas.model.Order

class ChampagneFreebie(private val order: Order) : Freebie {

    override fun present(): List<Menu> {
        val orderAmount = order.calculateAmount()
        if (orderAmount < MIN_ORDER_AMOUNT) {
            return emptyList()
        }
        return listOf(Drink.Champagne)
    }

    companion object {
        private const val MIN_ORDER_AMOUNT = 120_000
    }
}
