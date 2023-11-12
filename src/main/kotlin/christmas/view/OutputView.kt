package christmas.view

import christmas.model.Badge
import christmas.model.DiscountResult
import christmas.model.Menu
import java.text.DecimalFormat

import java.text.NumberFormat


class OutputView {
    private fun printTitle(title: String) {
        println("<${title}>")
    }

    private fun printEmpty() {
        println("없음")
    }

    fun printOrderMenus(menuAndCounts: List<Pair<Menu, Int>>) {
        printTitle("주문 메뉴")
        menuAndCounts.forEach {
            val menu = it.first
            val count = it.second
            println("${menu.koreanName} ${count.toNumberFormat()}개")
        }
        println()
    }

    fun printOrderAmount(orderAmount: Int) {
        printTitle("할인 전 총주문 금액")
        println("${orderAmount.toNumberFormat()}원")
        println()
    }

    fun printFreebies(freebies: List<Menu>) {
        printTitle("증정 메뉴")
        freebies.forEach { menu ->
            println("${menu.koreanName} 1개")
        }
        if (freebies.isEmpty()) {
            printEmpty()
        }
        println()
    }

    fun printBenefits(discountResults: List<DiscountResult>, freebies: List<Menu>) {
        printTitle("혜택 내역")
        discountResults.forEach { discountResult ->
            val amount = discountResult.amount
            println("${discountResult.name}: -${amount.toNumberFormat()}원")
        }
        if (freebies.isNotEmpty()) {
            val freebieAmount = freebies.sumOf { it.price }
            println("증정 이벤트: -${freebieAmount.toNumberFormat()}원")
        }
        if (discountResults.isEmpty() && freebies.isEmpty()) {
            printEmpty()
        }
        println()
    }

    fun printBenefitAmount(benefitAmount: Int) {
        printTitle("총혜택 금액")
        println("-${benefitAmount.toNumberFormat()}원")
        println()
    }

    fun printPaymentAmount(paymentAmount: Int) {
        printTitle("할인 후 예상 결제 금액")
        println("${paymentAmount.toNumberFormat()}원")
        println()
    }

    fun printBadges(badges: List<Badge>) {
        printTitle("12월 이벤트 배지")
        badges.forEach { badge ->
            println(badge.koreanName)
        }
        if (badges.isEmpty()) {
            printEmpty()
        }
    }

    fun printInvalidDateError() {
        printError("유효하지 않은 날짜입니다. 다시 입력해 주세요.")
    }

    fun printInvalidOrderError() {
        printError("유효하지 않은 주문입니다. 다시 입력해 주세요.")
    }

    private fun printError(message: String) {
        println("[ERROR] $message")
    }

    private fun Int.toNumberFormat(): String {
        val formatter: NumberFormat = DecimalFormat("#,###")
        return formatter.format(this)
    }

    private val Badge.koreanName: String
        get() = when (this) {
            Badge.Star -> "별"
            Badge.Tree -> "트리"
            Badge.Santa -> "산타"
        }
}
