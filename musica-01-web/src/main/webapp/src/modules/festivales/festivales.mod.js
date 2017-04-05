(function (ng) {
    var mod = ng.module("festivalModule", ['ui.router']);
    mod.constant("festivalesContext", "api/festivales");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/festivales/';
            $urlRouterProvider.otherwise("/festivalesList");

            $stateProvider.state('festivales', {
                url: '/festivales',
                abstract: true,
                resolve: {
                    books: ['$http', function ($http) {
                            return $http.get('data/festivales.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'festivales.html',
                        controller: ['$scope', 'festivales', function ($scope, books) {
                                $scope.festivalesRecords = festivales.data;
                            }]
                    }
                }
            }).state('festivalesList', {
                url: '/list',
                parent: 'festivales',
                views: {
                    'listView': {
                        templateUrl: basePath + 'festivales.list.html'
                    }
                }
            }).state('festivalDetail', {
                url: '/{festivalId:int}/detail',
                parent: 'festivales',
                param: {
                    festivalId: null
                },
                views: {
                   
                    'detailView': {
                        templateUrl: basePath + 'festivales.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentfestival = $scope.festivalesRecords[$params.festivalId-1];
                            }]
                    }

                }

            });
        }]);
})(window.angular);
