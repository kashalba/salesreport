var services = angular.module('services',[]);
var controllers = angular.module('controllers',['services']); 
var directives = angular.module('directives',[]); 
var app = angular.module('app', ['services', 'controllers', 'directives', 'ui.grid',
                         		'ui.grid.resizeColumns'  ]);
