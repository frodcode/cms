package skolka_utulna.meal

import skolka_utulna.meal.result.Menu
import skolka_utulna.meal.result.DailyMenu
import skolka_utulna.meal.result.WeekMenu
import skolka_utulna.meal.result.MonthMenu
import java.text.SimpleDateFormat

class MealMenuService {

    def getWeekMenu(Date forDate) {
        Date newDate = new Date(forDate.getTime());

        GregorianCalendar mondayCalendar = new GregorianCalendar();
        mondayCalendar.setTime(forDate);
        mondayCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        mondayCalendar.set(Calendar.HOUR_OF_DAY, 1)
        newDate.setTime(mondayCalendar.getTime().getTime());
        Date monday = new Date()
        monday.setTime(mondayCalendar.getTime().getTime());

        GregorianCalendar fridayCalendar = new GregorianCalendar();
        fridayCalendar.setTime(monday);
        fridayCalendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        Date friday = new Date()
        friday.setTime(fridayCalendar.getTime().getTime());

        List<MealMenu> allRecords = findAllMealMenus(monday, friday)
        def dailyMenus = []
        def allDays = getAllMenuDays()
        allDays.each {day->
            def recordsForDay = allRecords.findAll { mealMenu ->
                mealMenu.validDate.getDate() == day
            }


            GregorianCalendar menuDateCalendar = new GregorianCalendar();
            menuDateCalendar.setTime(forDate);
            mondayCalendar.set(Calendar.DAY_OF_WEEK, day);
            Date menuDate = new Date();
            menuDate.setTime(mondayCalendar.getTime().getTime());

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

    /**
     * 8 pro zari
     * @param monthNumber
     * @return
     */
    public MonthMenu getMenuForMonth(int monthNumber)
    {
        Date firstDate = new Date()
        firstDate.setMonth(monthNumber)
        firstDate = this.getFirstDateOfMonth(firstDate)

        Calendar c = Calendar.getInstance();
        c.setTime(firstDate);
        c.set(Calendar.DATE, c.getMaximum(Calendar.DATE));
        Date lastDate = c.getTime();

        def weeks = []
        WeekMenu weekMenu = getWeekMenu(firstDate)
        WeekMenu currentWeek = weekMenu
        weeks.add(weekMenu)
        def count = 0;
        while (!weekMenuContainsDate(currentWeek, lastDate)) {
            Date firstMonday = this.addDaysToDate(currentWeek.sinceDate, 7)
            currentWeek = getWeekMenu(firstMonday)
//            dump(currentWeek.sinceDate.format("yyyy-MM-dd"))
//            dump(firstMonday.format("yyyy-MM-dd"))
            weeks.add(currentWeek)
            count++
            if (count > 10) {
                throw new IllegalStateException('Something is wrong inside code. The month cannot have 10 weeks')
            }
        }
        return new MonthMenu(weekMenus: weeks)
    }

    private boolean weekMenuContainsDate(WeekMenu weekMenu, Date lastDate)
    {
        if (weekMenu.sinceDate.before(lastDate) && weekMenu.toDate.after(lastDate)) {
            return true
        }
        return false;
    }

    private Date addDaysToDate(final Date date, int noOfDays) {
        Date newDate = new Date(date.getTime());

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(newDate);
        calendar.add(Calendar.DATE, noOfDays);
        newDate.setTime(calendar.getTime().getTime());

        return newDate;
    }

    private Date getFirstDateOfMonth(Date date) {
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

}
