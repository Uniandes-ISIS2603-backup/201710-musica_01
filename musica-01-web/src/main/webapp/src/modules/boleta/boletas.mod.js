(function (ng) {
    var mod = ng.module("boletaModule", ['ui.router']);
    mod.constant("boletasContext", "api/boletas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/boletas/';
            $urlRouterProvider.otherwise("/boletasList");
            self = this;
             $stateProvider.state('boletas', {
                url: '/boletas',
                abstract: true,
                resolve: {
                    boletas: ['$http', 'boletasContext', function ($http, boletasContext) {
                            return $http.get(boletasContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'boletas.html',
                        controller: ['$scope', 'boletas', function ($scope, boletas) {
                                $scope.boletasRecords = boletas.data;
                            }]
                    }
                }
            }).state('boletasList', {
                url: '/list',
                parent: 'boletas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'boletas.list.html'
                    }
                }
            });
        }]);
})(window.angular);
