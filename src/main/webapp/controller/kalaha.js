var KalahaModule = angular.module('kalahaModule', []);

KalahaModule.controller("kalahaController", function($scope, $http) {

	urlKalaha = 'http://localhost:8080/rest/kalaha';
	
	$scope.message = '';
	
	$scope.getKalaha = function() {
		$http.get(urlKalaha).then(function(response) {
			$scope.kalaha = response.data;
			if($scope.kalaha.gameOver == "true"){
				if($scope.kalaha.winner == 3){
					$scope.message = "Game Over! The result was a draw.";
				} else{"Game Over! Winner is player "+ $scope.kalaha.winner;
					$scope.message = "Game Over! Player "+ $scope.kalaha.winner+ " is the winner.";
				}
			}else{
				$scope.message = "Player "+ $scope.kalaha.playerTurn+ " turn";
			}
		}).catch(function(response) {
			alert(response.status);
		});
	}
	
	$scope.playKalaha = function(pitNumber) {
		if (pitNumber != undefined) {
			$http.post(urlKalaha + '/' + pitNumber).then(function() {
			$scope.getKalaha();
			}).catch(function(response) {
				alert(response.status);
			});
		}
	}
	
	$scope.newGame = function() {
		$http.put(urlKalaha).then(function() {
			$scope.getKalaha();
		}).catch(function(response) {
			alert(response.status);
		});
	}

	$scope.getKalaha();

});