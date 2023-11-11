package christmas.view

class OutputView {
    fun printInvalidDateError() {
        printError("유효하지 않은 날짜입니다. 다시 입력해 주세요.")
    }

    private fun printError(message: String) {
        println("[ERROR] $message")
    }
}
