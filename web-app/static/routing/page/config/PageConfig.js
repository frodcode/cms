initNamespace('routing.page.config');

routing.page.config.PageConfig = function(data) {
    this.useParentSelect = data.useParentSelect;
    this.defaultDomainId = data.defaultDomainId;
    this.defaultParentId = data.defaultParentId;
    this.defaultUrlPartType = data.defaultUrlPartType;
    this.pageTypeId = data.pageTypeId;
    this.defaultDomainUrl = data.defaultDomainUrl;
};