showErrorFlag = false;
$.ajaxSetup({
	dataType: "json",
	type: 'post',
	cache: false,
	contentType: 'application/json',
	processData: false,
    headers: {
        "token": localStorage.getItem("token"),  //前后端分离时使用
    },
    crossDomain: true,
    xhrFields: {
	    withCredentials: true //跨域请求时使用
    },
    complete: function(xhr) {
    	if (this.url.indexOf('/find') > -1 && !showErrorFlag){
    		var json = xhr.responseText;
    		if (typeof(json) == 'string'){
    			json = JSON.parse(json);
    		}
    		if(json.code == '0'){
    			showError(json.msg);
    			showErrorFlag = true;
    			setTimeout(() => {
    				showErrorFlag = false;
				}, 1000);
    		}
    	}
    },
    beforeSend: function(xhr){
    	if (typeof(this.data) == 'object' && this.contentType == 'application/json')
    		this.data = JSON.stringify(this.data);
    },
    success: function(xhr, status, e){
    	
    },
    error: function(xhr, status, e){
    	closeLoading();
    	$.messager.alert('ERROR','[' + status + ']' + xhr.responseText + e ,'error');
    }
});

$.fn.extend({
	serializeObject : function (form) {
		var data = {}
		$.each(this.serializeArray(), function (index) {
			data[this['name']] = this['value'];
		});
		return data;
	}
});

function validate(){
	var valid = true;
	$('.easyui-validatebox').each(function(){
		var _valid = $(this).validatebox("isValid");
		if (!_valid){
			valid = false;
			return valid;
		}
	});
	return valid;
}


function loading(message){
	$.messager.progress({ 
       title: 'loading', 
       msg: message, 
       text: '' 
    });
}

function closeLoading(){
	$.messager.progress('close');
}

function showError(message,options,fun){
	if (typeof(options) == 'function') {
		$.messager.alert("Error", message, 'error', options);
	}else {
		if(typeof(options) == 'object'){
			var keys = Object.keys(options);
			keys.forEach(function(key){
				message = message.replace('{' + key + '}',options[key]);
			})
		}
		$.messager.alert("Error", message, 'error', fun);
	}
	$('.messager-button>a.l-btn').focus();
}

function showMsg(message, fun){
	$.messager.alert("info", message, 'info', fun);
}

function confirmMsg(message,options ,fun){
	if (typeof(options) == 'function') {
		$.messager.confirm("Confirm", message,options);
	}else {
		if(typeof(options) == 'object'){
			var keys = Object.keys(options);
			keys.forEach(function(key){
				message = message.replace('{' + key + '}',options[key]);
			})
			
			if($.locale.actions){
				var actions = Object.keys($.locale.actions);
				actions.forEach(function(action){
					message = message.replace(action,$.locale.actions[action]);
				});	
			}
		}
		$.messager.confirm("Confirm", message, fun);
	}
}

function returnData(data){
	if(data == undefined || data == '') {
		return {code:'0', msg: 'error'};
	}
	if (typeof(data) == 'string') {
		data = JSON.parse(data);
	}else if(typeof(data) == 'object'){
		
	}
	if (data.code == '5') { //no login
		//弹出登录框 to do
	}
	return data;
}

function getBaseData(reqData){
	var data = {};
	var warehouseId = $('#warehouseId').val();
	var companyId = $('#companyId').val();
	var userId = $('#userId').val();
	var userName = $('#userName').val();
	var locale = $('#locale').val();
	
	if (warehouseId == '' || warehouseId == undefined) {
		warehouseId = window.parent.document.getElementById('warehouseId').value;
	}
	if (companyId == '' || companyId == undefined) {
		companyId = window.parent.document.getElementById('companyId').value;
	}
	if (userName == '' || userName == undefined) {
		userName =window.parent.document.getElementById('userName').value;
	}
	if (userId == '' || userId == undefined) {
		userId =window.parent.document.getElementById('userId').value;
	}
	if (locale == '' || locale == undefined) {
		locale = window.parent.document.getElementById('locale').value;
	}
	if(warehouseId != '-1'){
		data['warehouseId'] = warehouseId;
	}
	if(companyId != '-1'){
		data['companyId'] = companyId;
	}
	data['userId'] = userId;
	data['userName'] = userName;
	data['locale'] = locale;
	data['data'] = reqData;
	return data;
}

function getCodelkupValue(listname,code){
	var codelkup = codelkupsMap[listname + code];
	if ( typeof(codelkup) == 'object' ){
		return codelkup.descr;
	}else{
		return code;
	}
}
function getFilterCodelkupValue(listname){
	var arr = [];
	var codelkup = codelkups[listname];
	if ( typeof(codelkup) != 'object' ){
		return arr;
	}
	var i = codelkup.length;
	for (var j=0; j<i; j++) {
		var d = codelkup[j];
		arr.push({value:d.descr,text:d.descr});
	}
	return arr;
}

function lazyLoadView(obj){
	$(obj).find('.lazy-load').each(function(){
		var component = $(this).attr('lazy-load');
		if (component == 'easyui-tabs')
			$(this).tabs();
		if (component == 'easyui-linkbutton')
			$(this).linkbutton();
		else if (component == 'easyui-menubutton')
			$(this).menubutton();
		else if(component == 'easyui-textbox')
			$(this).textbox();
		else if(component == 'easyui-datebox')
			$(this).datebox();
		else if(component == 'easyui-combobox')
			$(this).combobox();
		else if(component == 'easyui-numberbox')
			$(this).numberbox();
		else if(component == 'easyui-combogrid')
			$(this).combogrid();
		
		$(this).removeClass('lazy-load');
	});
}

function importExcel(template){
	if ($('wmsImportExcelDiv').length == 0){
		var div = '<div id="wmsImportExcelDiv" />';
		$('body').append(div);
	}
	if (template == undefined || template == null){
		template = '';
	}
	$('#wmsImportExcelDiv').dialog({
	    closed: true,
	    modal: true,
	    fix: true,
	    title: ' ',
	    width: 350,
	    height: 300,
	    content: '<iframe scrolling="no" frameborder="0"  src="../system/import?template='+template+'" style="width:100%;height:98%;" />'
	}).dialog('open');
}

function exportExcel(template, obj){
	confirmMsg($.locale.exportExcel, function (c){
		if (!c){
			return;
		}
		var data = $(obj).serializeObject();
		var baseData = getBaseData();
		$.extend(baseData, data);
		baseData['template'] = template;
		var url = getFileUrl() + '/file/export';
		loading();
		$.post({
			url: url,
			data:  baseData,
			success: function(data){
				data = returnData(data);
				closeLoading();
				if (data.code == '0') {
					showError(data.msg);
					return;
				}
				showMsg($.locale.exportTip);
			},
			error: function (data) {
				closeLoading();
				showMsg("Request failed!");
			}
		});
		
	});
}

function getReportUrl(url, p){
	var base = global['wms.url.report'];
	var d = new Date();
	var params = '?v='+ d.getTime();
	if (typeof(p) == 'object'){
		for(var i in p){
			if (typeof(p[i]) == 'string'){
				params += '&'+i+"="+p[i];
			}
		}
	}else{
		params += p;
	}
	return base + url + params;
}

function getFileUrl(){
	var base = global['wms.url.file'];
	return base;
	//return 'http://127.0.0.1:8082/wms-file/';
}

function getServiceUrl(){
	var base = global['wms.url.service'];
	return base;
	//return 'http://127.0.0.1:8180/WMS-JBT/';
}

function downloadFile(fileId){
	var param = {
		fileId: fileId
	};
	var baseData = getBaseData();
	$.extend(baseData, param);
	var d = new Date();
	var url = getFileUrl() + '/file/download?v='+d.getTime();
	for(var i in baseData){
		url += '&' + i + "=" + baseData[i];
	}
	window.open(url);
}

function exportReport(options){
	var defaultOptions = {
		fileType: "pdf"
	}
	if(options.fileType == null || options.fileType == undefined){
		options.fileType = defaultOptions.fileType
	}
	var baseData = getBaseData();
	$.extend(options,baseData);
	var url = getReportUrl("/report/" + options.fileType + '/' + options.reportType,options);
	
	window.open(url);
}

$(function(){
	$('body').css({'opacity':'1'});
	$('.icon-loading').remove();
});


