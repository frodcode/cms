package skolka_utulna.admin

import grails.converters.JSON
import skolka_utulna.Website
import skolka_utulna.data.AdminMenuFactory
import skolka_utulna.meal.MealMenuService
import skolka_utulna.meal.MealMenu
import skolka_utulna.meal.result.MonthMenu
import java.text.SimpleDateFormat
import skolka_utulna.meal.result.Menu

class MealAdminController {

    MealMenuService mealMenuService

    static namespace = 'admin'

    static layout = "admin"

    def index() {
        Website website = Website.findBySlug(params.websiteSlug)
        Date today = new Date()

        def monthNumber = today.getMonth()
        if (params.monthNumber) {
            monthNumber = params.monthNumber.toInteger()
        }
        def yearNumber = today.getYear()
        if (params.yearNumber) {
            yearNumber = params.yearNumber.toInteger()
        }
        Date currentMonth = new Date()
        currentMonth.setYear(yearNumber)
        currentMonth.setMonth(monthNumber)
        MonthMenu monthMenu = mealMenuService.getMenuForMonth(monthNumber, yearNumber, website)

        def nextMonth = addMonth(currentMonth, 1)
        def previousMonth = addMonth(currentMonth, -1)

        [website: website, monthMenu: monthMenu, currentMonth: currentMonth,nextMonth:nextMonth, previousMonth: previousMonth]
    }

    private def addMonth(Date currentMonth, int numOfMonths)
    {
            Date newDate = new Date(currentMonth.getTime());

            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(newDate);
            calendar.add(Calendar.MONTH, numOfMonths);
            newDate.setTime(calendar.getTime().getTime());
            return newDate;
    }

    def detail() {
        Website website = Website.findBySlug(params.websiteSlug)
        Date monday = new SimpleDateFormat("yyyy-MM-dd").parse(params.id);
        def editingWeek = mealMenuService.getWeekMenu(monday, website)
        [website: website, editingWeek: editingWeek, dailyMenuData: editingWeek as JSON]
    }

    def save() {
        Website website = Website.findBySlug(params.websiteSlug)
        def data = request.JSON
        def allMenusData = []
        data.collect{return it.value}.each {
            allMenusData += it.menus
        }
        def menus = allMenusData.collect {menuData ->
            def menuDate = new SimpleDateFormat("yyyy-MM-dd").parse(menuData.date);
            menuDate.setHours(12)
            def name = menuData.name.toString() != 'null' ? menuData.name.toString() : '';
            return new Menu(name: name, type: menuData.type, typeId: menuData.typeId, date: menuDate)
        }
        mealMenuService.saveMenus(menus, website)
        render([status:'ok'] as JSON)
    }

}
