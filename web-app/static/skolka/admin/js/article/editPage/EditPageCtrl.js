initNamespace('cms.article.editPage');

cms.article.editPage.EditPageCtrl = function ($scope, $http, articleData, saveArticleLink) {
    $scope.saveState = new admin.save.SaveState()
    $scope.article = articleData
    $scope.dataCollector = new cms.data.DataCollector('meal');
    $scope.save = function() {
        $scope.saveState.saving = true;
        var data = $scope.dataCollector.getAllData();
        var request = $http.post(saveArticleLink, data);
        request.success(function (data) {
            console.log(data)
            $scope.saveState.saving = false;
        }).error(function () {
                alert('Chyba při ukládání. Zkuste prosím akci opakovat');
                $scope.saveState.saving = false;
            });
    }

};