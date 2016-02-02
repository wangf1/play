sap.ui.define([
    "sap/ui/core/mvc/Controller", "openui5/tutorial/wt/hello/HelloDialogController", "openui5/tutorial/wt/helper/styleHelper"
], function(Controller, HelloDialogController, styleHelper) {
    "use strict";

    var init = function() {
        this.getView().addStyleClass(styleHelper.getContentDensityClass());
    };

    var theController = Controller.extend("openui5.tutorial.wt.App", {
        onInit: init,
        onOpenDialog: function() {
            HelloDialogController.instance.open(this.getView());
        }
    });
    return theController;
});
