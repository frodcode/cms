initNamespace('cms.article.editPage');

cms.article.editPage.EditPageDirective = function () {
    return {
        scope: {},
        restrict: 'E',
        replace: false,
        transclude: false,
        controller: 'EditPageCtrl',
        templateUrl: 'cms.article.editPage'
    };
};