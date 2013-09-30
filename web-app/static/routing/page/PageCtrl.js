initNamespace('routing.page');


routing.page.PageCtrl = function ($scope, routing_PageNodeFactory, routing_pageConfigData) {
    $scope.pageConfig = new routing.page.config.PageConfig(routing_pageConfigData);
    $scope.rootPageNode = routing_PageNodeFactory.createRoot($scope.pageNodesData)
    if ($scope.modelData) {
        $scope.pageNode = $scope.rootPageNode.getNodeById($scope.modelData.id)
    } else {
        $scope.pageNode = routing_PageNodeFactory.createNew($scope.pageConfig, $scope.rootPageNode);
    }
    $scope.parentNodeId = $scope.pageNode.parent.id

    var descendants = $scope.rootPageNode.getDescendants(true)
    $scope.parents  = _.filter(descendants, function(pageNode) {
        return pageNode != $scope.pageNode && $scope.pageNode != pageNode.parent;
    });

    $scope.$watch('modelData.id', function(newVal) {
        $scope.pageNode.id = newVal;
    });

    $scope.getPageNameForSelect = function(pageNode) {
        var prefix = new Array(pageNode.getLevel()).join('  ');
        return prefix + pageNode.name
    }
    $scope.$watch('parentNodeId', function(newVal) {
        if (newVal) {
            $scope.pageNode.parent = $scope.rootPageNode.getNodeById(parseInt(newVal))
        }
    });

    $scope.changeUrlType = function(pageNode) {
        if (pageNode.urlType === routing.page.model.UrlTypeEnum.FROM_PARENT) {
            pageNode.urlType = routing.page.model.UrlTypeEnum.FROM_ROOT;
        } else {
            pageNode.urlType = routing.page.model.UrlTypeEnum.FROM_PARENT;
        }
    }

    $scope.collector.getData = function() {
        var pageNode = $scope.pageNode
        return {
            'urlType': pageNode.urlType,
            'urlPart': pageNode.urlPart,
            'parentId': pageNode.parent.id,
            'pageTypeId': pageNode.pageTypeId,
            'id': pageNode.id,
            'domainId': pageNode.domainId
        }
    }

    $scope.printUrlPart = $scope.pageNode.urlPart.substr(1)

    $scope.$watch('printUrlPart', function(newVal) {
        $scope.pageNode.urlPart = '/' + newVal;
    });

};