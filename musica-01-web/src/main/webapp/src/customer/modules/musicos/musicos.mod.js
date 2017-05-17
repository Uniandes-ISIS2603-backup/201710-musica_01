/*
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
(function (ng) {
    var mod = ng.module("musicoCModule", ['ui.router']);
    mod.constant("musicosContext", "api/musicos");
    mod.constant("generosContext", "api/generos");
    mod.constant("funcionesContext", "api/funciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/customer/modules/musicos/';
            $urlRouterProvider.otherwise("/musicosCList");
            self = this;
             $stateProvider.state('musicos', {
                url: '/musicos',
                abstract: true,
                resolve: {
                    musicos: ['$http', 'musicosContext', function ($http, musicosContext) {
                            return $http.get(musicosContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'musicos.html',
                        controller: ['$scope', 'musicos', function ($scope, musicos) {
                                $scope.musicosRecords = musicos.data;
                            }]
                    }
                }
            }).state('musicosCList', {
                url: '/list',
                parent: 'musicos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'musicos.list.html'
                    }
                }
            }).state('musicoCDetail', {
                url: '/{musicoId:int}/detail',
                parent: 'musicos',
                param: {
                    musicoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'musicos.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentMusico = $scope.musicosRecords[$params.musicoId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);
