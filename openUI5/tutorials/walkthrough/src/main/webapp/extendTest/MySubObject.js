sap.ui.define([
    "openui5/tutorial/wt/extendTest/MyBaseObject"
], function(MyBaseObject) {
    "use strict";

    function privateFunction2() {
        console.log("privateFunction2 in sub object");
    }

    var mySubObject = MyBaseObject.extend("openui5.tutorial.wt.extendTest.MySubObject");

    // Override parent class function
    mySubObject.prototype.parentFunction2 = function() {
        // Call super method
        MyBaseObject.prototype.parentFunction2.apply(this);
        // sub class specific logic
        console.log("sub class specific logic for parentFunction2");
        privateFunction2();
    };

    return mySubObject;

});
