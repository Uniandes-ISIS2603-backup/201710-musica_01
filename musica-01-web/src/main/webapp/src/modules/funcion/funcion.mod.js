(function (ng) {
    var mod = ng.module("funcionModule", ['ui.router']);
    mod.constant("funcionesContext", "api/funciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/funciones/';
            $urlRouterProvider.otherwise("/funcionesList");

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
                        templateUrl: basePath + 'funciones.list.html'
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
                                $scope.currentfuncion = $scope.funcionesRecords[$params.funcionId-1];
                            }]
                    }

                }

            });
        }]);
})(window.angular);
