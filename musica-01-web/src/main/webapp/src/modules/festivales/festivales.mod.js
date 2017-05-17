/*
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
(function (ng) {
    var mod = ng.module("festivalModule", ['ui.router']);
    mod.constant("festivalesContext", "api/festivales");
    mod.constant("usuariosContext", "api/usuarios");
    mod.constant("generosContext", "api/generos");
    mod.constant("ciudadesContext", "api/ciudades");
    mod.constant("funcionesContext", "api/funciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/festivales/';
            $urlRouterProvider.otherwise("/festivalesList");
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
            }).state('festivalesList', {
                url: '/list',
                parent: 'festivales',
                views: {
                    'listView': {
                      controller: 'festivalesCtrl',
                      controllerAs: 'ctrl',
                      templateUrl: basePath + 'festivales.list.html'
                    }
                }
            }).state('festivalCreate', {
                url: '/create',
                parent: 'festivales',
                views: {
                    'festivalView': {
                        controller: 'festivalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'festivales.create.html'
                    }
                }
            }).state('festivalEdit', {
                url: '/{festivalId:int}/edit',
                parent: 'festivales',
                views: {
                    'festivalView': {
                        controller: 'festivalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'festivales.create.html'
                    }
                }
            }).state('festivalDetail', {
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
