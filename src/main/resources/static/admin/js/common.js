;!function(){
	
	var layer = layui.layer, $ = layui.jquery, form = layui.form, element = layui.element;
	
	/**
     * 获取url简化
	 */
	var getUrl = function(url)
	{
		return location.protocol+'//'+window.location.host + '/' + url;
	}
	
	/**
     * 展开与收拢
	 */
	optMore = function(obj)
	{
		$(obj).parents('tr').next('tr').toggle();
	}
	
	/**
     * 气泡提示
	 */
	tips = function(id,text)
	{
		if(id){	
			layer.tips(text,"#"+id, {
				tips  : [1, '#009688'],
				time  : 2000,
				area  : ['230px', 'auto']
			});
		}
	}
	
	/**
     * 全选和全不选
	 */
	form.on('checkbox(checkAll)', function(data)
	{
		var flag = data.elem.checked;
		$('.layui-form input:checkbox').prop("checked", flag)
		form.render('checkbox');
	});
	
	/**
     * 获取选中的checked的ID
	 */
	getcheckeds = function()
	{
		var ids  = [];
		$('.layui-table input[type=checkbox]').each(function()
		{
			var id = $(this).val();
			
			if($(this).prop('checked') && id>0)
			{
				ids.push(id);
			}
		})
		return ids.join(',');
	}
	
	/**
     * 批量删除方法
	 * @param url  删除的地址url
	 * @param id   单独删除的ID,当ID为空时为批量删除
	 * @param text 删除时的文字提示
	 * @param jump 跳转地址
	 */
	deleteData = function(url, id, text, jump)
	{
		var pids = id ? id : getcheckeds();
		var jump = jump ? jump : '';
		var text = text ? text:'您确定要删除吗？';
		
		if(pids && url)
		{	
			layer.confirm(text,
			function(){
				$.post(url,{id:pids},function(json){
					if(json.status==1)
					{
						layer.closeAll();
						jump?reload(jump):reload();
					}else{
						layer.msg(json.msg);
					}
				},'json');
			})
		}else{
			layer.msg('请选择要删除的数据！');
		}
	}

	/**
     * 更新状态
	 * @param url  		  更新的地址url
	 * @param id(int) 	  当ID为空时为更新数据;
	 * @param status(int) 需要更新的状态;
	 */
	updateData = function(url, id, status)
	{	
		var ids = id ? id : getcheckeds();
		
		if(ids && url)
		{	
			$.post(url,{'id':ids,'status':status},
			function(json)
			{
				if(json.status==1)
				{
					layer.msg(json.msg,function()
					{
						reload();
					});
				}else{
					layer.msg(json.msg);
				}
			},'json');
		}else{
			layer.msg('至少选择一条数据!',2,0);
		}
	}
	
	/**
	 * layer弹框简化
	 */
	showBox = function(title,width,height,close,url,success)
	{
		layer.open({
			type: 2,
			title:title,
			shadeClose: true,
			closeBtn:close,
			shade: 0.8,
			area: [width+'px', height+'px'], //宽高
			content:url,
			success: success
		});
	}
			
   /**
	* 省级联动
	* @param url(string)  请求路径 必填
	* @param province(int)省级  回显时填写
	* @param city(int) 	  市级  回显时填写
	* @param county(int)  区级  回显时填写
	*/
	getArea = function(url,province,city,county)
	{
		var province = arguments[1]?arguments[1]:'',city = arguments[2]?arguments[2]:'',county = arguments[3]?arguments[3]:'';
		
		$.post(url,{pid:1},function(json)
		{
			if(json)
			{	
				var option = [];
				
				$(json).each(function(index,rows){

					if(province==rows['id'])
					{	
						option +='<option value="'+rows['id']+'" selected>'+rows['name']+'</option>';
						
						if(city)
						{
							getAreaList('select[name=city]',url,province,city,'请选择城市');
						}
						
						if(county)
						{	
							getAreaList('select[name=county]',url,city,county,'请选择县/区');
						}
					}else{
						option +='<option value="'+rows['id']+'">'+rows['name']+'</option>';
					}
				})
				
				$('select[name=province]').html('<option value="">请选择省份</option>'+option);
				form.render('select');
			}
		},'json');


		form.on('select(province)', function(data)
		{
			var pid = data.value;
			getAreaList('select[name=city]',url,pid,'','请选择城市');
            $('select[name=county]').html('<option value="">请选择县/区</option>');
		})

		form.on('select(city)', function(data)
		{
			var pid = data.value;
			getAreaList('select[name=county]',url,pid,'','请选择县/区');
		})

		getAreaList = function(dom,url,pid,choose,text)
		{
			$.post(url,{pid:pid},
			function(json){
				if(json)
				{	
					var option = [];
					
					$(json).each(function(index,rows)
					{	
						if(choose==rows['id'])
						{
							option +='<option value="'+rows['id']+'" selected>'+rows['name']+'</option>';
						}else{
							option +='<option value="'+rows['id']+'">'+rows['name']+'</option>';
						}
					})
					
					$(dom).html('<option value="">'+text+'</option>'+option);
					form.render('select');
				}
			},'json');
		}
	}
	
	/**
	 * 格式化金钱
	 * @param url(string)  请求路径 必填
	 * @param province(int)省级  回显时填写
	 * @param city(int) 	  市级  回显时填写
	 * @param county(int)  区级  回显时填写
	 */
	formatPrice = function(str)
	{
		var newStr = "", count = 0;
		str = str.toString();
		
		if(str.indexOf(".") == -1){
			for(var i=str.length-1; i >= 0; i--){
				if(count % 3 == 0 && count != 0){
					newStr = str.charAt(i) + "," + newStr;
				}else{
					newStr = str.charAt(i) + newStr;
				}
				count++;
			}
			
			str = newStr + ".00"; //自动补小数点后两位
			return str;
		}else{
			for(var i = str.indexOf(".")-1; i >= 0; i--){
				if(count % 3 == 0 && count != 0){
					newStr = str.charAt(i) + "," + newStr;
				}else{
					newStr = str.charAt(i) + newStr; //逐个字符相接起来
				}
				count++;
			}
			
			str = newStr + (str + "00").substr((str + "00").indexOf("."),3);
			return str;
		 }
	}
	
	/**
	 * 字数输入限制
	 * @param field (string) 表单名字
	 * @param countfield 表单域元素名
	 * @param maxlimit   限制字符
	 */
	textCounter = function(field, countfield, maxlimit)
	{ 
	   if ($("#"+field).val().length > maxlimit)
	   {
		   $("#"+field).val($("#"+field).val().substring(0, maxlimit));
	   }
	   else
	   {
		   $("#"+countfield).text(maxlimit - $("#"+field).val().length);
	   }
    }
	
	/**
	 * layer load简单封装
	 * @param text load文字提示
	 */
	load = function(text)
	{
		layer.load(2, {content:text,success: 
			function(msg)
			{
				msg.find('.layui-layer-content').css({'padding-top':'5px','padding-left':'40px','width':'150px'});
			}
		}) 
	}
	
	/**
	 * 清理缓存
	 * @param submitId  按钮lay-filter
	 * @param url 		提交地址
	 * @param formId    表单ID
	 * @param callback  回调方法
	 */
	clearup = function()
	{
		load('正在清理缓存...');
		$.post(getUrl('admin/index/clear'),function()
		{
			layer.closeAll();
			layer.msg('清理缓存完毕！');
		},'json');
	}
	
	/**
	 * Ajax异步提交表单
	 * @param submitId  按钮lay-filter
	 * @param url 		提交地址
	 * @param formId    表单ID
	 * @param callback  回调方法
	 */
	ajaxform = function (submitId, url, formId, callback)
	{
		form.on('submit('+submitId+')', function(data)
		{
			$.post(url,$('#'+formId+'').serialize(),
			function(data)
			{	
				return callback(data);
			},'json');
			
			return false;
		});
	}
	
	/**
     * Pjax加载页面
	 */
	$(".pjax").click(function()
	{
		$(this).parents('ul').find('li').removeClass('layui-nav-itemed');
		$(this).parents('li').addClass('layui-nav-itemed');
		
		$(document).ready(
			function(){
				layer.closeAll()
			}
		).on('pjax:send',
			function()
			{
				load('正在努力加载页面....');
			}
		).on('pjax:complete',
			function(){
				form.render();element.init();layer.closeAll();
			}
		)
		$(document).pjax('.pjax','#page-wrapper',{
			timeout:9000
		});
	})
	
	/**
     * Pjax 表单提交表单
	 */
	$("#page-wrapper").on('submit', '.pjax-from',function (event)
	{
		var method = $(this).attr('method') ? $(this).attr('method') : 'GET';
		event.preventDefault();
		
		$(document).ready
		(
			function(){layer.closeAll()}
		).on('pjax:send',
			function(){load('正在努力加载页面....')}
		).on('pjax:complete',
			function(){form.render();element.init();layer.closeAll();}
		)
		
		$.pjax.submit(event, '#page-wrapper', {type:method, timeout:9000});
	});
	
	/**
     * Pjax刷新页面
	 */
	reload = function(url)
	{
		window.location.reload();
		/*
		$(document).ready
		(
			function(){layer.closeAll()}
		).on('pjax:send',
			function(){load('正在努力加载页面....')}
		).on('pjax:complete',
			function(){form.render();element.init();layer.closeAll();}
		)
		
		if(url)
		{
			$.pjax({url:url,container: '#page-wrapper',timeout:5000});
		}else{
			$.pjax.reload('#page-wrapper');
		}*/
	}
	
	/**
     * 退出登录系统
	 */
	$('#logout-btn').click(function()
	{	
		layer.confirm('您确定要退出本系统吗？',
		function()
		{
			$.post('/admin/logout',function(json)
			{
				if(json.status)
				{
					window.location.href = json.url;
				}else{
					layer.msg(json.msg);
				}
			},'json');
		});
	});
	 form.render();
	 element.init();
}();