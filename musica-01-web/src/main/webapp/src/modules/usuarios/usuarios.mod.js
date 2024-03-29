/*
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
(function (ng) {
    var mod = ng.module("usuarioModule", ['ui.router']);
    mod.constant("usuariosContext", "api/usuarios");
    mod.constant("festivalesContext", "api/festivales");
    mod.constant("boletasContext", "api/boletas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/usuarios/';
            $urlRouterProvider.otherwise("/usuariosList");
            self = this;
             $stateProvider.state('usuarios', {
                url: '/usuarios',
                abstract: true,
                resolve: {
                    usuarios: ['$http', 'usuariosContext', function ($http, usuariosContext) {
                            return $http.get(usuariosContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.html',
                        controller: ['$scope', 'usuarios', function ($scope, usuarios) {
                                $scope.usuariosRecords = usuarios.data;
                            }]
                    }
                }
            }).state('usuariosList', {
                url: '/list',
                parent: 'usuarios',
                views: {
                    'listView': {
                      controller: 'usuariosCtrl',
                      controllerAs: 'ctrl',
                      templateUrl: basePath + 'usuarios.list.html'
                    }
                }
            }).state('usuarioCreate', {
                url: '/create',
                parent: 'usuarios',
                views: {
                    'usuarioView': {
                        controller: 'usuariosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuarios.create.html'
                    }
                }
            }).state('usuarioEdit', {
                url: '/{usuarioId:int}/edit',
                parent: 'usuarios',
                views: {
                    'usuarioView': {
                        controller: 'usuariosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuarios.create.html'
                    }
                }
            }).state('usuarioDetail', {
                url: '/{usuarioId:int}/detail',
                parent: 'usuarios',
                param: {
                    usuarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'usuarios.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentUsuario = $scope.usuariosRecords[$params.usuarioId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);
