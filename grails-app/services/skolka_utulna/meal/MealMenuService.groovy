package skolka_utulna.meal

import skolka_utulna.meal.result.Menu
import skolka_utulna.meal.result.DailyMenu
import skolka_utulna.meal.result.WeekMenu

class MealMenuService {

    def getWeekMenu(Date forDate) {
        Date monday = forDate.clone()
        monday.setDate(Calendar.MONDAY)
        monday.setHours(1)

        Date friday = forDate.clone()
        friday.setDate(Calendar.FRIDAY)
        friday.setHours(23)

        List<MealMenu> allRecords = findAllMealMenus(monday, friday)
        def dailyMenus = []
        def allDays = getAllMenuDays()
        allDays.each {day->
            def recordsForDay = allRecords.findAll { mealMenu ->
                mealMenu.validDate.getDate() == day
            }
            dump(day)
            dump(recordsForDay.size())
            Date menuDate = forDate.clone()
            menuDate.setDate(day)
            dailyMenus.add(getDailyMenuFromData(menuDate, recordsForDay))
        }
        return new WeekMenu(dailyMenus: dailyMenus, sinceDate: monday, toDate: friday)
    }

    def getAllMenuDays() {
        return [Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY]
    }

    private def getDailyMenuFromData(Date date, List<MealMenu> recordsForTheDay) {
        def allMenuTypes = MealMenuType.list()
        def menus = []
        allMenuTypes.each { type ->
            def foundRecord = recordsForTheDay.find { record->
                record.mealMenuType.id == type.id
            }
            menus.add(new Menu(date: date, name: foundRecord?.name, type: type.name))
        }
        return new DailyMenu(date: date, menus: menus)
    }

    def findAllMealMenus(Date since, Date to) {
        return MealMenu.where {
            validDate >= since && validDate <= to
        }.findAll()
    }
}
