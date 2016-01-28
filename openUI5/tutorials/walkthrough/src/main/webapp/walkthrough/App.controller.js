sap.ui.define([
    "sap/ui/core/mvc/Controller", "sap/m/MessageToast", "sap/ui/model/json/JSONModel", "sap/ui/model/resource/ResourceModel"
], function(Controller, MessageToast, JSONModel, ResourceModel) {
    "use strict";
    var privateSayHello = function() {
        var oBundle = this.getView().getModel("i18n").getResourceBundle();
        var sRecipient = this.getView().getModel().getProperty("/recipient/name");
        var sMsg = oBundle.getText("helloMsg", [
            sRecipient
        ]);
        MessageToast.show(sMsg);
    };
    var setJsonModel = function(thisController) {
        var oData = {
            "recipient": {
                "name": "World"
            }
        };
        var oModel = new JSONModel(oData);
        thisController.getView().setModel(oModel);
    };
    var setI18nModel = function(thisController) {
        var i18nModel = new ResourceModel({
            bundleName: "openui5.tutorial.i18n.i18n"
        });
        thisController.getView().setModel(i18nModel, "i18n");
    };
    var init = function() {
        // since init is called against this controller object, so "this" point to this controller object.
        // but for plain function call setJsonModel and setI18nModel, "this" will not point to this controller.
        var thisController = this;
        setJsonModel(thisController);
        setI18nModel(thisController);
    };
    var theController = Controller.extend("openui5.tutorial.walkthrough.App", {
        onShowHello: privateSayHello,
        onInit: init
    });
    return theController;
});
