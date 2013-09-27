
initNamespace('cms');

cms.Application = function () {
    this.dic = angular.module('dic', ['ng']);
    this.injector = null;
};

cms.Application.prototype = {
    configure: function (callback) {
        if (this.injector !== null) {
            throw "You cannot call configure method after invoke.";
        }
        callback(this.dic);
    },
    invoke: function (callback) {
        if (this.injector === null) {
            this.injector = angular.injector(['dic']);
        }

        this.injector.invoke(callback);
    }
};