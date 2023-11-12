package christmas.model

interface Discount {
    val name: String
    fun calculate(): Int
}
