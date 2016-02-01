sap.ui.define([
    "sap/ui/core/mvc/Controller", "sap/ui/model/json/JSONModel"
], function(Controller, JSONModel) {
    "use strict";
    var init = function() {
        var oViewModel = new JSONModel({
            currency: "EUR"
        });
        this.getView().setModel(oViewModel, "view");
    };
    var controller = Controller.extend("openui5.tutorial.wt.invoice.InvoiceList", {
        onInit: init
    });
    return controller;
});
