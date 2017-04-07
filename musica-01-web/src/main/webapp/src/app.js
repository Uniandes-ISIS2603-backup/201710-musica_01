(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        // Internal modules dependencies       
        'musicoModule',
        'festivalModule',
        'boletaModule',
        'funcionModule',
        'ciudadModule',
        'generoModule',
        'lugarModule',
        'usuarioModule'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
