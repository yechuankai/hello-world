var inboundData = {};
var inboundNextLot = {};
$(function(){
	bindScan({
		inboundNumber: sacnInboundNumber,
		skuCode: scanSku,
		containerNumber:scanContainer,
		lpnNumber: scanLpn,
		uomQuantityReceive: scanQuantity,
	});
	$('#inboundNumber').textbox('textbox').focus();
});

//扫描入库单号
function sacnInboundNumber(){
	var flag = validate('inboundNumber');
	if (!flag){
		return;
	}
	var data = {
		inboundNumber: getValue('inboundNumber')
	};
	reqData = getBaseData(data);
	loading();
	$.post({
		url: getServiceUrl() + '/services/public/inbound/getInbound',
		data: reqData,
		success: function(rep){
			var repData = returnData(rep);
			closeLoading();
			if (repData.code == '0') {
				showError(repData.msg, function(){
					setValue('inboundNumber', '');
					$('#inboundNumber').textbox('textbox').focus();
				});
				return;
			}
			var inbound = repData.data;
			$.extend(inboundData, inbound);
			setValue('type', inbound.type);
			$('#skuCode').textbox('textbox').focus();
			$('#receiveList').datagrid('loadData', []);
		}
	});
};

function scanSku(){
	var flag = validate('skuCode');
	if (!flag){
		return;
	}
	var data = {
			inboundHeaderId: inboundData.inboundHeaderId,
			skuCode: getValue('skuCode')
	};
	var baseData = getBaseData(data);
	loading();
	$.post({
		url: getServiceUrl() + '/services/public/inbound/getInboundDetail',
		data: baseData,
		success: function(data){
			data = returnData(data);
			closeLoading();
			if (data.code == '0') {
				showError(data.msg, function(){
					setValue('skuCode', '');
					$('#skuCode').textbox('textbox').focus();
				});
				return;
			}
			var inboundDetail = data.data;
			$.extend(inboundData, inboundDetail);
			//pack
			var pack = inboundDetail.pack;
			setValue('packCode', pack.packCode);
			
			var comData = [];
			
			comData.push({label: pack.uom, value: pack.qty});
			comData.push({label: pack.uomInner, value: pack.qtyInner});
			comData.push({label: pack.uomCase, value: pack.qtyCase});
			
			$("#uom").combobox({
				data : comData,
				value : inboundDetail.uomQuantity,
				idField: 'value',
				textField: 'label'
			});
			
			//填充批属性
			setValue('locationCode',inboundDetail.locationCode);
			
			$('#lotAttribute1').val(inboundDetail.lotAttribute1);
			$('#lotAttribute2').val(inboundDetail.lotAttribute2);
			$('#lotAttribute3').val(inboundDetail.lotAttribute3);
			$('#lotAttribute4').val(inboundDetail.lotAttribute4);
			$('#lotAttribute5').val(inboundDetail.lotAttribute5);
			$('#lotAttribute6').val(inboundDetail.lotAttribute6);
			$('#lotAttribute7').val(inboundDetail.lotAttribute7);
			$('#lotAttribute8').val(inboundDetail.lotAttribute8);
			$('#lotAttribute9').val(inboundDetail.lotAttribute9);
			$('#lotAttribute10').val(inboundDetail.lotAttribute11);
			$('#lotAttribute11').val(inboundDetail.lotAttribute11);
			$('#lotAttribute12').val(inboundDetail.lotAttribute12);
			lotAttribute(false);
			$('#containerNumber').textbox('textbox').focus();
			$('#receiveList').datagrid('loadData', []);
		}
	});
	
};

function scanContainer(){
	$('#lpnNumber').textbox('textbox').focus();
};

function scanLpn(){
	$('#uomQuantityReceive').textbox('textbox').focus();
};

function scanQuantity(){
	var flag = validate('uomQuantityReceive');
	if (!flag){
		return;
	}
	
	if (inboundData['inboundNumber'] == undefined){
		showError($.locale.enterInboundNumber, function(){
			$('#inboundNumber').textbox('textbox').focus();
		});
		return;
	}
	
	if (inboundData['skuCode'] == undefined){
		showError($.locale.enterSku, function(){
			$('#skuCode').textbox('textbox').focus();
		});
		return;
	}
	
	var uomQuantity = getValue('uom');
	console.log('uomQuantity='+uomQuantity);
	var receiveQuantity = getValue('uomQuantityReceive') * uomQuantity;
	if (receiveQuantity == '' || receiveQuantity == 0){
		showError($.locale.enterQuantity, function(){
			$('#uomQuantityReceive').textbox('textbox').focus();
		});
		return;
	}
	//验证批属性
	if (!lotConfirm()){
		return;
	}
	
	console.log('receiveQuantity='+receiveQuantity);
	var expectedQuantity = inboundData.quantityReceive - inboundData.quantityReceived;
	if (receiveQuantity > expectedQuantity){
		//validate 按实物进行收货 不进行校验
		console.log('receiveQuantity('+receiveQuantity+') > expectedQuantity('+expectedQuantity+')');
	}
	
	var sku = inboundData.sku;
	//计算体积
	var volume = receiveQuantity * sku.volume;
	console.log('sku.volume='+volume);
	//计算重量
	var weight = receiveQuantity * sku.weightGross;
	console.log('sku.weight='+weight);
	
	//增加到明细列表中
	var addRow = {
		lpnNumber: getValue('lpnNumber'),
		volume: volume,
		weight: weight,
		uom: $('#uom').combobox('getText'),
		uomQuantityReceive: getValue('uomQuantityReceive'),
	};
	$('#receiveList').datagrid('appendRow', addRow);
	
	//计算体积
	var volume = parseFloat(getValue('volume')) + volume;
	setValue('volume', volume);
	//计算重量
	var weight = parseFloat(getValue('weight')) + weight;
	setValue('weight', weight);
	//计算剩余量
	inboundData.quantityReceive = inboundData.quantityReceive + receiveQuantity;
	
	setValue('uomQuantityReceive', ''),
	setValue('lpnNumber', '');
	$('#lpnNumber').textbox('textbox').focus();
}


function clean(){
	setValue('inboundNumber', '');
	setValue('type', '');
	setValue('skuCode', '');
	setValue('packCode', '');
	setValue('uom', '');
	setValue('containerNumber', '');
	setValue('locationCode', '');
	setValue('lpnNumber', '');
	setValue('uomQuantityReceive', '');
	setValue('volume', '0');
	setValue('weight', '0');
	$('#receiveList').datagrid('loadData', []);
}

function lotAttribute(show){
	//计算显示与必填
	var lotLabel = inboundData.lotLabel;
	var lotv = inboundData.lotv;
	
	var length = 12;
	var pre = '';
	for (var i=1; i <= length; i++){
		var el = '#lotAttribute' + i;
		var lazy = $(el).attr('lazy-load');
		if (lazy == undefined || lazy == ''){
			continue;
		}
		
		var options = {
			label: lotLabel['lotAttribute' + i + 'Label'],
			required: lotv['lotAttribute' + i + 'Required'] == 'Y',
			inputEvents: $.extend({}, $.fn.textbox.defaults.inputEvents,{
	            keyup: function(event){
	                if(event.keyCode == 13){
	                	var id = $(this).parent().parent().find(".textbox-f").attr('id');
	                	var flag = validate(id);
	                	if (!flag){
	                		return false;
	                	}
	                	if (inboundNextLot[id] != undefined){
		                	//下一个焦点
		                	$('#'+inboundNextLot[id]).textbox('textbox').focus();
	                	}else{ //最后一个批属性执行回车操作
	                		lotConfirm();
	                	}
	                }
	            }
	        })
		};
		if (lotv['lotAttribute' + i + 'View'] != 'Y'){
			$('#row_lotAttribute'+i).hide();
		}else{
			//处理回车焦点事件
			var cur = 'lotAttribute' + i;
			if (pre != ''){
				inboundNextLot[pre] = cur
			}
			pre = cur;
		}
		
		if (lazy == 'easyui-textbox'){
			$(el).textbox(options);
		}else if(lazy == 'easyui-datebox'){
			$(el).datebox(options);
		}
		$(el).attr('lazy-load','');
	}
	if (show){
		$('#lot').dialog('open').dialog('center')
		for (var i=1; i <= length; i++){
			var flag = validate('lotAttribute' + i);
			if (!flag){
				$('#lotAttribute'+i).textbox('textbox').focus();
				return;
			}
		}
	}
}

function lotConfirm(){
	var length = 12;
	for (var i=1; i <= length; i++){
		var flag = validate('lotAttribute' + i);
		if (!flag){
			$('#lot').dialog('open').dialog('center');
			$('#lotAttribute'+i).textbox('textbox').focus();
			return false;
		}
	}
	$('#lot').dialog('close');
	return true;
}

function submit(){
	var receiveData = $('#receiveList').datagrid('getData').rows;
	if (receiveData.length == 0){
		showError($.locale.submitRowIsNull);
		return;
	}
	
	if (!lotConfirm()){
		return;
	}
	
	var lotvalue = {};
	var length = 12;
	for (var i=1; i <= length; i++){
		var value = getValue('lotAttribute' + i);
		lotvalue['lotAttribute' + i] = value;
	}
	
	var containerNumber = getValue('containerNumber');
	var locationCode = getValue('locationCode');
	var rows = [];
	for (var i in receiveData){
		var row = {};
		$.extend(row, inboundData);
		$.extend(row, receiveData[i]);
		$.extend(row, lotvalue)
		row['containerNumber'] = containerNumber;
		row['locationCode'] = locationCode;
		rows.push(row);
	}
	var baseData = getBaseData(rows);
	loading();
	$.post({
		url: getServiceUrl() + '/services/public/inbound/receive',
		data: baseData,
		success: function(data){
			data = returnData(data);
			closeLoading();
			if (data.code == '0') {
				showError(data.msg);
				return;
			}
			showMsg($.locale.submitSuccess, function(){
				setValue('containerNumber', '');
				setValue('lpnNumber', '');
				setValue('uomQuantityReceive', '');
				setValue('volume', '0');
				setValue('weight', '0');
				$('#receiveList').datagrid('loadData', []);
				$('#containerNumber').textbox('textbox').focus();
			});
		}
	});
}

