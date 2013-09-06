package skolka_utulna.meal

import skolka_utulna.meal.result.Menu
import skolka_utulna.meal.result.DailyMenu
import skolka_utulna.meal.result.WeekMenu

class MealMenuService {

    def getWeekMenu(Calendar forDate) {
        Calendar monday = forDate.clone()
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

        Calendar friday = forDate.clone()
        friday.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)

        List<MealMenu> allRecords = findAllMealMenus(monday, friday)
        def dailyMenus = []
        def allDays = getAllMenuDays()
        allDays.each {day->
            def recordsForDay = allRecords.findAll { mealMenu ->
                mealMenu.validDate.getDay() == day
            }
            Calendar menuDate = forDate.clone()
            menuDate.set(Calendar.DAY_OF_WEEK, day)
            dailyMenus.add(menuDate, getDailyMenuFromData(recordsForDay))
        }
        return new WeekMenu(dailyMenus: dailyMenus, sinceDate: monday, toDate: friday)
    }

    def getAllMenuDays() {
        return [Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY]
    }

    private def getDailyMenuFromData(Calendar date, List<MealMenu> recordsForTheDay) {
        def allMenuTypes = MealMenuType.list()
        def menus = []
        allMenuTypes.each { type ->
            def foundRecord = recordsForTheDay.find { record->
                record.mealMenuType.id == type.id
            }
            menus.add(new Menu(date: date, name: foundRecord?.name, type: type.name))
        }
        return new DailyMenu(menus: menus)
    }

    def findAllMealMenus(Calendar since, Calendar to) {
        return MealMenu.where {
            validDate >= since && validDate <= to
        }.findAll()
    }
}
