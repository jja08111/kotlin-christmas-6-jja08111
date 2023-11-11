package christmas.controller

import christmas.model.Date
import christmas.view.InputView
import christmas.view.OutputView

class ChristmasController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {
    private fun inputDate(): Date = inputUntilValid(onInvalid = outputView::printInvalidDateError) {
        val day = readDay()
        return Date(year = YEAR, month = MONTH, day = day)
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

    fun run() {
        inputDate()
    }

    companion object {
        private const val YEAR = 2023
        private const val MONTH = 12
    }
}
