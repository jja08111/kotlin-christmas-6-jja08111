package christmas.model.freebie

import christmas.model.Menu

interface Freebie {
    fun present(): List<Menu>
}
