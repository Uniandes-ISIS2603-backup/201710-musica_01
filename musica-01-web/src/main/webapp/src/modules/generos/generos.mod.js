(function (ng) {
    var mod = ng.module("generoModule", ['ui.router']);
    mod.constant("generosContext", "api/generos");
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
                        templateUrl: basePath + 'generos.list.html'
                    }
                }
            });
        }]);
})(window.angular);
