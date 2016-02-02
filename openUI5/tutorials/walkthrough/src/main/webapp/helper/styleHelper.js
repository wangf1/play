sap.ui.define([
    "sap/ui/Device"
], function(Device) {
    var sContentDensityClass;
    var getContentDensityClass = function() {
        if (!sContentDensityClass) {
            if (Device.support.touch) {
                sContentDensityClass = "sapUiSizeCompact";
            } else {
                sContentDensityClass = "sapUiSizeCozy";
            }
        }
        return sContentDensityClass;
    };
    var toExpose = {
        getContentDensityClass: getContentDensityClass
    };
    return toExpose;
});
