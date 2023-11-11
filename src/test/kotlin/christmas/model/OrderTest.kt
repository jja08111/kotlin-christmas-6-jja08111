package christmas.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class OrderTest {
    @Test
    fun `주문 성공 테스트`() {
        assertDoesNotThrow {
            Order(menuAndCounts = listOf(Main.BarbecueRips to 8, Drink.Champagne to 12))
        }
    }

    @Test
    fun `메뉴가 텅 빈 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            Order(menuAndCounts = emptyList())
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -100])
    fun `메뉴 개수가 1 미만인 경우 예외를 던진다`(count: Int) {
        assertThrows<IllegalArgumentException> {
            Order(menuAndCounts = listOf(Main.BarbecueRips to count))
        }
    }

    @Test
    fun `중복된 메뉴가 존재하면 예외를 던진다`() {
        val duplicatedMenuAndCounts = listOf(
            Main.BarbecueRips to 1,
            Dessert.ChocolateCake to 2,
            Main.BarbecueRips to 3
        )
        assertThrows<IllegalArgumentException> {
            Order(menuAndCounts = duplicatedMenuAndCounts)
        }
    }

    @Test
    fun `음료만 주문한 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            Order(menuAndCounts = listOf(Drink.Champagne to 1, Drink.ZeroCola to 3))
        }
    }

    @Test
    fun `메뉴가 20개를 초과한 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            Order(menuAndCounts = listOf(Main.BarbecueRips to 10, Drink.ZeroCola to 11))
        }
    }
}