initNamespace('cms.meal');

cms.meal.mealModuleFactoryFactory = function (saveButtonModule) {
    return function (dailyMenuData, saveDailyMenuLink) {
        return angular.module('cms.meal', [
            saveButtonModule.name
        ])
            // config
            .value('dailyMenuData', dailyMenuData)
            .value('saveDailyMenuLink', saveDailyMenuLink)

            .controller('DailyMenuCtrl', cms.meal.dailyMenu.DailyMenuCtrl)
            .directive('dailyMenu', cms.meal.dailyMenu.DailyMenuDirective)

            .controller('DetailPageCtrl', cms.meal.detailPage.DetailPageCtrl)
            .directive('detailPage', cms.meal.detailPage.DetailPageDirective)
            ;
    };


};