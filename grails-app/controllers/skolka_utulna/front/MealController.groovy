package skolka_utulna.front

import skolka_utulna.meal.MealMenuService
import frod.routing.domain.Page
import skolka_utulna.MenuItem
import skolka_utulna.Website
import skolka_utulna.MainMenuItem

class MealController {

    MealMenuService mealMenuService

    def index() {
        Date today = new Date()
        Page page = Page.get(params.pageId)
        MainMenuItem mainMenuItem = MainMenuItem.findByPage(page)
        Website website = mainMenuItem.website
        def weeklyMenu = mealMenuService.getWeekMenu(today, website)
//        dump(weeklyMenu)
        [weeklyMenu: weeklyMenu]
    }
}
