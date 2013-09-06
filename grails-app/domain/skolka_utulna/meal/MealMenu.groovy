package skolka_utulna.meal

class MealMenu {

    Date validDate

    String name

    MealMenuType mealMenuType

    static constraints = {
        name(maxSize: 100000)
    }
}
