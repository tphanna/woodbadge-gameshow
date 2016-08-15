
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
            controller  : 'finalController',
            activetab   : 'final',
            round       : 4
        })

        .when('/score', {
            templateUrl : 'pages/scoreboard.html',
            controller  : 'scoreController',
            activetab   : 'score',
            round       : 5
        });
    
});


gameshowApp.controller('mainController', function($scope, $route) {
    $scope.$route = $route;
});

gameshowApp.controller('roundController', function($scope, $route, $http, $interval) {
    $scope.showQuestion = false;
    $scope.showCritter = '';
    $scope.round = $route.current.round;
    $scope.questions = {};
    $scope.timerPromise = {};

    $scope.getQuestion = function (category, points) {
        $scope.questions[category].filter(function(question){if (question.points == points){$scope.question = question;}});
        if (!$scope.question.answered) {
            $scope.showQuestion = true;
        }
    };

    $scope.getQuestions = function () {
        var url = 'http://localhost:8080/questions/'+$scope.round;
        $http.get(url).success(function(data) {
            $scope.questions = data;
        });
    };

    $scope.answerQuestion = function(result) {
        if ($scope.showCritter == '' && result != 'noAnswer') {
            return;
        }
        var url = 'http://localhost:8080/score/'+$scope.round+'/'+ $scope.question.category+'/'+$scope.question.points+'/'+$scope.showCritter.toUpperCase()+'/' + result;
        $http.post(url);
        $scope.showCritter = '';
        if (result == 'correct' || result == 'noAnswer') {
            $scope.question.answered = true;
            $scope.showQuestion = false;
        }

    };

    $scope.startTimer = function() {
        if ($scope.timerPromise) {
            $interval.cancel($scope.timerPromise);
        }
        $scope.timer = 10;
        $scope.timerPromise = $interval(function() {
            $scope.timer -=1
            },
            1000,10);
    }

    $scope.cancelQuestion = function() {
        $scope.showQuestion = false;
        $scope.showCritter = '';
    };

    $scope.getQuestions();
});

gameshowApp.controller('finalController', function($scope, $route, $http) {
    $scope.$route = $route;
    $scope.showQuestion = false;

    $scope.toggleFinalQuestion = function() {
        $scope.showQuestion = !$scope.showQuestion;
    };

    $scope.getScore = function () {
        var url = 'http://localhost:8080/score';
        $http.get(url).success(function(data) {
            $scope.score = data;
        });
    };

    $scope.finalQuestion = function(points, critter, result) {
        var url = 'http://localhost:8080/score/final/' + points + '/' + critter + '/' + result;
        $http.post(url).success(function() {
            $scope.getScore();
        });
    };

    $scope.getScore();
});

gameshowApp.controller('scoreController', function($scope, $route, $http) {
    $scope.$route = $route;

    $scope.getScore = function () {
        var url = 'http://localhost:8080/score';
        $http.get(url).success(function(data) {
            $scope.score = data;
        });
    };

    $scope.getScore();
});
