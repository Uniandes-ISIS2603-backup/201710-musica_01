/*
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
(function (ng) {
    var mod = ng.module("generoModule", ['ui.router']);
    mod.constant("generosContext", "api/generos");
    mod.constant("festivalesContext", "api/festivales");
    mod.constant("musicosContext", "api/musicos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/generos/';
            $urlRouterProvider.otherwise("/generosList");
            self = this;
             $stateProvider.state('generos', {
                url: '/generos',
                abstract: true,
                resolve: {
                    generos: ['$http', 'generosContext', function ($http, generosContext) {
                            return $http.get(generosContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'generos.html',
                        controller: ['$scope', 'generos', function ($scope, generos) {
                                $scope.generosRecords = generos.data;
                            }]
                    }
                }
            }).state('generosList', {
                url: '/list',
                parent: 'generos',
                views: {
                    'listView': {
                      controller: 'generosCtrl',
                      controllerAs: 'ctrl',
                      templateUrl: basePath + 'generos.list.html'
                    }
                }
            }).state('generoCreate', {
                url: '/create',
                parent: 'generos',
                views: {
                    'generoView': {
                        controller: 'generosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'generos.create.html'
                    }
                }
            }).state('generoEdit', {
                url: '/{generoId:int}/edit',
                parent: 'generos',
                views: {
                    'generoView': {
                        controller: 'generosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'generos.create.html'
                    }
                }
            }).state('generoDetail', {
                url: '/{generoId:int}/detail',
                parent: 'generos',
                param: {
                    generoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'generos.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentGenero = $scope.generosRecords[$params.generoId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);
