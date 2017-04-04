(function (ng) {
    var mod = ng.module("musicoModule", ['ui.router']);
    mod.constant("musicosContext", "api/musicos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/musicos/';
            $urlRouterProvider.otherwise("/musicosList");
            self = this;
             $stateProvider.state('musicos', {
                url: '/musicos',
                abstract: true,
                resolve: {
                    musicos: ['$http', function ($http) {
                            return $http.get('data/books.json');
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
            }).state('musicosList', {
                url: '/list',
                parent: 'musicos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'musicos.list.html'
                    }
                }
            });
        }]);
})(window.angular);
