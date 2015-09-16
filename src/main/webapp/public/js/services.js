

services.service('SalesReportService',['$http', function($http) {

	this.promise;

    this.initNumbers = function() {
    	 this.promise = $http.get('http://localhost:8080/report');
    };

    this.getData = function() {
    	if(!this.promise){
    		this.initNumbers();
    	}
    	return this.promise;
    };
}]);