sap.ui.define([
    "sap/ui/core/mvc/Controller", "sap/ui/core/routing/History", "sap/m/MessageToast", "sap/ui/model/json/JSONModel"
], function(Controller, History, MessageToast, JSONModel) {
    "use strict";
    // I return a closure in order to get a reference of the controller
    var createObjectMatchedEventHandler = function(controller) {
        var eventHandler = function(oEvent) {
            controller.getView().bindElement({
                path: "/" + oEvent.getParameter("arguments").invoicePath,
                model: "invoice"
            });
        };
        return eventHandler;
    };
    var init = function() {
        var oViewModel = new JSONModel({
            currency: "EUR"
        });
        this.getView().setModel(oViewModel, "view");
        var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
        var onObjectMatched = createObjectMatchedEventHandler(this);
        oRouter.getRoute("detail").attachPatternMatched(onObjectMatched, this);
    };
    var onNavBack = function() {
        var oHistory = History.getInstance();
        var sPreviousHash = oHistory.getPreviousHash();

        if (sPreviousHash !== undefined) {
            window.history.go(-1);
        } else {
            var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
            oRouter.navTo("overview", true);
        }
    };
    var onRatingChange = function(oEvent) {
        var fValue = oEvent.getParameter("value");
        var oResourceBundle = this.getView().getModel("i18n").getResourceBundle();
        MessageToast.show(oResourceBundle.getText("ratingConfirmation", [
            fValue
        ]));
    };
    return Controller.extend("openui5.tutorial.wt.main.Detail", {
        onInit: init,
        onNavBack: onNavBack,
        onRatingChange: onRatingChange
    });
});
