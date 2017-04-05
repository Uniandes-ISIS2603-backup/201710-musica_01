(function (ng) {
    var mod = ng.module("usuarioModule", ['ui.router']);
    mod.constant("usuariosContext", "api/usuarios");
    mod.config(['$stateProvider'
                , '$urlRouterProvider'
                , function ($stateProvider, $urlRouterProvider) {
                    var basePath = 'src/modules/usuarios/';
                    $urlRouterProvider.otherwise("/ciudadesList");
                    self = this;
                    $stateProvider.state('usuarios', {
                        url: '/usuarios'
                        , abstract: true
                        , resolve: {
                            ciudades: ['$http'
                                    , 'usuariosContext'
                                    , '$stateParams'
                                    , function ($http, usuariosContext) {
                                        return $http.get('usuariosContext');
                            }]
                        }
                        , views: {
                            'mainView': {
                                templateUrl: basePath + 'ciudades.html'
                                , controller: ['$scope'
                                            , 'usuarios'
                                            , function ($scope, usuarios) {
                                                $scope.ciudades = ciudades.data;
                                            }]
                            }
                        }
                    }).state('ciudadesList', {
                        url: '/list'
                        , parent: 'usuarios'
                        , views: {
                            'listView': {
                                templateUrl: basePath + 'ciudades.list.html'
                            }
                        }
                    });
                }]);
})(window.angular);