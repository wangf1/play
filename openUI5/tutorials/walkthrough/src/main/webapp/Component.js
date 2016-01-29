sap.ui.define([
    "sap/ui/core/UIComponent", "sap/ui/model/json/JSONModel", "sap/ui/model/resource/ResourceModel"
], function(UIComponent, JSONModel) {
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
