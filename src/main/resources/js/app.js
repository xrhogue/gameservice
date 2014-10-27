(function() {
	var app = angular.module('game', ['ngGrid', 'ngResource', 'ui.bootstrap']);
	app.factory('StatService', ['$http', '$log', '$resource', function($http, $log, $resource) {						
		return $resource("http://localhost:8080/stat/:verb", {verb: 'list'}, {
			getStats: {method: 'GET', isArray: true}
		});
	}]);
	
	app.controller('GameController', function() {
	});
	
	app.controller('PanelController', function() {
		this.tab = 1;
		
		this.selectTab = function(setTab) {
			this.tab = setTab;
		};
		
		this.isSelected = function(checkTab) {
			return this.tab === checkTab;
		};
	});
	
	app.controller('StatsController', ['$scope', '$modal', '$log', 'StatService', function($scope, $modal, $log, statService) {
		var cellTemplate='<div class="ngCellText" data-ng-model="row"><a data-ng-click="updateSelectedRow(row,$event)"><img alt="Edit" src="edit.png" height="16px" width="16px"/></a></div>';
		$scope.stats = statService.query();
		
//		$scope.$on('ngGridEventEndCellEdit', function(evt) {
//            statService.saveStat(evt.targetScope.row.entity);
//         });
		
		$scope.gridOptions = {
				data : 'stats',
				columnDefs: [{field: 'id', displayName: 'Id', cellClass: 'gridCellNumberStyle'},
				             {field: 'code', displayName: 'Code'},
				             {field: 'shortForm', displayName: 'Short Name'},
				             {field: 'longForm', displayName: 'Long Name'},
				             {field: 'multiplier', displayName: 'Multiplier', cellClass: 'gridCellNumberStyle'},
				             {field: '', cellTemplate: cellTemplate, width: '32px'}]
		};
		
		var dialog;
		    
		$scope.updateSelectedRow = function(row) {
			$scope.myrow = row.entity;
			
		    var modalInstance = $modal.open({
		    	templateUrl: 'statDlg.html',
		    	controller: ModalInstanceCtrl,
		    	resolve: {
		    		item: function () {
		    			return row.entity;
		    		}
		    	}
		    });
		}
	}]);
	
	var ModalInstanceCtrl = function ($scope, $modalInstance, item) {
		  $scope.item = item;
		  
		  var id = item.id;
		  var code = item.code;
		  var shortForm = item.shortForm;
		  var longForm = item.longForm;
		  var multiplier = item.multiplier;

		  $scope.ok = function () {
			  item.$save({verb: 'update', stat: item})
			  
			  $modalInstance.dismiss('cancel');
		  };

		  $scope.cancel = function () {
			  item.id = id;
			  item.code = code;
			  item.shortForm = shortForm;
			  item.longForm = longForm;
			  item.multiplier = multiplier;
			  
			  $modalInstance.dismiss('cancel');
		  };
	};
})();
