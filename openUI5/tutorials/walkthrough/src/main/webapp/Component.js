sap.ui.define([
    "sap/ui/core/UIComponent", "sap/ui/model/json/JSONModel", "sap/ui/model/resource/ResourceModel", "sap/ui/Device"
], function(UIComponent, JSONModel, Device) {
    "use strict";
    var setJsonModel = function(thisController) {
        var oData = {
            "recipient": {
                "name": "World"
            }
        };
        var oModel = new JSONModel(oData);
        thisController.setModel(oModel);
    };
    var privateInit = function(thisController) {
        setJsonModel(thisController);
        // create the views based on the url/hash
        thisController.getRouter().initialize();
        // set device model
        var oDeviceModel = new JSONModel(Device);
        oDeviceModel.setDefaultBindingMode("OneWay");
        thisController.setModel(oDeviceModel, "device");
    };
    var theComponent = UIComponent.extend("openui5.tutorial.wt.Component", {
        metadata: {
            manifest: "json"
        },
        init: function() {
            // call the init function of the parent
            UIComponent.prototype.init.apply(this, arguments);
            var thisController = this;
            privateInit(thisController);
        }
    });
    return theComponent;
});
