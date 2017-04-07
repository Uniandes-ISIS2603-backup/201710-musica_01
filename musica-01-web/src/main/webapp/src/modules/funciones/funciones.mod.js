(function (ng) {
    var mod = ng.module("funcionModule", ['ui.router']);
    mod.constant("funcionesContext", "api/funciones");
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
                        templateUrl: basePath + 'funciones.list.html'
                    }
                }
            });
        }]);
})(window.angular);