initNamespace('routing.page');

routing.page.pageModuleFactory = function (webalizeModule) {
    return angular.module('rounting.page', [
        'ui.select2', webalizeModule.name
    ])
        .controller('routing_PageCtrl', routing.page.PageCtrl)
        .directive('routingPage', routing.page.PageDirective);
};
