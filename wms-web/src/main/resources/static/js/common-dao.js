$.extend($.fn.datagrid.methods, {
    getEditingRowIndexs: function(jq) {
        var rows = $.data(jq[0], "datagrid").panel.find('.datagrid-row-editing');
        var indexs = [];
        rows.each(function(i, row) {
            var index = row.sectionRowIndex;
            if (indexs.indexOf(index) == -1) {
                indexs.push(index);
            }
        });
        return indexs;
    }
});
$.extend($.fn.combogrid.defaults, {
	striped: true,
	panelWidth: 400,
	delay: 100,
	mode: 'remote',
	method: 'post',
	fix:true,
	rownumbers: true,
	pagination: true,
	pageSize: 20,
	pageList: [20,50,100,500,1000]
});
$.extend($.fn.datagrid.defaults, {
	striped: true,
	rownumbers: true,
	selectOnCheck:true,
	checkOnSelect:false,
	method: 'post',
	pagination: true,
	pagePosition: 'top',
	pageSize: 20,
	pageList: [20,50,100,500,1000],
	multiSort: true,
	remoteSort: true
});
$.extend($.fn.datagrid.defaults.view, {
	//加载按单元格编辑
	onAfterRender: function(target) {
		var options = $(target).datagrid('options');
		//验证是否为combogrid
		if (options['idField'] != null && options['textField'] != null)
			return;
		
		$(target).datagrid('enableCellEditing');
    }
});
$(function(){
	//公共搜索-------------------------START
	$('.common-search').click(function(){
		var searchFormId = $(this).attr('data-form');
		var searchForm = $(searchFormId);
		var searchData = $(searchForm).serializeObject();
		
		var data = getBaseData();
		$.extend(data, searchData);
		
		var loadTable = searchForm.attr('data-table');
		var url = $(loadTable).attr('data-delayUrl');
		$(loadTable).datagrid({
			url: url,
			queryParams: data
		}).datagrid('clearChecked');
	});
	$('.common-search-clear').click(function(){
		var searchFormId = $(this).attr('data-form');
		var searchForm = $(searchFormId).form('reset');
	});
	if($('.common-search')){
		var searchFormId = $('.common-search-clear').attr('data-form');
		$(searchFormId).find(':text').keypress(function(e){
			if (e.keyCode == 13){
				$('.common-search').trigger('click');
			}
		});
	}
	if($('.common-search-clear')){
		var searchFormId = $('.common-search-clear').attr('data-form');
		var searchForm = $(searchFormId).form('reset');
	}
	//公共搜索-------------------------END
	
	//公共行保存-------------------------START
	$('.common-rowsave').click(function(){
		var datagrid = $(this).attr('data-table');
		var url = $(this).attr('data-url');

		var editingRows = $(datagrid).datagrid('getEditingRowIndexs');
		for(var i in editingRows) {
			var flag = $(datagrid).datagrid('validateRow', editingRows[i]);
			if (!flag){
				showError($.local.errorRows,[editingRows[i]],function(){});
				return;
			}
		}
		
		var rows = $(datagrid).datagrid('getChanges');

		if (editingRows.length == 0 && rows.length == 0) {
			showError($.locale.noChange);
			return;
		}
		if (editingRows.length > 0){
			$(datagrid).datagrid('endCellEdit');
			rows = $(datagrid).datagrid('getChanges');
		}
		
		var data = getBaseData(rows);
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
	
	//公共行保存-------------------------END
	
	
	//公共行删除-------------------------START
	$('.common-rowdel').click(function(){

		var datagrid = $(this).attr('data-table');
		var url = $(this).attr('data-url');
		
		var checkedRows = $(datagrid).datagrid('getChecked');
		if (checkedRows.length == 0) {
			showError($.locale.selectOneRow);
			return;
		}
		confirmMsg($.locale.deleteOneRow,{row:checkedRows.length}, function(r){
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
	//公共行删除-------------------------END
	
	//公共显示新增框-------------------------START
	$('.common-add').click(function(){
		var panel = $(this).attr('data-addpanel');
		lazyLoadView(panel);
		$(panel).dialog({
		    closed: true,
		    modal: true,
		    fix: true
		}).dialog('open');
		$(panel).find('form').form('reset');
	});
	//公共显示新增框-------------------------END
	
	//公共显示新增框-------------------------START
	$('.common-confirmadd').click(function(){
		var from = $(this).attr('data-form');
		var datagrid = $(this).attr('data-table');
		var url = $(from).attr('data-url');
		var valid = $(from).form('validate');
		if (!valid) {
			return;
		}
		var data = $(from).serializeObject();
		data = getBaseData(data);
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
				confirmMsg(data.msg + '<br /> Go on ?', function(r){
					$(from).form('reset');
					if (!r) {
						$(from).parents('.window-body').dialog('close');
						$(datagrid).datagrid('reload').datagrid('clearChecked');
					}
				});
			}
		});
	});
	//公共显示新增框-------------------------END
});


