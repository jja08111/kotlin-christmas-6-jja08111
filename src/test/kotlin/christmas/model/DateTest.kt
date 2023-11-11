package christmas.model

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DateTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 3, 5, 7, 8, 10, 12])
    fun `31일까지 있는 월의 day 경계값 테스트`(month: Int) {
        assertDoesNotThrow {
            Date(year = 2023, month = month, day = 31)
        }
        assertDoesNotThrow {
            Date(year = 2023, month = month, day = 1)
        }
        assertThrows<IllegalArgumentException> {
            Date(year = 2023, month = month, day = 32)
        }
        assertThrows<IllegalArgumentException> {
            Date(year = 2023, month = month, day = 0)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [4, 6, 9, 11])
    fun `30일까지 있는 월의 day 경계값 테스트`(month: Int) {
        assertDoesNotThrow {
            Date(year = 2023, month = month, day = 30)
        }
        assertDoesNotThrow {
            Date(year = 2023, month = month, day = 1)
        }
        assertThrows<IllegalArgumentException> {
            Date(year = 2023, month = month, day = 31)
        }
        assertThrows<IllegalArgumentException> {
            Date(year = 2023, month = month, day = 0)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [4, 8, 104, 396, 400])
    fun `윤년일때 day 경계값 테스트`(year: Int) {
        assertDoesNotThrow {
            Date(year = year, month = 2, day = 29)
        }
        assertThrows<IllegalArgumentException> {
            Date(year = year, month = 2, day = 30)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 5, 100, 401])
    fun `윤년이 아닐때 day 경계값 테스트`(year: Int) {
        assertDoesNotThrow {
            Date(year = year, month = 2, day = 28)
        }
        assertThrows<IllegalArgumentException> {
            Date(year = year, month = 2, day = 29)
        }
    }
}