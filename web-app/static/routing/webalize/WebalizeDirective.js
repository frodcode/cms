initNamespace('routing.webalize');

routing.webalize.WebalizeDirective = function () {
	return {
		restrict: 'A',
		controller: 'routing_WebalizeCtrl',
		scope: {
			source: '@',
			slug: '=ngModel'
		},
		link: function (scope, el, attrs, ctrl) {
			var $el = $(el);
			scope.$watch('slug', function () {
				$el.val(scope.slug);
			});
			$el.change(function () {
				scope.$apply(function () {
					scope.customSlug = $el.val();
				});
			});
		}
	};
};
