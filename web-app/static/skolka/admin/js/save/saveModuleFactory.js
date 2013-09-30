initNamespace('admin.save');

admin.save.saveModuleFactory = function () {
    return angular.module('admin.save', [
    ])
        .directive('adminSaveButton', admin.save.SaveButtonDirective);
};
