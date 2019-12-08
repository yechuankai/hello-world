var headerData;
function baseAction(type, checkedRows) {
    confirmMsg($.locale.lineConfirm,{type:type,row:checkedRows.length}, function(r){
        if (!r){
            return;
        }
        var data = getBaseData(checkedRows);
        loading();
        $.post({
            url: '../../services/inner/outbound/'+type,
            data:data,
            success: function(data){
                data = returnData(data);
                closeLoading();
                if (data.code == '0') {
                    showError(data.msg);
                    return;
                }
                debugger;
                var singleData = false;
                if ($('#outboundData').length > 0){
	                $('#outboundData').datagrid('reload').datagrid('clearChecked');
	                var collapse = $('#detailLayout').layout('panel','south').panel("options").collapsed;
	                headerData = checkedRows[0];
	                if(!collapse){
	                    //展开状态并且只选择了一行刷新明细
	                    refreshOutboundDetail(headerData);
	                }else {
	                    $('#detailLayout').layout('collapse', 'south');
	                }
	                if (checkedRows.length == 1){
	                	singleData = true;
	                }
	                headerData = data.data[0];
                }else{
                	singleData = true;
                	var dataList = data.data;
					if (Array.isArray(dataList)){
						headerData = dataList[0];
					}
					refreshOutboundHeader(headerData);
					//编辑界面时刷新列表
					refreshOutboundDetail(checkedRows[0]);
                }
                if (singleData && type == 'allocate' && headerData.status != '30'){
                	showWarn(data.msg);
                }
                if (singleData && type == 'pick' && headerData.status != '60'){
                	showWarn(data.msg);
                }
                if (singleData && type == 'ship' && headerData.status != '80'){
                	showWarn(data.msg);
                }
            }
        });
    });
}

function getOwnerId(){
    return headerData.ownerId;
}

function getOutboundHeaderData(){
    return headerData;
}


/* 导出文件  */
function exportOutboundReport(type, data) {
	exportReport({
		__codelkup: "OUTBOUNDTYPE",
		fileType: "pdf",
		reportType: type,
		ids: data
	});
}
