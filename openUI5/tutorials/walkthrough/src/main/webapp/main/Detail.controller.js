sap.ui.define([
    "sap/ui/core/mvc/Controller", "sap/ui/core/routing/History"
], function(Controller, History) {
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
    return Controller.extend("openui5.tutorial.wt.main.Detail", {
        onInit: init,
        onNavBack: onNavBack
    });
});
