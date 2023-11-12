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

    fun printFreebies(countByFreebie: Map<Menu, Int>) {
        printTitle("증정 메뉴")
        if (countByFreebie.isEmpty()) {
            printEmpty()
            println()
            return
        }
        countByFreebie.forEach { (menu, count) ->
            println("${menu.koreanName} ${count}개")
        }
        println()
    }

    fun printBenefits(discountResults: List<DiscountResult>, countByFreebie: Map<Menu, Int>) {
        printTitle("혜택 내역")
        if (discountResults.isEmpty() && countByFreebie.isEmpty()) {
            printEmpty()
            println()
            return
        }
        discountResults.forEach { discountResult ->
            val amount = discountResult.amount
            println("${discountResult.name}: -${amount.toNumberFormat()}원")
        }
        if (countByFreebie.isNotEmpty()) {
            val freebieAndCounts = countByFreebie.toList()
            val freebieAmount = freebieAndCounts.sumOf { it.first.price * it.second }
            println("증정 이벤트: -${freebieAmount.toNumberFormat()}원")
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
        if (badges.isEmpty()) {
            printEmpty()
            return
        }
        badges.forEach { badge ->
            println(badge.koreanName)
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
