
initNamespace('cms');

cms.AppModuleFactory = function () {
    this.modules = [];
    this.configureCallbacks = [];
};

cms.AppModuleFactory.prototype = {
    add: function (module) {
        this.modules.push(module.name);
    },
    configureApp: function (callback) {
        this.configureCallbacks.push(callback);
    },
    createAppModule: function () {
        var appModule = angular.module('app', this.modules);
        _.each(this.configureCallbacks, function (cb) {
            cb(appModule);
        });
        return appModule;
    }
};