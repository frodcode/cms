initNamespace('cms.meal.detailPage');

cms.meal.detailPage.DetailPageDirective = function () {
    return {
        scope: {},
        restrict: 'E',
        replace: false,
        transclude: false,
        controller: 'DetailPageCtrl',
        templateUrl: 'cms.meal.detailPage'
    };
};