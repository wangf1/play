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
    var buildSayHelloDialog = function(theController) {
        var oDialog = sap.ui.xmlfragment("openui5.tutorial.wt.hello.HelloDialog");
        theController.getView().addDependent(oDialog);
        return oDialog;
    };
    var openSayHelloDialog = function() {
        var oDialog = buildSayHelloDialog(this);
        oDialog.open();
    };
    var theController = Controller.extend("openui5.tutorial.wt.hello.HelloPanel", {
        onShowHello: privateSayHello,
        onOpenDialog: openSayHelloDialog
    });
    return theController;
});
