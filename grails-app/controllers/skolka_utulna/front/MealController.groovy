package skolka_utulna.front

import skolka_utulna.meal.MealMenuService

class MealController {

    MealMenuService mealMenuService

    def index() {
        Date today = new Date()
        def weeklyMenu = mealMenuService.getWeekMenu(today)
//        dump(weeklyMenu)
        [weeklyMenu: weeklyMenu]
    }
}
