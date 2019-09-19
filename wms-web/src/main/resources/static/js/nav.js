(function($){
	function getParentMenu(rootMenu, menu){
		return findParent(rootMenu);

		function findParent(pmenu){
			var p = undefined;
			$(pmenu).find('.menu-item').each(function(){
				if (!p && this.submenu){
					if ($(this.submenu)[0] == $(menu)[0]){
						p = pmenu;
					} else {
						p = findParent(this.submenu);
					}
				}
			});
			return p;
		}
	}
	function getParentItem(pmenu, menu){
		var item = undefined;
		$(pmenu).find('.menu-item').each(function(){
			if ($(this.submenu)[0] == $(menu)[0]){
				item = $(this);
				return false;
			}
		});
		return item;
	}

	$.extend($.fn.menubutton.methods, {
		enableNav: function(enabled){
			var curr;
			$(document).unbind('.menubutton');
			if (enabled == undefined){enabled = true;}
			if (enabled){
				$(document).bind('keydown.menubutton', function(e){
					var currButton = $(this).find('.m-btn-active,.m-btn-plain-active,.l-btn:focus');
					if (!currButton.length){
						return;
					}

					if (!curr || curr.button != currButton[0]){
						curr = {
							menu: currButton.data('menubutton') ? $(currButton.menubutton('options').menu) : $(),
							button: currButton[0]
						};
					}
					var item = curr.menu.find('.menu-active');

					switch(e.keyCode){
						case 13:  // enter
							item.trigger('click');
							break;
						case 27:  // esc
							currButton.trigger('mouseleave');
							break;
						case 38:  // up
							var prev = !item.length ? curr.menu.find('.menu-item:last') : item.prevAll('.menu-item:first');
							prev.trigger('mouseenter');
							return false;
						case 40:  // down
							var next = !item.length ? curr.menu.find('.menu-item:first') : item.nextAll('.menu-item:first');
							next.trigger('mouseenter');
							return false;
						case 37:  // left
							var pmenu = getParentMenu(currButton.data('menubutton') ? $(currButton.menubutton('options').menu) : $(), curr.menu);
							if (pmenu){
								item.trigger('mouseleave');
								var pitem = getParentItem(pmenu, curr.menu);
								if (pitem){
									pitem.trigger('mouseenter');
								}
								curr.menu = pmenu;
							} else {
								var prev = currButton.prevAll('.l-btn:first');
								if (prev.length){
									currButton.trigger('mouseleave');
									prev.focus();
								}
							}
							return false;
						case 39:  // right
							if (item.length && item[0].submenu){
								curr.menu = $(item[0].submenu);
								curr.button = currButton[0];
								curr.menu.find('.menu-item:first').trigger('mouseenter');
							} else {
								var next = currButton.nextAll('.l-btn:first');
								if (next.length){
									currButton.trigger('mouseleave');
									next.focus();
								}
							}
							return false;
					}
				});						
			}
		}
	});
})(jQuery);

$(function(){
	$('div.menu-item').click(function(){
		var url = ($(this).attr('data-url'));
		if (url != undefined && url != "#") {
			var name = $(this).find('div.menu-text').text();
			//验证系统是否正常
			var checkData = test(false);
			if (typeof(checkData) == 'object'
					&& ( checkData['code'] == '5' || checkData['code'] == '0')){
				showError(checkData.msg, function(){
					if (checkData.code == '5'){
						window.location.href=getServiceUrl();
					}
				});
				return;
			}
			addPanel(name, url);
		}
	});
});

function closeAll(){
	confirmMsg($.locale.closeAll, function(r) {
		if (!r){return;}
		var tab = $('#main').tabs('tabs');
		var count = tab.length;
		for(var i = 0;i < count; i ++){
			$('#main').tabs('close', 0);
		}
	});
}

function addPanel(name, url){
	//查询目前面板中是否已存在相同选项卡
	var count = 0;
	$('.tabs-title').each(function(){
		var title = $(this).text();
		if (title.indexOf(name) > -1){
			count ++;
		}
	});
	if (count > 0){
		name = name + '('+(count+1)+')';
	}
	$('#main').tabs('add',{
		title: name + "&nbsp;",
		content: '<iframe scrolling="no" frameborder="0"  src="'+url+'" style="width:100%;height:98%;" />',
		closable: true,
		plain: false,
		border:false,
		style: {'overflow':'hidden'}
	});
}
