$(function(){
	$('.common-putaway-ok').click(function(){
		var datagrid = $(this).attr('data-table');
		var url = $(this).attr('data-url');
	
		var checkedRows = $(datagrid).datagrid('getChecked');
		if (checkedRows.length == 0) {
			showError($.locale.selectOneRow);
			return;
		}
		confirmMsg($.locale.putawayRows,{row:checkedRows.length}, function(r){
			if (!r){
				return;
			}
			var data = getBaseData(checkedRows);
			loading();
			$.post({
				url: url,
				data:data,
				success: function(data){
					data = returnData(data);
					closeLoading();
					if (data.code == '0') {
						showError(data.msg);
						return;
					}
					$(datagrid).datagrid('reload').datagrid('clearChecked');
				}
			});
		});
	});
});