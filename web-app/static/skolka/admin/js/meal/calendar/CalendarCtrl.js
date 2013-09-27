initNamespace('cms.meal.calendar');

cms.meal.calendar.CalendarCtrl = function($scope, $rootScope, loadCalendarForMonthLink) {
    $scope.message = 'message'
//    $scope.firstDayOfThisMonth = moment([currentYearNumber, currentMonthNumber]);
    $scope.loadCalendarLink =loadCalendarForMonthLink

    $scope.setNextMonth = function()
    {

    };

    $scope.setPreviousMonth = function() {

    };
};