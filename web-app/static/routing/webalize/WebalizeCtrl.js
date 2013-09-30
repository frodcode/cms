initNamespace('routing.webalize');

routing.webalize.WebalizeCtrl = function ($scope) {
	$scope.$watch('slug', function () {
		$scope.customSlug = webalize($scope.source) === $scope.slug ? null : $scope.slug;
	});

	$scope.$watch('source + customSlug', function () {
		if ($scope.customSlug) {
			$scope.slug = webalize($scope.customSlug || '');
		} else {
			$scope.slug = webalize($scope.source || '');
		}
	});
};
