package skolka_utulna.admin

import skolka_utulna.Website
import skolka_utulna.data.AdminMenuFactory
import skolka_utulna.meal.MealMenuService
import skolka_utulna.meal.MealMenu
import skolka_utulna.meal.result.MonthMenu

class MealAdminController {

    MealMenuService mealMenuService

    static namespace = 'admin'

    static layout = "admin"

    def index() {
        Date today = new Date()

        MonthMenu monthMenu = mealMenuService.getMenuForMonth(today.getMonth())
        Website website = Website.findBySlug(params.websiteSlug)
        [website: website, monthMenu: monthMenu]
    }
}
