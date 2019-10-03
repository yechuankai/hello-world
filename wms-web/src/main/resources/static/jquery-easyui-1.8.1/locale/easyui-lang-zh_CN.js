if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = '第';
	$.fn.pagination.defaults.afterPageText = '共 {pages} 页';
	$.fn.pagination.defaults.displayMsg = '显示 {from} 到 {to} , 共 {total} 记录';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = '正在处理，请稍待。。。';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = '确定';
	$.messager.defaults.cancel = '取消';
	$.messager.defaults.msg = "请稍等...";
}
$.map(['validatebox','textbox','passwordbox','filebox','searchbox',
		'combo','combobox','combogrid','combotree',
		'datebox','datetimebox','numberbox',
		'spinner','numberspinner','timespinner','datetimespinner'], function(plugin){
	if ($.fn[plugin]){
		$.fn[plugin].defaults.missingMessage = '该输入项为必输项';
	}
});
if ($.fn.validatebox){
	$.fn.validatebox.defaults.rules.email.message = '请输入有效的电子邮件地址';
	$.fn.validatebox.defaults.rules.url.message = '请输入有效的URL地址';
	$.fn.validatebox.defaults.rules.length.message = '输入内容长度必须介于{0}和{1}之间';
	$.fn.validatebox.defaults.rules.remote.message = '请修正该字段';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['日','一','二','三','四','五','六'];
	$.fn.calendar.defaults.months = ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = '今天';
	$.fn.datebox.defaults.closeText = '关闭';
	$.fn.datebox.defaults.okText = '确定';
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
if ($.fn.datetimespinner){
	$.fn.datetimespinner.defaults.selections = [[0,4],[5,7],[8,10],[11,13],[14,16],[17,19]]
}

/**
 * international
 */
if ($.messager){
	$.messager.defaults.msg = "请稍等......";
}
if($.fn.filebox){
	$.fn.filebox.defaults.buttonText = "选择文件";
}

var locale = {
	selectOneRow: "请选择一行数据",
	errorRow: '编辑行{row}校验失败',
	enterInboundNumber: "请输入入库单号",
	enterSku:'请选择货品',
	enterQuantity:'请输入接收量',
	submitRowIsNull:'提交的行为空',
	noChange:'数据没有改变',
	statusError:'状态错误',
	ownerRequired: '需要选择货主',
	selectOneSku:'请选择一件货品',
	selectTwoMoreRows:'请选择两行或更多行',
	inputTargetLocation:'请输入目标详细地址',
	saveFirst:'请先保存数据',
	selectTemplate:'请选择模板',
	selectFile:'请选择文件',
	selectOneRole:'请选择一个角色',
	selectCompany:'请选择一个公司',
	submitSuccess:'提交成功',
	exportTip:'导出中......',
	addSuccess:'添加成功',
	importTip:'导入中......',
	saveSuccess:'保存成功',
	uploadSuccess:'上传成功',
	changePasswordSuccess:'密码修改成功',
	exitConfirm:'退出当前页 ?',
	exportExcel:'根据当前条件导出Excel文件?',
	closeAll:'关闭所有页面?',
	goOn:"继续?",
	deleteOneRow:'确定删除{row}行数据 ?',
	optOneRow:'确定{opt}{row}行数据 ?',
	lineConfirm:'确定{type}{row} ?',
	putawayRows:'确定发货{row}行?',
	cancelRows:'确定取消{row}行 ?',
	unLockRows:'确定解锁 {row}行?',
	clearAll:'清空所有许可? ',
	offLine:'确定离线 {row}行 ?',
	initWarehouse:'确定初始化{row}行仓库数据?',
	warehouseAction:'确定{action}{row}行仓库数据?',
	uploadAppName:'上传app',
	inboundNewStatusPrint: '只允许待收货、收货中状态可打印',
	actions:{
		receiveAll:"全部接收",
		unReceive:"撤销收货",
		close: '关闭',
		pick:"拣货",
		ship:"发运",
		allocate:"分配",
		unallocate:"取消分配",
		release:"发货",
		unpick:"取消发货",
		cancel:"取消",
		Enable:"启用",
		Disable:"禁用"
	}	
}
if ($.locale){
	$.extend($.locale,locale);
}else{
	$.locale = locale;
}


