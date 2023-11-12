package christmas.model

enum class Badge(val requiredBenefitAmount: Int) {
    Star(requiredBenefitAmount = 5_000),
    Tree(requiredBenefitAmount = 10_000),
    Santa(requiredBenefitAmount = 20_000)
}
