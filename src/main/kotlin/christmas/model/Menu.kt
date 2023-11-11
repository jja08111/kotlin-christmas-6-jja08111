package christmas.model

val allMenus: List<Menu> = Appetizer.entries + Main.entries + Dessert.entries + Drink.entries

fun String.toMenuOrNull(): Menu? {
    return allMenus.firstOrNull { menu -> menu.koreanName == this }
}

interface Menu {
    val koreanName: String
    val price: Int
}

enum class Appetizer(override val koreanName: String, override val price: Int) : Menu {
    ButtonMushroomSoup(koreanName = "양송이수프", price = 6_000),
    Tapas(koreanName = "타파스", price = 5_500),
    CaecarSalad(koreanName = "시저샐러드", price = 8_000)
}

enum class Main(override val koreanName: String, override val price: Int) : Menu {
    TBoneStake(koreanName = "티본스테이크", price = 55_000),
    BarbecueRips(koreanName = "바비큐립", price = 54_000),
    SeafoodPasta(koreanName = "해산물파스타", price = 35_000),
    ChristmasPasta(koreanName = "크리스마스파스타", price = 25_000)
}

enum class Dessert(override val koreanName: String, override val price: Int) : Menu {
    ChocolateCake(koreanName = "초코케이크", price = 15_000),
    IceCream(koreanName = "아이스크림", price = 5_000)
}

enum class Drink(override val koreanName: String, override val price: Int) : Menu {
    ZeroCola(koreanName = "제로콜라", price = 3_000),
    RedWine(koreanName = "레드와인", price = 60_000),
    Champagne(koreanName = "샴페인", price = 25_000)
}
