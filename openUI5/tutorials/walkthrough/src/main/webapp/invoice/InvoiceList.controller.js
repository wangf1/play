sap.ui.define([
    "sap/ui/core/mvc/Controller", "sap/ui/model/json/JSONModel", "openui5/tutorial/wt/model/formatter"
], function(Controller, JSONModel, formatter) {
    "use strict";
    var init = function() {
        var oViewModel = new JSONModel({
            currency: "EUR"
        });
        this.getView().setModel(oViewModel, "view");
    };
    var controller = Controller.extend("openui5.tutorial.wt.invoice.InvoiceList", {
        formatter: formatter,
        onInit: init
    });
    return controller;
});
