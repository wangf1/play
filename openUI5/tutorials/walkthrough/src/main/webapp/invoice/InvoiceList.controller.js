sap.ui.define([
    "sap/ui/core/mvc/Controller", "sap/ui/model/json/JSONModel", "openui5/tutorial/wt/model/formatter", "sap/ui/model/Filter", "sap/ui/model/FilterOperator"
], function(Controller, JSONModel, formatter, Filter, FilterOperator) {
    "use strict";
    var init = function() {
        var oViewModel = new JSONModel({
            currency: "EUR"
        });
        this.getView().setModel(oViewModel, "view");
    };
    var onFilterInvoices = function(oEvent) {
        var aFilter = [];
        var sQuery = oEvent.getParameter("query");
        if (sQuery) {
            aFilter.push(new Filter("ProductName", FilterOperator.Contains, sQuery));
        }
        var oList = this.getView().byId("invoiceList");
        var oBinding = oList.getBinding("items");
        oBinding.filter(aFilter);
    };
    var controller = Controller.extend("openui5.tutorial.wt.invoice.InvoiceList", {
        formatter: formatter,
        onInit: init,
        onFilterInvoices: onFilterInvoices
    });
    return controller;
});
