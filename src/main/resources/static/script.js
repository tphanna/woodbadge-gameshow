
var gameshowApp = angular.module('gameshowApp', ['ngRoute']);

gameshowApp.config(function($routeProvider) {
    $routeProvider

        .when('/start', {
            templateUrl : 'pages/start.html',
            controller  : 'mainController',
            activetab   : 'start',
            round       : 0
        })

        .when('/round1', {
            templateUrl : 'pages/round.html',
            controller  : 'roundController',
            activetab   : 'round1',
            round       : 1
        })

        .when('/round2', {
            templateUrl : 'pages/round.html',
            controller  : 'roundController',
            activetab   : 'round2',
            round       : 2
        })

        .when('/round3', {
            templateUrl : 'pages/round.html',
            controller  : 'roundController',
            activetab   : 'round3',
            round       : 3
        })

        .when('/finalQuestion', {
            templateUrl : 'pages/finalQuestion.html',
            controller  : 'mainController',
            activetab   : 'final',
            round       : 4
        })

        .when('/score', {
            templateUrl : 'pages/scoreboard.html',
            controller  : 'mainController',
            activetab   : 'score',
            round       : 5
        });
    
});


gameshowApp.controller('mainController', function($scope, $route, $http) {
    $scope.$route = $route;
});

gameshowApp.controller('roundController', function($scope, $route, $http) {
    $scope.showQuestion = false;
    $scope.showCritter = '';
    $scope.round = $route.current.round;
    $scope.questions = {};

    $scope.getQuestion = function (category, points) {
        $scope.questions[category].filter(function(question){if (question.points == points){$scope.question = question;}});
        if (!$scope.question.answered) {
            $scope.showQuestion = true;
        }
    }

    $scope.getQuestions = function () {
        var url = 'http://localhost:8080/questions/'+$scope.round;
        $http.get(url).success(function(data) {
            $scope.questions = data;
        });
    }

    $scope.answerQuestion = function(result) {
        if ($scope.showCritter == '') {
            return;
        }
        var url = 'http://localhost:8080/score/'+$scope.round+'/'+ $scope.question.category+'/'+$scope.question.points+'/eagle/' + result;
        $http.post(url);
        $scope.showCritter = '';
        if (result == 'correct') {
            $scope.question.answered = true;
            $scope.showQuestion = false;
        }

    }

    $scope.cancelQuestion = function() {
        $scope.showQuestion = false;
        $scope.showCritter = '';
    }

    $scope.getQuestions();
});
