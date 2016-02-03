sap.ui.jsview("openui5.tutorial.wt.invoice.InvoiceTable", (function() {

    var getControllerName = function() {
        return "openui5.tutorial.wt.invoice.InvoiceList";
    };
    var createTableHeaderToolBar = function(oController) {
        var toolBar = new sap.m.Toolbar({
            content: [
                new sap.m.Title({
                    text: "{i18n>invoiceListTitle}"
                }), new sap.m.ToolbarSpacer(), new sap.m.SearchField({
                    width: "50%",
                    search: oController.onFilterInvoices
                })
            ]
        });
        return toolBar;
    };
    var createTable = function(oController) {
        var table = new sap.m.Table({
            id: oController.createId("invoiceList"),
            headerText: "{i18n>invoiceListTitle}",
            width: "auto",
            items: {
                path: "invoice>/Invoices",
                sorter: {
                    path: "ShipperName",
                    group: true
                },
                template: new sap.m.ColumnListItem({
                    type: "Navigation",
                    press: oController.onPress,
                    cells: [
                        new sap.m.ObjectNumber({
                            number: "{invoice>Quantity}"
                        }), new sap.m.ObjectIdentifier({
                            title: "{invoice>ProductName}"
                        }), new sap.m.Text({
                            text: {
                                path: "invoice>Status",
                                /* Note: the formatter function by default bind to the event source, which will cause error when we want call
                                this.getView() or other controller method. So we should bind the function to the controller*/
                                /*According to UI5 document Essentials->Model View Controller (MVC) ->Views ->JS View, another approach to make the function bind to controller is:
                                 * press: [oController.handleButtonClicked, oController]. But his is not as clear as the function bind.*/
                                formatter: oController.formatter.statusText.bind(oController)
                            }
                        }), new sap.m.Text({
                            text: "{invoice>ShipperName}"
                        }), new sap.m.ObjectNumber({
                            number: {
                                parts: [
                                    {
                                        path: "invoice>ExtendedPrice"
                                    }, {
                                        path: "view>/currency"
                                    }
                                ],
                                type: "sap.ui.model.type.Currency",
                                formatOptions: {
                                    showMeasure: false
                                }
                            },
                            unit: "{view>/currency}",
                            state: "{= ${invoice>ExtendedPrice} > 50 ? 'Error' : 'Success' }"
                        })
                    ]
                })
            },
            headerToolbar: createTableHeaderToolBar(oController),
            columns: [
                new sap.m.Column({
                    hAlign: "Right",
                    minScreenWidth: "Small",
                    demandPopin: true,
                    width: "4em",
                    header: [
                        new sap.m.Text({
                            text: "{i18n>columnQuantity}"
                        })
                    ]
                }), new sap.m.Column({
                    header: [
                        new sap.m.Text({
                            text: "{i18n>columnName}"
                        })
                    ]
                }), new sap.m.Column({
                    minScreenWidth: "Small",
                    demandPopin: true,
                    header: [
                        new sap.m.Text({
                            text: "{i18n>columnStatus}"
                        })
                    ]
                }), new sap.m.Column({
                    minScreenWidth: "Small",
                    demandPopin: false,
                    header: [
                        new sap.m.Text({
                            text: "{i18n>columnSupplier}"
                        })
                    ]
                }), new sap.m.Column({
                    hAlign: "Right",
                    header: [
                        new sap.m.Text({
                            text: "{i18n>columnPrice}"
                        })
                    ]
                })
            ]

        });
        table.addStyleClass("sapUiResponsiveMargin");
        return table;
    };
    var createContent = function(oController) {
        var table = createTable(oController);
        return table;
    };

    var view = {
        getControllerName: getControllerName,
        createContent: createContent
    };
    return view;
})());
