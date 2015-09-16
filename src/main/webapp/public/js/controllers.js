
controllers.controller('GraphCtrl', ['$scope','$timeout','SalesReportService',function ($scope,$timeout,SalesReportService) {
    $scope.chart = null;
    $scope.config={};
 
    $scope.config.data=[]

 
    $scope.showGraph = function() {
        var config = {};
        config.bindto = '#chart';
        config.data = {};
        config.data.json = $scope.config.data;
        $scope.chart = c3.generate({
            data: {
                columns: [
                    ['Product-1', 5],
                    ['Product-2', 4],
                    ['Product-3', 3],
                    ['Product-4', 3],
                ],
            	//columns: $scope.config.data,
                type : 'donut'
            },
            donut: {
                title: "Sales Report"
            }});    
    };
    
    SalesReportService.getData().then(function(result){
		$scope.config.data = result.data;
	});  
    
}]);


controllers.controller('MainCtrl', [ '$scope', 'uiGridConstants', 'SalesReportService',
		function($scope, uiGridConstants, SalesReportService) {
			$scope.gridOptions = {
					enableSorting: true,	  
				    enableFiltering: true,
				    enableColumnResizing: true,
				    columnDefs: [
				      // default
				      { field: 'salesId', width:100 },
				      // search filter input
				      { field: 'product.name',
				    	  width:150,
				          filter: {
				              condition: uiGridConstants.filter.CONTAINS,
				              placeholder: 'search'
				            }},


				      // multiple filters
				      { field: 'dateOfSale', width:100, filters: [
				        {
				          condition: uiGridConstants.filter.GREATER_THAN,
				          placeholder: 'greater than'
				        },
				        {
				          condition: uiGridConstants.filter.LESS_THAN,
				          placeholder: 'less than'
				        }
				      ]},
				      ,
				      // custom condition function
				      {
				        field: 'amount',
				        width:100,
				        filter: {
				            condition: uiGridConstants.filter.GREATER_THAN,
				            placeholder: 'greater than'
				          }
				      },
				    ]
				  };

			SalesReportService.getData().then(function(result) {
					$scope.gridOptions.data = result.data;
				});

		} ]);