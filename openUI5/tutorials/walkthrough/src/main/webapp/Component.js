sap.ui.define([
    "sap/ui/core/UIComponent", "sap/ui/model/json/JSONModel", "sap/ui/model/resource/ResourceModel"
], function(UIComponent, JSONModel, ResourceModel) {
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
    var setI18nModel = function(thisController) {
        var i18nModel = new ResourceModel({
            bundleName: "openui5.tutorial.i18n.i18n"
        });
        thisController.setModel(i18nModel, "i18n");
    };
    var privateInit = function(thisController) {
        setJsonModel(thisController);
        setI18nModel(thisController);
    };
    var theComponent = UIComponent.extend("openui5.tutorial.Component", {
        metadata: {
            rootView: "openui5.tutorial.walkthrough.App"
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
