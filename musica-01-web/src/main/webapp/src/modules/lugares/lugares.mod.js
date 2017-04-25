/* 
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
(function (ng) {
    var mod = ng.module("lugarModule", ['ui.router']);
    mod.constant("lugaresContext", "api/lugares");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/lugares/';
            $urlRouterProvider.otherwise("/lugaresList");
            self = this;
             $stateProvider.state('lugares', {
                url: '/lugares',
                abstract: true,
                resolve: {
                    lugares: ['$http', 'lugaresContext', function ($http, lugaresContext) {
                            return $http.get(lugaresContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'lugares.html',
                        controller: ['$scope', 'lugares', function ($scope, lugares) {
                                $scope.lugaresRecords = lugares.data;
                            }]
                    }
                }
            }).state('lugaresList', {
                url: '/list',
                parent: 'lugares',
                views: {
                    'listView': {
                        templateUrl: basePath + 'lugares.list.html'
                    }
                }
            }).state('lugarDetail', {
                url: '/{lugarId:int}/detail',
                parent: 'lugares',
                param: {
                    lugarId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'lugares.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentLugar = $scope.lugaresRecords[$params.lugarId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);
