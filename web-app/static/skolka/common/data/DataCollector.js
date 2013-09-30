initNamespace('cms.data');

cms.data.DataCollector = function(namespace, parent) {
    if (namespace === null || typeof namespace === 'undefined') {
        throw 'You must specify namespace for data collector'
    }
    this.namespace = namespace;

    this.subcollectors = [];

    this.createNew = function(newNamespace) {
        var collector = new cms.data.DataCollector(newNamespace, this)
        this.subcollectors.push(collector)
        return collector;
    }

    this.getAllData = function() {
        var data = {}
        if (typeof this.getData != 'function') {
            console.warn('Data collector does not have getData function!');
            return {}
        }
        _.each(this.subcollectors, function(collector) {
            data = angular.extend(data, collector.getAllData())
        });
        var myData;
        data[this.namespace] = this.getData();
        return data;
    }
}