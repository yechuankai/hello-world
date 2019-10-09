var testtimer;
function test(async){
	if (async == undefined){
		async = true;
	}
	var checkData = {};
	$.post({
		url: '../services/web/test',
		async: async,
		success: function(data){
			if (typeof(data) == 'string'){
				data = JSON.parse(data);
			}
			//data = returnData(data);
			if (data.code == '5' || data.code == '0') {
				clearTimeout(testtimer);
				if (async && data.code == '5'){
					showError(data.msg, function(){
						window.location.href = getServiceUrl();
					});
				}
				checkData = data;
				return;
			}else{
				clearTimeout(testtimer);
				testtimer = setTimeout(test, 1000 * 20);
			}
		}
	});
	return checkData;
}
testtimer = setTimeout(test, 1000 * 20);
