nologin = false;
function test(async){
	if (async == undefined){
		async = true;
	}
	var checkData = {};
	if (!nologin){
		$.post({
			url: '../services/web/test',
			async: async,
			success: function(data){
				data = returnData(data);
				if (data.code == '5' || data.code == '0') {
					nologin = true;
					/*showError(data.msg, function(){
						window.location.href='/web/login';
					});*/
					checkData = data;
					return;
				}
				setTimeout(test, 1000 * 60);
			}
		});
	}
	return checkData;
}
setTimeout(test, 1000 * 60);
