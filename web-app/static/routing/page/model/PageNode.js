initNamespace('routing.page.model');

routing.page.model.PageNode = function(data) {
    var self = this;
    this.name = data.name
    this.id = data.id
    this.domainId = data.domainId
    this.parent = null
    this.name = data.name
    this.children = []

    this.urlPart = data.urlPart ? data.urlPart : ''
    this.langPart = data.langPart
    this.urlType = routing.page.model.UrlTypeEnum.getEnumValue(data.urlType.name)
    console.log(this.urlType)
    this.httpMethod = data.httpMethod
    this.requestType = data.requestType
    this.domainUrl = data.domainUrl
    this.pageTypeId = data.pageTypeId

    this.getLevel = function() {
        level = 1;
        if (this.parent) {
            level += this.parent.getLevel()
        }
        return level
    }

    this.getDescendants = function(includeSelf) {
        console.log('zjistuji potomky')
        var descendants = [];
        if (includeSelf) {
            descendants.push(this)
        }
        _.each(this.children, function(child) {
            descendants.push(child)
            _.each(child.getDescendants(), function(desc){
                descendants.push(desc)
            });
        });
        return descendants
    }

    this.getNodeById = function(pageId) {
        return _.find(this.getDescendants(true), function(node) {
            return node.id === pageId
        })
    }

    this.handleSlashes = function(urlPart, lastSlash) {
        if (urlPart === "/") {
            urlPart = ''
        }
        if (lastSlash) {
            urlPart += '/'
        }
        return urlPart
    }

    this.getFullUrlPart = function(lastSlash) {
        var urlPart = ''

        if (!this.parent || this.urlType === routing.page.model.UrlTypeEnum.FROM_ROOT) {
            urlPart += this.urlPart
            urlPart = this.handleSlashes(urlPart, lastSlash)
            return urlPart
        }

        urlPart += this.parent.getFullUrlPart()
        urlPart += this.urlPart
        urlPart = this.handleSlashes(urlPart, lastSlash)

        return urlPart;
    }

    this.getParentsUrlPart = function(lastSlash) {
        var fullUrlPart = this.getFullUrlPart(lastSlash)
        var parentsUrlPart = fullUrlPart.substr(0, fullUrlPart.length-this.urlPart.length)
        if (parentsUrlPart === '') {
            parentsUrlPart = '/';
        }
        return parentsUrlPart;
    }

    this.getUrl = function() {
        return this.domainUrl + this.getFullUrlPart()
    }

    this.getUrlPartWithoutSlash = function() {
        return this.urlPart.substr(1)
    }

    this.isRoot = function() {
        if (this.parent) {
            return false
        }
        return true;
    }


}