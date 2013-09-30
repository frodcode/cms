initNamespace('routing.page.model.UrlTypeEnum');

routing.page.model.UrlTypeEnum.FROM_PARENT = 'FROM_PARENT';
routing.page.model.UrlTypeEnum.FROM_ROOT = 'FROM_ROOT';
routing.page.model.UrlTypeEnum.ROOT = 'ROOT';

routing.page.model.UrlTypeEnum.getEnumValue = function(value) {
    if (!routing.page.model.UrlTypeEnum[value]) {
        throw 'Unknown url type '+value;
    }
    return routing.page.model.UrlTypeEnum[value];
}