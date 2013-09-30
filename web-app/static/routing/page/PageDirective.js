initNamespace('routing.page');


routing.page.PageDirective = function () {
    return {
        restrict:'E',
        controller:'routing_PageCtrl',
        templateUrl: 'routing.page.page',

        scope:{
            modelData:'=',
            pageNodesData: '=',
            collector: '=',
            pageName: '@'
        },
        link:function ($scope, el, attrs, ctrl) {
            console.log($scope.pageNodesdata)
            console.log('linkuju')
        }
    };

};