(function (ng) {
    var mod = ng.module("ciudadModule", ['ui.router']);
    mod.constant("ciudadesContext", "api/ciudades");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ciudades/';
            $urlRouterProvider.otherwise("/ciudadesList");
            self = this;
             $stateProvider.state('ciudades', {
                url: '/ciudades',
                abstract: true,
                resolve: {
                    ciudades: ['$http', function ($http) {
                            return $http.get('data/books.json');
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
                        templateUrl: basePath + 'ciudades.list.html'
                    }
                }
            });
        }]);
})(window.angular);
