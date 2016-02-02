sap.ui.define([
    "sap/ui/core/mvc/Controller"
], function(Controller) {
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

    return Controller.extend("openui5.tutorial.wt.main.Detail", {
        onInit: init
    });
});
