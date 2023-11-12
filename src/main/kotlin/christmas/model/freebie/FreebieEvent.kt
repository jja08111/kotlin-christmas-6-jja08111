package christmas.model.freebie

import christmas.model.Menu

interface FreebieEvent {
    fun present(): List<Menu>
}
