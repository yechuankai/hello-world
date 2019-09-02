function submitForm(){
	var valid = $('#loginFrom').form('validate');
	if (!valid) {
		return;
	}
	loading();
	$('#loginFrom').form('submit', {
	    url:'../login',
		success:function(data){
			data = returnData(data);
			closeLoading();
			if (data.code == '0') {
				showError(data.msg);
				return;
			}
			window.location.href="main";
		}
	});
}
function clearForm(){
	$('#loginFrom').form('clear');
	$("#_easyui_textbox_input1").focus();
}

$(function(){
	$("#submitForm").click(submitForm);
	$("#clearForm").click(clearForm);
	
	
	$("#_easyui_textbox_input1").keypress(function(e){
		if (e.keyCode == 13) {
			$("#_easyui_textbox_input2").focus();
		}
	}).focus();
	
	$("#_easyui_textbox_input2").keypress(function(e){
		if (e.keyCode == 13) {
			submitForm();
		}
	});
	
});