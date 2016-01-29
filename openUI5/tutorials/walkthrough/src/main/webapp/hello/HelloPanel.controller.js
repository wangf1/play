sap.ui.define([
    "sap/ui/core/mvc/Controller", "sap/m/MessageToast"
], function(Controller, MessageToast) {
    "use strict";
    var sayHelloDialog;
    var privateSayHello = function() {
        var oBundle = this.getView().getModel("i18n").getResourceBundle();
        var sRecipient = this.getView().getModel().getProperty("/recipient/name");
        var sMsg = oBundle.getText("helloMsg", [
            sRecipient
        ]);
        MessageToast.show(sMsg);
    };
    var buildSayHelloDialog = function(theController) {
        if (sayHelloDialog) {
            return;
        }
        sayHelloDialog = sap.ui.xmlfragment("openui5.tutorial.wt.hello.HelloDialog", theController);
        theController.getView().addDependent(sayHelloDialog);
    };
    var openSayHelloDialog = function() {
        buildSayHelloDialog(this);
        sayHelloDialog.open();
    };
    var closeSayHelloDialog = function() {
        sayHelloDialog.close();
    };
    var theController = Controller.extend("openui5.tutorial.wt.hello.HelloPanel", {
        onShowHello: privateSayHello,
        onOpenDialog: openSayHelloDialog,
        onCloseDialog: closeSayHelloDialog
    });
    return theController;
});
