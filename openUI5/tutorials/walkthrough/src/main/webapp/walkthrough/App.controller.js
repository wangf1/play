sap.ui.define([
    "sap/ui/core/mvc/Controller", "sap/m/MessageToast"
], function(Controller, MessageToast) {
    "use strict";
    var privateSayHello = function() {
        MessageToast.show("Hello World");
    };
    var theController = Controller.extend("openui5.tutorial.walkthrough.App", {
        onShowHello: privateSayHello
    });
    return theController;
});
