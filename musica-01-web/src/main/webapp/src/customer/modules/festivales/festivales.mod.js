/*
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
(function (ng) {
    var mod = ng.module("festivalCModule", ['ui.router']);
    mod.constant("festivalesContext", "api/festivales");
    mod.constant("usuariosContext", "api/usuarios");
    mod.constant("generosContext", "api/generos");
    mod.constant("ciudadesContext", "api/ciudades");
    mod.constant("funcionesContext", "api/funciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/customer/modules/festivales/';
            $urlRouterProvider.otherwise("/festivalesCList");
            self = this;
             $stateProvider.state('festivales', {
                url: '/festivales',
                abstract: true,
                resolve: {
                    festivales: ['$http', 'festivalesContext', function ($http, festivalesContext) {
                            return $http.get(festivalesContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'festivales.html',
                        controller: ['$scope', 'festivales', function ($scope, festivales) {
                                $scope.festivalesRecords = festivales.data;
                            }]
                    }
                }
            }).state('festivalesCList', {
                url: '/list',
                parent: 'festivales',
                views: {
                    'listView': {
                        templateUrl: basePath + 'festivales.list.html'
                    }
                }
            }).state('festivalCDetail', {
                url: '/{festivalId:int}/detail',
                parent: 'festivales',
                param: {
                    festivalId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'festivales.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentFestival = $scope.festivalesRecords[$params.festivalId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);
