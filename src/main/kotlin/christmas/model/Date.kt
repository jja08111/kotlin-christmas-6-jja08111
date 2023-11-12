package christmas.model

class Date(val year: Int, val month: Int, val day: Int) : Comparable<Date> {
    init {
        val maxDayOfMonth = getMaxDayOf(year = year, month = month)
        require(day in 1..maxDayOfMonth)
    }

    private fun getMaxDayOf(year: Int, month: Int): Int {
        return when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> if (year.isLeapYear()) 29 else 28
            else -> throw IllegalArgumentException()
        }
    }

    private fun Int.isLeapYear(): Boolean {
        return this % 4 == 0 && this % 100 != 0 || this % 400 == 0
    }

    override fun compareTo(other: Date): Int {
        return compareValuesBy(this, other, { it.year }, { it.month }, { it.day })
    }
}
