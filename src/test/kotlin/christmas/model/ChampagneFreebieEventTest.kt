package christmas.model

import christmas.model.freebie.ChampagneFreebieEvent
import org.junit.jupiter.api.Test

class ChampagneFreebieEventTest {
    @Test
    fun `주문 금액이 12만원 미만이면 증정품이 없다`() {
        val order = Order(
            menuAndCounts = listOf(
                Appetizer.CaecarSalad to 1,
                Main.TBoneStake to 2
            )
        )
        val champagneFreebieEvent = ChampagneFreebieEvent(order = order)

        val freebies = champagneFreebieEvent.present()

        assert(freebies.isEmpty())
    }

    @Test
    fun `주문 금액이 12만원 이상이면 샴페인 증정품이 있다`() {
        val order = Order(
            menuAndCounts = listOf(
                Main.TBoneStake to 2,
                Dessert.IceCream to 2
            )
        )
        val champagneFreebieEvent = ChampagneFreebieEvent(order = order)

        val freebies = champagneFreebieEvent.present()

        assert(freebies.size == 1)
        assert(freebies[0] == Drink.Champagne)
    }
}
