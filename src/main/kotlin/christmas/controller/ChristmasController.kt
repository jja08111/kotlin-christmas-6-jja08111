package christmas.controller

import christmas.model.Order
import christmas.model.event.ChristmasEvent
import christmas.view.InputView
import christmas.view.OutputView
import java.time.DateTimeException
import java.time.LocalDate

class ChristmasController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {
    private fun inputDate(): LocalDate = inputUntilValid(
        onInvalid = outputView::printInvalidDateError
    ) {
        val day = readDay(month = MONTH)
        return try {
            LocalDate.of(YEAR, MONTH, day)
        } catch (e: DateTimeException) {
            throw IllegalArgumentException()
        }
    }

    private fun inputOrder(): Order = inputUntilValid(
        onInvalid = outputView::printInvalidOrderError
    ) {
        val nameAndCounts = readOrder()
        return Order(menuAndCounts = nameAndCounts)
    }

    private inline fun <T> inputUntilValid(onInvalid: () -> Unit, block: InputView.() -> T): T {
        while (true) {
            try {
                return inputView.block()
            } catch (e: IllegalArgumentException) {
                onInvalid()
            }
        }
    }

    private fun OutputView.printEventResult(date: LocalDate, order: Order) {
        val event = ChristmasEvent(order = order, date = date)
        val freebies = event.calculateFreebie()
        printEventResultHeader(date)
        printOrderMenus(order.menuAndCounts)
        printOrderAmount(order.calculateAmount())
        printFreebies(freebies)
        printBenefits(event.calculateDiscount(), freebies)
        printBenefitAmount(event.calculateTotalBenefitAmount())
        printPaymentAmount(event.calculatePaymentAmount())
        printBadges(badges = event.calculateBadge(), month = MONTH)
    }

    fun run() {
        outputView.printWelcomeHeader(month = MONTH)
        val date = inputDate()
        val order = inputOrder()
        outputView.printEventResult(date, order)
    }

    companion object {
        private const val YEAR = 2023
        private const val MONTH = 12
    }
}
