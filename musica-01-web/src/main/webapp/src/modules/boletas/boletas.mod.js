/*
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
(function (ng) {
    var mod = ng.module("boletaModule", ['ui.router']);
    mod.constant("boletasContext", "api/boletas");
    mod.constant("usuariosContext", "api/usuarios");
    mod.constant("funcionesContext", "api/funciones");
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
            }).state('boletaCreate', {
                url: '/create',
                parent: 'boletas',
                views: {
                    'boletaView': {
                        controller: 'boletasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'boletas.create.html'
                    }
                }
            }).state('boletaEdit', {
                url: '/{boletaId:int}/edit',
                parent: 'boletas',
                views: {
                    'boletaView': {
                        controller: 'boletasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'boletas.create.html'
                    }
                }
            }).state('boletaDetail', {
                url: '/{boletaId:int}/detail',
                parent: 'boletas',
                param: {
                    boletaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'boletas.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentBoleta = $scope.boletasRecords[$params.boletaId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);
