/*
 * Copyright (c) 2017 jc.bustamante143.
 * Realizado por el grupo TumBoleta - Uniandes 2017.
 */
(function (ng) {
    var mod = ng.module("musicoModule");

    mod.controller("musicosCtrl", ['$scope', '$state', '$stateParams', '$http', 'musicosContext', 'generosContext', 'funcionesContext', function ($scope, $state, $stateParams, $http, context, generosContext, funcionesContext) {

            // inicialmente el listado de musicos está vacio
            $scope.records = {};
            // carga las musicos
            $http.get(context).then(function (response) {
                $scope.records = response.data;
            }, responseError);

            $http.get(generosContext).then(function (response) {
                       $scope.generos = response.data;
                   });

           $http.get(funcionesContext).then(function (response) {
                        $scope.funciones = response.data;
                   });

            // el controlador recibió un musicoId ??
            // revisa los parámetros (ver el :musicoId en la definición de la ruta)
            if ($stateParams.musicoId !== null && $stateParams.musicoId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.musicoId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        }, responseError);

                // el controlador no recibió un musicoId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                  id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                  nombre: '',
                  trayectoria: '' ,
                  requerimientoSonido: '',
                  requerimientoCapacidad: '',
                  generoMusico: {},
                  funcionesMusico: {}
                };

                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('musicosList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('musicosList');
                            }, responseError);
                }
                ;
            }

            this.deleteRecord = function(id){
                return $http.delete(context + "/"+ id)
                        .then(function(){
                            $state.reload();
                },responseError);
            };

            // -----------------------------------------------------------------
            // Funciones para manejar las fechas

            $scope.popup = {
                opened: false
            };
            $scope.dateOptions = {
                dateDisabled: disabled,
                formatYear: 'yy',
                maxDate: new Date(2020, 5, 22),
                minDate: new Date(),
                startingDay: 1
            };

            this.today = function () {
                $scope.dt = new Date();
            };
            this.today();

            this.clear = function () {
                $scope.dt = null;
            };
            this.setDate = function (year, month, day) {
                $scope.dt = new Date(year, month, day);
            };

            this.open = function () {
                $scope.popup.opened = true;
            };

            function disabled(data) {
                var date = data.date,
                        mode = data.mode;
                return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
            }


            // -----------------------------------------------------------------
            // Funciones para manejra los mensajes en la aplicación


            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            // Función showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            this.showSuccess = function (msg) {
                showMessage(msg, "success");
            };

            var self = this;
            function responseError(response) {

                self.showError(response.data);
            }
        }]);

})(window.angular);
