//设置同步响应
$.ajaxSetup({
	async: false
});

function validate(el){
	return $('#' + el).textbox('isValid');
}

function getValue(el){
	return $('#' + el).textbox('getValue');
}
function setValue(el, val){
	var cls = $('#' + el).attr('class');
	if (cls != undefined && cls.indexOf('easyui-combobox') > -1){
		$('#' + el).combobox('setValue', val);
	}else{
		$('#' + el).textbox('setValue', val);
	}
}

function bindScan(obj){
	if (typeof(obj) == 'object'){
		for(var i in obj){
			$("#" + i).textbox({
		        inputEvents: $.extend({}, $.fn.textbox.defaults.inputEvents,{
		            keyup:function(event){
		                if(event.keyCode == 13){
		                	var id = $(this).parent().parent().find(".textbox-f").attr('id');
		                	var fn = obj[id];
		                	fn(this);
		                }
		            }
		        })
			});
		}
	}
};

$(function(){
	$('.wms-m-back').click(function(){
		var go = $(this).attr('go');
		confirmMsg($.locale.exitConfirm, function(r){
			if (!r){
				return;
			}
			window.location.href = '../main?go='+go;
		});
	});
});