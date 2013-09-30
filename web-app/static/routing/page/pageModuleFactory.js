initNamespace('routing.page');

routing.page.pageModuleFactory = function () {
    return angular.module('rounting.page', [
        'ui.select2',
        routing.webalize.webalizeModuleFactory().name
    ])
        .controller('routing_PageCtrl', routing.page.PageCtrl)
        .directive('routingPage', routing.page.PageDirective);
};
