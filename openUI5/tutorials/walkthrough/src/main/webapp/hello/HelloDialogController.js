sap.ui.define([
    "sap/ui/base/Object", "openui5/tutorial/wt/helper/styleHelper"
], function(Object, styleHelper) {
    "use strict";
    var sayHelloDialog;
    var buildSayHelloDialog = function(theController) {
        if (sayHelloDialog) {
            return;
        }
        sayHelloDialog = sap.ui.xmlfragment("openui5.tutorial.wt.hello.HelloDialog", theController);
    };
    var openSayHelloDialog = function(oView) {
        buildSayHelloDialog(this);
        // forward compact/cozy style into Dialog
        jQuery.sap.syncStyleClass(styleHelper.getContentDensityClass(), oView, sayHelloDialog);
        oView.addDependent(sayHelloDialog);
        sayHelloDialog.open();
    };
    var closeSayHelloDialog = function() {
        sayHelloDialog.close();
    };
    var TheObjectConstructor = Object.extend("openui5.tutorial.wt.hello.HelloDialogController", {
        open: openSayHelloDialog,
        close: closeSayHelloDialog
    });
    // Singleton pattern and static property pattern
    var singletonInstance = new TheObjectConstructor();
    TheObjectConstructor.instance = singletonInstance;
    return TheObjectConstructor;
});
