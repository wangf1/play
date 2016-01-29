sap.ui.define([
    "sap/ui/core/mvc/Controller", "openui5/tutorial/wt/hello/HelloDialogController"
], function(Controller, HelloDialogController) {
    "use strict";
    var theController = Controller.extend("openui5.tutorial.wt.App", {
        onOpenDialog: function() {
            HelloDialogController.instance.open(this.getView());
        }
    });
    return theController;
});
