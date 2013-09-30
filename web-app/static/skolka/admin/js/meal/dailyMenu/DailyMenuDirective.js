initNamespace('cms.meal.dailyMenu');

cms.meal.dailyMenu.DailyMenuDirective = function () {
    return {
        scope: {
            collector: "=",
            dailyMenu: "=dailyMenu"
        },
        restrict: 'E',
        replace: false,
        transclude: true,
        controller: 'DailyMenuCtrl',
        templateUrl: 'cms.meal.dailyMenu.dailyMenu'
    };
};