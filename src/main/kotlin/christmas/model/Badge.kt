package christmas.model

enum class Badge(private val requiredBenefitAmount: Int) {
    Star(requiredBenefitAmount = 5_000),
    Tree(requiredBenefitAmount = 10_000),
    Santa(requiredBenefitAmount = 20_000);

    companion object {
        fun getOrNullBy(benefitAmount: Int): Badge? {
            val sortedBadges = entries.sortedBy { it.requiredBenefitAmount }
            return sortedBadges.lastOrNull { it.requiredBenefitAmount <= benefitAmount }
        }
    }
}
