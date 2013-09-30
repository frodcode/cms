initNamespace('admin.save');


admin.save.SaveButtonDirective = function () {
    return {
        restrict:'A',
        scope:{
            adminSaveButton:'=',
            savingMessage: '@'
        },
        link:function ($scope, $el, attrs, ctrl) {
            $scope.$watch('adminSaveButton.saving', function(newVal) {
                var savingMessage = $scope.savingMessage ? $scope.savingMessage : 'Ukládám...'
                if (newVal) {
                    $el.attr('value', savingMessage)
                    $el.attr('disabled', true)
                } else {
                    $el.attr('disabled', false)
                    $el.attr('value', attrs.value)
                }
            })
        }
    };

};