var headerData;
function baseAction(type, checkedRows){
	confirmMsg($.locale.lineConfirm,{type:type,row:checkedRows.length}, function(r){
		if (!r){
			return;
		}
		var data = getBaseData(checkedRows);
		loading();
		$.post({
			url: getServiceUrl() + '/services/inner/inbound/'+type,
			data:data,
			success: function(data){
				data = returnData(data);
				closeLoading();
				if (data.code == '0') {
					showError(data.msg);
					return;
				}
				if ($('#inboundData').length > 0){
					$('#inboundData').datagrid('reload').datagrid('clearChecked');
					var collapse = $('#detailLayout').layout('panel','south').panel("options").collapsed;
					headerData = checkedRows[0];
					if(!collapse){
						//展开状态并且只选择了一行刷新明细
						refreshInboundDetail(headerData);
					}else {
						$('#detailLayout').layout('collapse', 'south');
					}
					if (Array.isArray(data.data)){
						headerData = data.data[0];
					}
				}else{
					var header = data.data;
					if (Array.isArray(header)){
						header = data.data[0];
					}
					refreshInboundHeader(header);
					//编辑界面时刷新列表
					refreshInboundDetail(checkedRows[0]);
				}
				if (type == 'createPutaway' ){
					showMsg(data.msg);
				}
			}
		});
	});
}

function getInboundHeaderData(){
	return headerData;
}

function getOwnerId(){
	return headerData.ownerId;
}

/* 导出文件  */
function exportInboundReport(type, data) {
	exportReport({
		__codelkup: "INBOUNDTYPE",
		fileType: "pdf",
		reportType: type,
		id: data.inboundHeaderId
	});
}
