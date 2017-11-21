(function() {
	var app = angular.module('game', ['ngGrid', 'ngResource', 'ui.bootstrap', 'angularjs-dropdown-multiselect']);
	
	app.factory('StatService', ['$http', '$log', '$resource', function($http, $log, $resource) {						
		return $resource("/stat/:verb", {verb: 'list'}, {
			getStats: {method: 'GET', isArray: true}
		});
	}]);
	
	app.factory('SkillService', ['$http', '$log', '$resource', function($http, $log, $resource) {						
		return $resource("/skills", {}, {
			getSkills: {method: 'GET', isArray: true}
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
	
	app.controller('StatsController', ['$scope', '$modal', '$log', '$window', 'StatService', function($scope, $modal, $log, $window, StatService) {
		var cellTemplate='<div class="ngCellText" data-ng-model="row"><a data-ng-click="updateSelectedRow(row,$event)"><img alt="Edit" src="/images/edit.png" height="16px" width="16px"/></a><a data-ng-click="deleteSelectedRow(row,$event)"><img alt="Delete" src="/images/delete.png" height="16px" width="16px"/></a></div>';
		$scope.stats = StatService.query();
		
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
				             {field: '', cellTemplate: cellTemplate, width: '48px'}]
		};
		
		$scope.addNewAttribute = function() {
			var entity = new StatService();
			
			$scope.stats.push(entity);
			
		    var modalInstance = $modal.open({
		    	templateUrl: 'statDlg.html',
		    	controller: StatModalInstanceCtrl,
		    	resolve: {
		    		item: function () {
		    			return entity;
		    		},
		    		stats: function () {
		    			return $scope.stats;
		    		}
		    	}
		    });			
		};
		
		$scope.updateSelectedRow = function(row) {
			$scope.myrow = row.entity;
			
		    var modalInstance = $modal.open({
		    	templateUrl: 'statDlg.html',
		    	controller: StatModalInstanceCtrl,
		    	resolve: {
		    		item: function () {
		    			return row.entity;
		    		},
		    		stats: function () {
		    			return $scope.stats;
		    		}
		    	}
		    });
		};
	    
		$scope.deleteSelectedRow = function(row) {
			$scope.selectedItem = row.entity;
			
			var deleteItem = $window.confirm('Delete ' + $scope.selectedItem.shortForm + '?');
			
			if (deleteItem) {				
				$scope.stats.splice(row.rowIndex, 1);
				$scope.selectedItem.$delete({verb: 'delete', id: $scope.selectedItem.id});
			}
		};
	}]);
	
	app.controller('SkillController', ['$scope', '$modal', '$log', '$window', 'StatService', 'SkillService', function($scope, $modal, $log, $window, StatService, SkillService) {
		var cellTemplate='<div class="ngCellText" data-ng-model="row"><a data-ng-click="updateSelectedRow(row,$event)"><img alt="Edit" src="/images/edit.png" height="16px" width="16px"/></a><a data-ng-click="deleteSelectedRow(row,$event)"><img alt="Delete" src="/images/delete.png" height="16px" width="16px"/></a></div>';
		$scope.skills = SkillService.query();
		
		$scope.gridOptions = {
				data : 'skills',
				columnDefs: [{field: 'id', displayName: 'Id', cellClass: 'gridCellNumberStyle'},
				             {field: 'name', displayName: 'Name'},
				             {field: 'shortName', displayName: 'Short Name'},
				             {field: 'primaryStat.code', displayName: 'Primary Attribute'},
				             {field: 'secondaryStats', displayName: 'Secondary Attributes'},
				             {field: 'baseCost', displayName: 'Base Cost', cellClass: 'gridCellNumberStyle'},
				             {field: 'levelCost', displayName: 'Level Cost', cellClass: 'gridCellNumberStyle'},
				             {field: 'selectable', displayName: 'Selectable', cellTemplate: '<input type="checkbox" ng-model="row.entity.selectable" ng-click="toggle(row.entity.selectable)">'},
				             {field: '', cellTemplate: cellTemplate, width: '48px'}]
		};
		
		$scope.addNewSkill = function() {
			var entity = new SkillService();
			
			$scope.skills.push(entity);
			
		    var modalInstance = $modal.open({
		    	templateUrl: 'skillDlg.html',
		    	controller: SkillModalInstanceCtrl,
		    	resolve: {
		    		item: function () {
		    			return entity;
		    		},
		    		stats: function () {
		    			return StatService.query();
		    		},
		    		skills: function () {
		    			return $scope.skills;
		    		}
		    	}
		    });			
		};
		
		$scope.updateSelectedRow = function(row) {
			$scope.myrow = row.entity;
			
		    var modalInstance = $modal.open({
		    	templateUrl: 'skillDlg.html',
		    	controller: SkillModalInstanceCtrl,
		    	resolve: {
		    		item: function () {
		    			return row.entity;
		    		},
		    		stats: function () {
		    			return StatService.query().$promise.then(function (data) {
	    					var stats = [];

	    					if (data.length > 0) {
		    					var	index = 0;
		    					
		    					while (index < data.length) {
		    						var stat = data[index];
		    					
		    						stat.label = stat.shortForm;
		    					
		    						stats[index++] = stat;
		    					}
		    				}
		    				
		    				return stats;
		    			});
		    		},
		    		skills: function () {
		    			return $scope.skills;
		    		}
		    	}
		    });
		};
	    
		$scope.deleteSelectedRow = function(row) {
			$scope.selectedItem = row.entity;
			
			var deleteItem = $window.confirm('Delete ' + $scope.selectedItem.name + '?');
			
			if (deleteItem) {				
				$scope.skills.splice(row.rowIndex, 1);
				$scope.selectedItem.$delete({method: 'DELETE', id: $scope.selectedItem.id});
			}
		};
	}]);

	app.directive('uppercase', ['$parse', function($parse) {
		return {
			restrict: 'A',
			require: 'ngModel',
		    link: function(scope, element, attrs, modelCtrl) {
		    	var uppercase = function(inputValue) {
		           if (inputValue == undefined)
		        	   inputValue = '';
		           
		           var uppercased = inputValue.toUpperCase();
		           
		           if (attrs.ngMaxlength != undefined && uppercased.length > attrs.ngMaxlength) {
		        	   uppercased = uppercased.substr(0, attrs.ngMaxlength);
		           }
		           
		           if (uppercased !== inputValue) {
		        	   modelCtrl.$setViewValue(uppercased);
		        	   modelCtrl.$render();
		           }
		           
		           return uppercased;
		    	}
		    	
		        modelCtrl.$parsers.push(uppercase);
		         
		        uppercase($parse(attrs.ngModel)(scope));  // capitalize initial value
		    }
		};
	}]);
	
	app.directive('capitalize', ['$parse', function($parse) {
		return {
			restrict: 'A',
			require: 'ngModel',
		    link: function(scope, element, attrs, modelCtrl) {
		    	var capitalize = function(inputValue) {
		           if (inputValue == undefined)
		        	   inputValue = '';
		           		           
		           var capitalized = inputValue;
		           
		           if (inputValue.length > 0) {
		        	   capitalized = inputValue.substr(0, 1).toUpperCase();
		        	   
		        	   if (inputValue.length > 1) {
		        		   capitalized += inputValue.substr(1, inputValue.length - 1).toLowerCase();
		        	   }
		           }
		           
		           if (attrs.ngMaxlength != undefined && uppercased.length > attrs.ngMaxlength) {
		        	   capitalized = capitalized.substr(0, attrs.ngMaxlength);
		           }
		           
		           if (capitalized !== inputValue) {
		        	   modelCtrl.$setViewValue(capitalized);
		        	   modelCtrl.$render();
		           }
		           
		           return capitalized;
		    	}
		    	
		        modelCtrl.$parsers.push(capitalize);
		         
		        capitalize($parse(attrs.ngModel)(scope));  // capitalize initial value
		    }
		};
	}]);
	
	app.directive('checkRange', ['$parse', function($parse) {
		return {
			restrict: 'A',
			require: 'ngModel',
		    link: function(scope, element, attrs, modelCtrl) {
		    	var getValue = function(inputValue) {
		           if (inputValue == undefined)
		        	   inputValue = attrs.ngMin;
		           
		           value = inputValue;
		           
		           if (attrs.ngMin != undefined && value < attrs.ngMin) {
		        	   value = attrs.ngMin;
		           }
		           
		           if (attrs.ngMax != undefined && value > attrs.ngMax) {
		        	   value = attrs.ngMax;
		           }
		           
		           if (value !== inputValue) {
		        	   modelCtrl.$setViewValue(value);
		        	   modelCtrl.$render();
		           }
		           
		           return value;
		    	}
		    	
		        modelCtrl.$parsers.push(getValue);
		         
		        getValue($parse(attrs.ngModel)(scope));  // force value into range
		    }
		};
	}]);
	
	var StatModalInstanceCtrl = function ($scope, $modalInstance, item, stats) {
		$scope.availableCodes = getAvailableCodes(stats);
		$scope.item = item;
		  
		var id = item.id;
		var code = item.code;
		var shortForm = item.shortForm;
		var longForm = item.longForm;
		var multiplier = item.multiplier;

		$scope.ok = function () {
			item.$save({verb: 'update', stat: item})
			  
			$modalInstance.close(item);
		};

		$scope.cancel = function () {
			item.id = id;
			item.code = code;
			item.shortForm = shortForm;
			item.longForm = longForm;
			item.multiplier = multiplier;
			
			if (item.id == undefined) {
				stats.pop();
			}
			
			$modalInstance.dismiss('cancel');
		};
		
		function getAvailableCodes(stats) {
			var availableCodes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
			
			stats.forEach(function(stat) {
				if (availableCodes.indexOf(stat.code) > -1 && item.code != stat.code) {
					availableCodes.splice(availableCodes.indexOf(stat.code), 1);
				}
			});
			
			return availableCodes;
		}
	};
	
	var SkillModalInstanceCtrl = function ($scope, $modalInstance, item, stats, skills) {
		$scope.stats = stats;
		$scope.item = item;
		  
		var id = item.id;
		var name = item.name;
		var shortName = item.shorName;
		var primaryStat = item.primaryStat;
		var secondaryStats = item.secondaryStats;
		var baseCost = item.baseCost;
		var levelCost = item.levelCost;
		var selectable = item.selectable;
		
		if (item.primaryStat == null) {
			item.primaryStat = stats[0];
		}
		
		$scope.secondaryStats = [];
		$scope.statSettings = { smartButtonMaxItems: 3, smartButtonTextConverter: function(itemText, originalItem) { return originalItem.code; } };
		
		$scope.ok = function () {
			item.$save({method: 'PUT', skill: item})
			  
			$modalInstance.close(item);
		};

		$scope.cancel = function () {
			item.id = id;
			item.name = name;
			item.shortName = shortName;
			item.primaryStat = primaryStat;
			item.secondaryStats = secondaryStats;
			item.baseCost = baseCost;
			item.levelCost = levelCost;
			item.selectable = selectable;
			
			if (item.id == undefined) {
				skills.pop();
			}
			
			$modalInstance.dismiss('cancel');
		};
	};
})();
