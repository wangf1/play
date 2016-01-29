sap.ui.define([
    "sap/ui/core/mvc/Controller", "sap/m/MessageToast", "openui5/tutorial/wt/hello/HelloDialogController"
], function(Controller, MessageToast, HelloDialogController) {
    "use strict";
    var privateSayHello = function() {
        var oBundle = this.getView().getModel("i18n").getResourceBundle();
        var sRecipient = this.getView().getModel().getProperty("/recipient/name");
        var sMsg = oBundle.getText("helloMsg", [
            sRecipient
        ]);
        MessageToast.show(sMsg);
    };
    var openSayHelloDialog = function() {
        var helloDialog = HelloDialogController.instance;
        helloDialog.open(this.getView());
    };
    var TheControllerConstructor = Controller.extend("openui5.tutorial.wt.hello.HelloPanel", {
        onShowHello: privateSayHello,
        onOpenDialog: openSayHelloDialog
    });
    return TheControllerConstructor;
});
