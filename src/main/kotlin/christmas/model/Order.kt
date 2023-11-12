package christmas.model

class Order(
    val menuAndCounts: List<Pair<Menu, Int>>
) {
    init {
        val menuCounts = this.menuAndCounts.map { it.second }
        val totalMenuCount = menuCounts.sum()
        require(menuCounts.all { it >= MIN_MENU_COUNT })
        requireUniqueMenus(this.menuAndCounts)
        requireNotOnlyDrink(this.menuAndCounts)
        require(totalMenuCount in TOTAL_MENU_COUNT_RANGE)
    }

    private fun requireUniqueMenus(order: List<Pair<Menu, Int>>) {
        val menus = order.map { it.first }
        val uniqueMenus = menus.toSet()
        require(menus.size == uniqueMenus.size)
    }

    private fun requireNotOnlyDrink(order: List<Pair<Menu, Int>>) {
        require(order.any { it.first !is Drink })
    }

    fun countDessert(): Int = countMenu<Dessert>()

    fun countMain(): Int = countMenu<Main>()

    private inline fun <reified T : Menu> countMenu(): Int {
        return menuAndCounts.sumOf { (menu, count) ->
            if (menu is T) count else 0
        }
    }

    fun calculateAmount(): Int {
        return menuAndCounts.sumOf { (menu, count) -> menu.price * count }
    }

    companion object {
        private const val MIN_MENU_COUNT = 1
        private val TOTAL_MENU_COUNT_RANGE = 1..20
    }
}
