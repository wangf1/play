sap.ui.define([
    "sap/ui/base/Object"
], function(UI5BaseObject) {
    "use strict";

    function privateFunction1() {
        console.log("privateFunction1");
    }

    var myBaseObject = UI5BaseObject.extend("openui5.tutorial.wt.extendTest.MyBaseObject");

    myBaseObject.prototype.parentFunction1 = function() {
        privateFunction1();
        console.log("parentFunction1");
    };

    myBaseObject.prototype.parentFunction2 = function() {
        console.log("parentFunction2");
    };

    return myBaseObject;

});
