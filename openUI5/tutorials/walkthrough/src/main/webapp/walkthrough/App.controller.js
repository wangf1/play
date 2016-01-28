sap.ui.define([
    "sap/ui/core/mvc/Controller", "sap/m/MessageToast"
], function(Controller, MessageToast) {
    "use strict";
    var privateSayHello = function() {
        var oBundle = this.getView().getModel("i18n").getResourceBundle();
        var sRecipient = this.getView().getModel().getProperty("/recipient/name");
        var sMsg = oBundle.getText("helloMsg", [
            sRecipient
        ]);
        MessageToast.show(sMsg);
    };
    var theController = Controller.extend("openui5.tutorial.wt.walkthrough.App", {
        onShowHello: privateSayHello
    });
    return theController;
});
