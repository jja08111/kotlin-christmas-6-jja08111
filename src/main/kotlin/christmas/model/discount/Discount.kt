package christmas.model.discount

interface Discount {
    val name: String
    fun calculate(): Int
}
