(function(){
	var app = angular.module('game', ['ngGrid', 'ui.bootstrap']);
	
	app.factory('StatService', function(){
		var stats = [{
			id: 1,
			code: 'X',
			shortForm: 'XXX',
			longForm: 'Xxxxxx',
			multiplier: 3
		},
		{
			id: 2,
			code: 'Y',
			shortForm: 'YYY',
			longForm: 'Yyyyyyy',
			multiplier: 2

		}];
		
		function getStats() {
			return stats;
		}
		
		function saveStat(stat) {
			console.log("Saved");
		}
		
		return {getStats : getStats, saveStat : saveStat};
	});
	
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
	
	app.controller('StatsController', ['$scope', '$modal', 'StatService', function($scope, $modal, statService) {
		var cellTemplate='<div class="ngCellText" data-ng-model="row"><a data-ng-click="updateSelectedRow(row,$event)"><img alt="Edit" src="edit.png" height="16px" width="16px"/></a></div>';
		
		$scope.stats = statService.getStats();
		
		$scope.$on('ngGridEventEndCellEdit', function(evt) {
            statService.saveStat(evt.targetScope.row.entity);
         });
		
		$scope.gridOptions = {
				data : 'stats',
				columnDefs: [{field: 'id', displayName: 'Id', cellClass: 'gridCellNumberStyle'},
				             {field: 'code', displayName: 'Code'},
				             {field: 'shortForm', displayName: 'Short'},
				             {field: 'longForm', displayName: 'Long'},
				             {field: 'multiplier', displayName: 'Multiplier', cellClass: 'gridCellNumberStyle'},
				             {field: '', cellTemplate: cellTemplate}]
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
		
	    $scope.save = function() {
	    	console.log($modal);
	    	$modal.dismiss('cancel');
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
			  $modalInstance.close();
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
