if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = 'Page';
	$.fn.pagination.defaults.afterPageText = 'of {pages}';
	$.fn.pagination.defaults.displayMsg = 'Displaying {from} to {to} of {total} items';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = 'Processing, please wait ...';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = 'Ok';
	$.messager.defaults.cancel = 'Cancel';
}
$.map(['validatebox','textbox','passwordbox','filebox','searchbox',
		'combo','combobox','combogrid','combotree',
		'datebox','datetimebox','numberbox',
		'spinner','numberspinner','timespinner','datetimespinner'], function(plugin){
	if ($.fn[plugin]){
		$.fn[plugin].defaults.missingMessage = 'This field is required.';
	}
});
if ($.fn.validatebox){
	$.fn.validatebox.defaults.rules.email.message = 'Please enter a valid email address.';
	$.fn.validatebox.defaults.rules.url.message = 'Please enter a valid URL.';
	$.fn.validatebox.defaults.rules.length.message = 'Please enter a value between {0} and {1}.';
	$.fn.validatebox.defaults.rules.remote.message = 'Please fix this field.';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['S','M','T','W','T','F','S'];
	$.fn.calendar.defaults.months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = 'Today';
	$.fn.datebox.defaults.closeText = 'Close';
	$.fn.datebox.defaults.okText = 'Ok';
	$.fn.datebox.defaults.formatter = function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	};
	$.fn.datebox.defaults.parser = function(s){
		if (!s) return new Date();
		var ss = s.split('-');
		var y = parseInt(ss[0],10);
		var m = parseInt(ss[1],10);
		var d = parseInt(ss[2],10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
			return new Date(y,m-1,d);
		} else {
			return new Date();
		}
	};
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText
	});
}
if ($.messager){
	$.messager.defaults.msg = "Please wait......";
}

var locale = {
	selectOneRow: "Please select one row......",
	errorRow: 'Edit Line {row} Validate Fail.',
	enterInboundNumber: "enter inboundNumber.",
	enterSku:'enter sku.',
	enterQuantity:'enter receive quantity.',
	submitRowIsNull:'submit row is null.',
	noChange:'No data changes.',
	statusError:'Status error',
	ownerRequired: 'Owner required',
	selectOneSku:'Please select a sku.',
	selectTwoMoreRows:'Please select more than two recrods.',
	inputTargetLocation:'Please input target location info.',
	saveFirst:'Please Save Record First.',
	selectTemplate:'Please Select Template.',
	selectFile:'Please Select File.',
	selectOneRole:'Please select one Role.',
	selectCompany:'Please select one Company.',
	
	submitSuccess:'submit success.',
	exportTip:'Export In Backgound.',
	addSuccess:'add Success.',
	importTip:'Import In Backgound.',
	saveSuccess:'Save Success',
	uploadSuccess:'upload success',
	changePasswordSuccess:'Change Password Success.',
	uploadAppName:'upload app',
	
	exitConfirm:'Exit the current page ?',
	exportExcel:'Export Excel By Current conditions?',
	closeAll:'Close All Tabs?',
	goOn:"Go on ?",
	deleteOneRow:'Confirm Delete {row} Recored ?',
	optOneRow:'Confirm {opt} {row} Recored ?',
	lineConfirm:'Confirm {type} {row} ?',
	putawayRows:'Confirm Putaway {row} Recored ?',
	cancelRows:'Confirm Cancel {row} Recored ?',
	unLockRows:'Confirm unLock {row} Recored ?',
	clearAll:'Clear All Permission ? ',
	offLine:'Confirm Off line {row} Recored ?',
	initWarehouse:'Confirm Init {row} Warehouse Recored ?',
	warehouseAction:'Confirm {action} {row} Warehouse Recored ?',
	
	inboundNewStatusPrint: 'Only New、InReceive Can Print',
	weightGrossMorethenNet: 'gross is greater than net weight',
	lessThenZero: 'Value cannot be less than 0',
	characterornumber: 'Only Character or Number',
	locationprintall: 'Print All Location?'
}
if ($.locale){
	$.extend($.locale,locale);
}else{
	$.locale = locale;
}
