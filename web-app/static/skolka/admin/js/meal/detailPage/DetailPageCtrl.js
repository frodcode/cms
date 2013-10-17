initNamespace('cms.meal.detailPage');

cms.meal.detailPage.DetailPageCtrl = function ($scope, $http, dailyMenuData, saveDailyMenuLink) {
    $scope.message = '';
    $scope.saveState = new admin.save.SaveState()
    $scope.dailyMenuData = dailyMenuData
    $scope.dataCollector = new cms.data.DataCollector('meal');
    $scope.dataCollector.getData = function() {}
    $scope.dailyMenus = _.map(dailyMenuData.dailyMenus, function(data) {
        return new cms.meal.model.DailyMenu(data);
    })
    $scope.menuCollectors = []
    _.each(dailyMenuData.dailyMenus, function(data, index) {
        $scope.menuCollectors.push($scope.dataCollector.createNew(index))
    })
    $scope.save = function() {
        $scope.message = '';
        $scope.saveState.saving = true;
        var data = $scope.dataCollector.getAllData();
        var request = $http.post(saveDailyMenuLink, data);
        request.success(function (data) {
            console.log(data)
            $scope.saveState.saving = false;
            $scope.message = 'Jídelníček uložen';
        }).error(function () {
            alert('Chyba při ukládání. Zkuste prosím akci opakovat');
            $scope.saveState.saving = false;
        });
    }

};