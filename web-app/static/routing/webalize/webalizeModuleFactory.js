initNamespace('routing.webalize');

routing.webalize.webalizeModuleFactory = function () {
	return angular.module('rounting.webalize', [])
		.controller('routing_WebalizeCtrl', routing.webalize.WebalizeCtrl)
		.directive('routingWebalizedInput', routing.webalize.WebalizeDirective);
};
