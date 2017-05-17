/*
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
(function (ng) {
    var mod = ng.module("ciudadCModule", ['ui.router']);
    mod.constant("ciudadesContext", "api/ciudades");
    mod.constant("lugaresContext", "api/lugares");
    mod.constant("festivalesContext", "api/festivales");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/customer/modules/ciudades/';
            $urlRouterProvider.otherwise("/ciudadesCList");
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
            }).state('ciudadesCList', {
                url: '/list',
                parent: 'ciudades',
                views: {
                    'listView': {
                        templateUrl: basePath + 'ciudades.list.html'
                    }
                }
            }).state('ciudadCDetail', {
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
