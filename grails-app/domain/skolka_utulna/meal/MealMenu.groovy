package skolka_utulna.meal

import skolka_utulna.Website

class MealMenu {

    Date validDate

    String name

    MealMenuType mealMenuType

    Website website

    static constraints = {
        name(maxSize: 100000)
    }
}
