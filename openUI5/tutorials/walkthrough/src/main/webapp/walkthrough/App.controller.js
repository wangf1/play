sap.ui.define([
    "sap/ui/core/mvc/Controller"
], function(Controller) {
    "use strict";
    var privateSayHello = function() {
        alert("Hello World");
    };
    var theController = Controller.extend("openui5.tutorial.walkthrough.App", {
        onShowHello: privateSayHello
    });
    return theController;
});
