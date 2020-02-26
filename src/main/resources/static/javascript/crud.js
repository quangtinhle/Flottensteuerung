/**
 * Die XSRF-Tokens müssen bei jedem Request zum Server geschickt werden, da ansonsten der Spring-Security-Layer
 * entsprechende Requests herausfilter bzw. nicht erlaubt.
 */



function CRUDController(endpoint, gridFields, set, get) {
	var self = this;
	self.endpoint = endpoint;
		
	var dialog = null;
	var dialogValidator = null;
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var formSubmitHandler = $.noop;
	var setControlData = set;
	var getControlData = get;
	var jsGridFields = gridFields;
	jsGridFields.push({type:"control", modeSwitchButton:false, editButton:false, headerTemplate: function() {
    	return $("<button>").attr("type", "button").text("Add").on("click", function() {
    		self.showDetailsDialog("Add", {});
    	})
	}});
	
	$.each(jsGridFields, function(e) {
		var element = jsGridFields[e];
		if (element.items != null) {			
			var ui = $("#" + element.uiRef);
			ui.append($("<option>", {value: "", text: "(Auswählen)"}));
			for (var item in element.items) {
				var option = element.items[item];
				ui.append($("<option></option>")
					.attr("value", option[element.valueField])
					.text(option[element.textField]));
			}
		}
	});
	
	$(document).ajaxSend(function(event, request, options) {
		request.setRequestHeader(header, token);
	});
	
	var ajaxLoader = {
		loadData: function(filter) {
			var ret = $.Deferred();
			$.ajax({type: "GET", url: self.endpoint, contentType: "application/json", dataType:"JSON", data:filter, success: function(retData) {					
				var jsGridData = {data: retData, itemsCount: retData.length};
				ret.resolve(retData);
			}});			
			return ret.promise();
		},
		insertItem: function(args) {		
			var ret = $.Deferred();
			$.ajax({type: "POST", url:self.endpoint, contentType: "application/json", dataType:"JSON", data:JSON.stringify(args), success: function(retData) {
				alert("Der Datensatz wurde erfolgreich erstellt.");
				ret.resolve(retData);
			}, error: function(e, status, errorThrown) {
				alert(e.responseText);
				ret.resolve(null);
			}});
			return ret.promise();
		},
 		updateItem: function(args) {
			$.ajax({type: "PUT", url:self.endpoint + "/" + args.id, contentType: "application/json", dataType:"JSON", data:JSON.stringify(args), success: function(retData) {
				alert("Der Datensatz wurde erfolgreich geändert.");
			}, error: function(e) {
				alert(e.responseText);
			}});
		},
		deleteItem: function(args) {
			$.ajax({type: "DELETE", url: self.endpoint + "/" + args.id, contentType: "application/json", dataType:"JSON", success: function(retData) {
				alert("Der Datensatz wurde erfolgreich gelöscht.");
			}, error: function(e) {
				alert(e.responseText);
			}});
		}
	};
	
	var init = function() {
		//1. Zunächst wird das Grid erstellt
	    $("#jsGrid").jsGrid({
	        width: "100%",
	        height: "600px",
	 
	        inserting: false,
	        editing: false,
	        sorting: true,
	        paging: false,
	        selecting: true,
	        pageLoading: false,
	        filtering: false,
	        autoload: true,
	        
	        loadIndication: true,
	    	loadIndicationDelay: 1000,
	    	loadMessage: "Lade Daten. Bitte warten...",
	 
	    	rowDoubleClick: function(args) {
	    		self.showDetailsDialog("Edit", args.item);
	    	},
	    	
	        controller: ajaxLoader,
	        noDataContent: "Keine Daten verf&uuml;gbar",
	        deleteConfirm: "Sind Sie sicher, dass Sie den Datensatz löschen möchten?",
	 
	        fields: jsGridFields
	    });
	    
	    //2. jQuery UI Dialog initialisieren, welches die Erfassungsmaske darstellt.
	    dialog = $("#detailsDialog").dialog({
	        autoOpen: false,
	        width: 300,
	        height: 400,
	        resizable:false,
	        modal: true,
	        buttons: {
	        	"Speichern": function() {
	        		formSubmitHandler();
	        	},
	        	Cancel: function() {
	        		dialog.dialog("close");	
	        	}
	        },
	        close: function() {
	        	dialogValidator.resetForm();
	            $("#detailsForm").find(".error").removeClass("error");
	        }
	    });
	    
	    //3. Validatoren aktivieren
	    dialogValidator = $("#detailsForm").validate({
	        rules: {
	            login: "required",
	        },
	        messages: {
	            login: "Please enter name",
	        },
	        submitHandler: function() {
	            formSubmitHandler();
	        }
	    });
    	dialogValidator.showErrors();
	};
	
	self.save = function(client, isNew) {
    	if (!$("#detailsForm").valid())
    		return;
    	
    	getControlData(client);
    	
        $("#jsGrid").jsGrid(isNew ? "insertItem" : "updateItem", client);		 
        $("#detailsDialog").dialog("close");
	};
	
	self.showDetailsDialog = function(dialogType, subject) {
		setControlData(subject);
		formSubmitHandler = function() {
			self.save(subject, dialogType === "Add");
		};
		
        dialog.dialog("option", "title", dialogType + " Client").dialog("open");
	};
	
	init();
}


