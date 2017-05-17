/*
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
(function (ng) {
    var mod = ng.module("funcionModule", ['ui.router']);
    mod.constant("funcionesContext", "api/funciones");
    mod.constant("lugaresContext", "api/lugares");
    mod.constant("boletasContext", "api/boletas");
    mod.constant("musicosContext", "api/musicos");
    mod.constant("festivalesContext", "api/festivales");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/funciones/';
            $urlRouterProvider.otherwise("/funcionesList");
            self = this;
             $stateProvider.state('funciones', {
                url: '/funciones',
                abstract: true,
                resolve: {
                    funciones: ['$http', 'funcionesContext', function ($http, funcionesContext) {
                            return $http.get(funcionesContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'funciones.html',
                        controller: ['$scope', 'funciones', function ($scope, funciones) {
                                $scope.funcionesRecords = funciones.data;
                            }]
                    }
                }
            }).state('funcionesList', {
                url: '/list',
                parent: 'funciones',
                views: {
                    'listView': {
                      controller: 'funcionesCtrl',
                      controllerAs: 'ctrl',
                      templateUrl: basePath + 'funciones.list.html'
                    }
                }
            }).state('funcionCreate', {
                url: '/create',
                parent: 'funciones',
                views: {
                    'funcionView': {
                        controller: 'funcionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'funciones.create.html'
                    }
                }
            }).state('funcionEdit', {
                url: '/{funcionId:int}/edit',
                parent: 'funciones',
                views: {
                    'funcionView': {
                        controller: 'funcionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'funciones.create.html'
                    }
                }
            }).state('funcionDetail', {
                url: '/{funcionId:int}/detail',
                parent: 'funciones',
                param: {
                    funcionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'funciones.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentFuncion = $scope.funcionesRecords[$params.funcionId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);
