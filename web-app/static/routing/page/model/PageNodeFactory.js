initNamespace('routing.page.model');

routing.page.model.PageNodeFactory = function () {
    var self = this;
    this.createRoot = function(pageNodesData) {
        var allPageNodes = _.map(pageNodesData, function(data) {
            return new routing.page.model.PageNode(data)
        })
        _.each(allPageNodes, function(node) {
            self.loadChildrenFor(node, allPageNodes, pageNodesData);
        })
        var root = _.find(allPageNodes, function(node) {
           return node.isRoot()
        });
        return root;
    }

    this.loadChildrenFor = function(pageNode, allPageNodes, pageNodesData) {
        _.each(pageNodesData, function(data, key
            ) {
            if (data.parentId === pageNode.id) {
                pageNode.children.push(allPageNodes[key])
                allPageNodes[key].parent = pageNode
            }
        });
    }

    this.createNew = function(pageConfig, rootPageNode)  {
        var parent;
        if (pageConfig.defaultParentId) {
            parent = rootPageNode.getNodeById(pageConfig.defaultParentId);
            if (!parent) {
                throw 'Defined parent with id '+$scope.pageConfig.defaultParentId+' is not present in parents'
            }
        } else {
            parent = rootPageNode;
        }

        var pageNode = new routing.page.model.PageNode({
            domainUrl: pageConfig.defaultDomainUrl,
            domainId: pageConfig.defaultDomainId,
            pageTypeId: pageConfig.pageTypeId,
            urlType: pageConfig.defaultUrlPartType
        });

        pageNode.parent = parent;
        parent.children.push(pageNode)
        return pageNode;
    }
};