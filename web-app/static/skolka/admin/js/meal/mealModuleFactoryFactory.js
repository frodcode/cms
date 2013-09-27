initNamespace('cms.meal');

cms.meal.mealModuleFactoryFactory = function () {
    return function (loadCalendarForMonthLink) {
        return angular.module('cms.meal', [
        ])
            // config
            .value('loadCalendarForMonthLink', loadCalendarForMonthLink)

            .controller('CalendarCtrl', cms.meal.calendar.CalendarCtrl)
            ;
    };


};