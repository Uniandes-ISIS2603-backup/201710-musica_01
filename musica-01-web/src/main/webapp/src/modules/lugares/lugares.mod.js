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
                    lugares: ['$http', function ($http) {
                            return $http.get('data/books.json');
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
            });
        }]);
})(window.angular);
