package christmas

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class ApplicationTest : NsTest() {
    @Test
    fun `모든 타이틀 출력`() {
        assertSimpleTest {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1")
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            )
        }
    }

    @Test
    fun `혜택 내역 없음 출력`() {
        assertSimpleTest {
            run("26", "타파스-1,제로콜라-1")
            assertThat(output()).contains("<혜택 내역>$LINE_SEPARATOR".toString() + "없음")
        }
    }

    @Test
    fun `날짜 예외 테스트`() {
        assertSimpleTest {
            runException("a")
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `주문 예외 테스트`() {
        assertSimpleTest {
            runException("3", "제로콜라-a")
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `주문 메뉴 출력`() {
        assertSimpleTest {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1")
            assertThat(output()).contains(
                "티본스테이크 1개",
                "바비큐립 1개",
                "초코케이크 2개",
                "제로콜라 1개"
            )
        }
    }

    @Test
    fun `모든 혜택 내역 출력`() {
        assertSimpleTest {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1")
            assertThat(output()).contains(
                "크리스마스 디데이 할인: -1,200원",
                "평일 할인: -4,046원",
                "특별 할인: -1,000원",
                "증정 이벤트: -25,000원",
            )
        }
    }

    @Test
    fun `총혜택 금액 출력`() {
        assertSimpleTest {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1")
            assertThat(output()).contains(
                "<총혜택 금액>${LINE_SEPARATOR}-31,246원"
            )
        }
    }

    @Test
    fun `예상 결제 금액 출력`() {
        assertSimpleTest {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1")
            assertThat(output()).contains(
                "<할인 후 예상 결제 금액>$LINE_SEPARATOR" + "135,754원",
            )
        }
    }

    @Test
    fun `이벤트 배지 출력`() {
        assertSimpleTest {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1")
            assertThat(output()).contains(
                "<12월 이벤트 배지>$LINE_SEPARATOR" + "산타",
            )
        }
    }

    override fun runMain() {
        main()
    }

    companion object {
        private val LINE_SEPARATOR = System.lineSeparator()
    }
}
