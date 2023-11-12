package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.model.Menu
import christmas.model.toMenuOrNull

class InputView {
    fun readDay(month: Int): Int {
        println("${month}월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        val input = Console.readLine()
        return input.toIntOrNull() ?: throw IllegalArgumentException()
    }

    fun readOrder(): List<Pair<Menu, Int>> {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        val input = Console.readLine()
        val rawMenus = input.split(ORDER_DELIMITER)
        return rawMenus.map { rawMenu ->
            val splitted = rawMenu.split(MENU_AND_COUNT_DELIMITER)
            require(splitted.size == 2)
            val menuName = splitted[0].toMenuOrNull() ?: throw IllegalArgumentException()
            val count = splitted[1].toIntOrNull() ?: throw IllegalArgumentException()
            return@map menuName to count
        }
    }

    companion object {
        private const val ORDER_DELIMITER = ","
        private const val MENU_AND_COUNT_DELIMITER = "-"
    }
}
