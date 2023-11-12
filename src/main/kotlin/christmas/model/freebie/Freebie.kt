package christmas.model.freebie

import christmas.model.Menu

interface Freebie {
    fun present(): Map<Menu, Int>
}
