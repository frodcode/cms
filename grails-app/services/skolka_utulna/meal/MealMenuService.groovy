package skolka_utulna.meal

import skolka_utulna.meal.result.Menu
import skolka_utulna.meal.result.DailyMenu
import skolka_utulna.meal.result.WeekMenu
import skolka_utulna.meal.result.MonthMenu
import java.text.SimpleDateFormat
import skolka_utulna.Website

class MealMenuService {

    def getWeekMenu(Date forDate, Website website) {
        Date newDate = new Date(forDate.getTime());

        GregorianCalendar mondayCalendar = new GregorianCalendar();
        mondayCalendar.setTime(forDate);
        mondayCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        mondayCalendar.set(Calendar.HOUR_OF_DAY, 1)
        newDate.setTime(mondayCalendar.getTime().getTime());
        Date monday = new Date()
        monday.setTime(mondayCalendar.getTime().getTime());
        monday = getYesterdayMidnight(monday)

        GregorianCalendar fridayCalendar = new GregorianCalendar();
        fridayCalendar.setTime(monday);
        fridayCalendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        Date friday = new Date()
        friday.setTime(fridayCalendar.getTime().getTime());
        friday = getTodayMidnight(friday)

        List<MealMenu> allRecords = findAllMealMenus(monday, friday, website)
        def dailyMenus = []
        def allDays = getAllMenuDays()
        allDays.each {day->
            def recordsForDay = allRecords.findAll { mealMenu ->
                def calDate = Calendar.getInstance();
                calDate.setTime(mealMenu.validDate);
                calDate.get(Calendar.DAY_OF_WEEK) == day
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
            menus.add(new Menu(date: date, name: foundRecord?.name, type: type.name, typeId: type.id))
        }
        return new DailyMenu(date: date, menus: menus)
    }

    def findAllMealMenus(Date since, Date to, Website website) {
        return MealMenu.where {
            validDate >= since && validDate <= to && website == website
        }.findAll()
    }

    /**
     * 8 pro zari
     * @param monthNumber
     * @return
     */
    public MonthMenu getMenuForMonth(int monthNumber, int yearNumber, Website website)
    {
        Date firstDate = new Date()
        firstDate.setMonth(monthNumber)
        firstDate.setYear(yearNumber)
        firstDate = this.getFirstDateOfMonth(firstDate)

        Calendar c = Calendar.getInstance();
        c.setTime(firstDate);
        c.set(Calendar.DATE, c.getMaximum(Calendar.DATE));
        Date lastDate = c.getTime();

        def weeks = []
        WeekMenu weekMenu = getWeekMenu(firstDate, website)
        WeekMenu currentWeek = weekMenu
        weeks.add(weekMenu)
        def count = 0;
        while (!weekMenuContainsDate(currentWeek, lastDate)) {
            Date firstMonday = this.addDaysToDate(currentWeek.sinceDate, 7)
            currentWeek = getWeekMenu(firstMonday, website)
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
        if (weekMenu.toDate.after(lastDate)) {
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

    private Date getYesterdayMidnight(Date date)  {
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 2)
        Date yesterday = new Date()
        yesterday.setTime(cal.getTime().getTime())
        return yesterday
    }

    private Date getTodayMidnight(Date date) {
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 58)
        Date today = new Date()
        today.setTime(cal.getTime().getTime())
        return today
    }

    private Date getFirstDateOfMonth(Date date) {
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public def saveMenus(List<Menu> menuList, Website website) {
        menuList.each { menu ->
            def mealMenu = findMealMenuForDay(menu.date, menu.typeId, website)
            if (!mealMenu) {
                mealMenu = new MealMenu()
                mealMenu.mealMenuType = MealMenuType.get(menu.typeId)
                mealMenu.validDate = menu.date
                mealMenu.website = website
            }
            mealMenu.name = menu.name
            mealMenu.save(flush:true, failOnError: true)
        }
    }

    def findMealMenuForDay(Date searchByDate, typeId, website) {
        def yesterday = getYesterdayMidnight(searchByDate)
        def todayMidnight = getTodayMidnight(searchByDate)
        def query = MealMenu.where {
            validDate > yesterday && validDate < todayMidnight && website == website && mealMenuType.id == typeId
        }
        return query.find()

    }

}
