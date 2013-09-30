initNamespace('cms.meal.dailyMenu');

cms.meal.dailyMenu.DailyMenuCtrl = function ($scope) {
    $scope.collector.getData = function() {
        var dailyMenu = $scope.dailyMenu
        return {
            'date': dailyMenu.date.format('YYYY-MM-DD'),
            'menus' :_.map(dailyMenu.menus, function(menu) {
                return {
                    'type': menu.type,
                    'typeId': menu.typeId,
                    'name' : menu.name,
                    'date' : menu.date.format('YYYY-MM-DD')
                }
            })
        }
    }
};