package christmas.model

class ChampagneFreebieEvent(private val order: Order) : FreebieEvent {

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
