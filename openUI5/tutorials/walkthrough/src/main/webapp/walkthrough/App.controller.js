sap.ui.define([
    "sap/ui/core/mvc/Controller", "sap/m/MessageToast", "sap/ui/model/json/JSONModel"
], function(Controller, MessageToast, JSONModel) {
    "use strict";
    var privateSayHello = function() {
        MessageToast.show("Hello World");
    };
    var privateInit = function() {
        var oData = {
            "recipient": {
                "name": "World"
            }
        };
        var oModel = new JSONModel(oData);
        this.getView().setModel(oModel);
    };
    var theController = Controller.extend("openui5.tutorial.walkthrough.App", {
        onShowHello: privateSayHello,
        onInit: privateInit
    });
    return theController;
});
