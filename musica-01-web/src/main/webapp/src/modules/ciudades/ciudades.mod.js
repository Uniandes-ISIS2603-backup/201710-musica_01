/*
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
(function (ng) {
    var mod = ng.module("ciudadModule", ['ui.router']);
    mod.constant("ciudadesContext", "api/ciudades");
    mod.constant("lugaresContext", "api/lugares");
    mod.constant("festivalesContext", "api/festivales");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ciudades/';
            $urlRouterProvider.otherwise("/ciudadesList");
            self = this;
             $stateProvider.state('ciudades', {
                url: '/ciudades',
                abstract: true,
                resolve: {
                    ciudades: ['$http', 'ciudadesContext', function ($http, ciudadesContext) {
                            return $http.get(ciudadesContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ciudades.html',
                        controller: ['$scope', 'ciudades', function ($scope, ciudades) {
                                $scope.ciudadesRecords = ciudades.data;
                            }]
                    }
                }
            }).state('ciudadesList', {
                url: '/list',
                parent: 'ciudades',
                views: {
                    'listView': {
                      controller: 'ciudadesCtrl',
                      controllerAs: 'ctrl',
                      templateUrl: basePath + 'ciudades.list.html'
                    }
                }
            }).state('ciudadCreate', {
                url: '/create',
                parent: 'ciudades',
                views: {
                    'ciudadView': {
                        controller: 'ciudadesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'ciudades.create.html'
                    }
                }
            }).state('ciudadEdit', {
                url: '/{ciudadId:int}/edit',
                parent: 'ciudades',
                views: {
                    'ciudadView': {
                        controller: 'ciudadesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'ciudades.create.html'
                    }
                }
            }).state('ciudadDetail', {
                url: '/{ciudadId:int}/detail',
                parent: 'ciudades',
                param: {
                    ciudadId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ciudades.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentCiudad = $scope.ciudadesRecords[$params.ciudadId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);
