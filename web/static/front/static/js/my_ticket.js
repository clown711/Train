/**
 * Created by Administrator on 2017/3/8.
 */

	/*显示未完成订单*/
    $('.unfinish').click(function(){
        $('.finish').removeClass('active');
        $('.unfinish').addClass('active');
        $('.done').addClass('hidden');
        $('.undone').removeClass('hidden');
        $('.lay-hd span').html("未完成订单");
    });
    
	/*显示已完成订单*/    
    $('.finish').click(function(){
        $('.finish').addClass('active');
        $('.unfinish').removeClass('active');
        $('.done').removeClass('hidden');
        $('.undone').addClass('hidden');
        $('.lay-hd span').html("已完成订单");
    });
    /*控制改签*/
    function gaiqian(dom, basePath) {
    	var orderId = $(dom).parent().children("[name='orderId']").val();
    	var ticketSeattype = $(dom).parent().children("[name='ticketSeattype']").val();
    	layer.open({
    		  type: 2,
    		  title: '改签',
    		  shadeClose: true,
    		  shade: 0.8,
    		  area: ['380px', '50%'],
    		  content: basePath+'user/toUserOrderChange?orderId='+orderId+'&ticketSeattype='+ticketSeattype //iframe的url
    		});
	}
    
    /*控制取票*/
    function qupiao(dom, basePath){
    	var orderId = $(dom).parent().children("[name='orderId']").val();
    	layer.confirm('确认取票吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
        	//确认执行
        	 $.ajax({
       			url: basePath+"user/userOrderConfirm",
       	        type: "POST",
       	        dataType: "json",
       	        async: false,
       	        data:{
       	        	orderId:orderId
       	        },
       	        success: function (json) {
       	        	if(json.result == 1){
       	        		alert("取票成功");
       	        		layer.closeAll();
       	        	}
       	        	else{
       	        		alert(json.errorInfo);
       	        		layer.closeAll();
       	        	}
       	        },
       	        error:function(){
       	        	alert("连接失败");
       	        }
       		}); 
        }, function(){  //取消执行

        });
    }
    
    /*控制退票*/
    function tuipiao(dom, basePath){
    	var orderId = $(dom).parent().children("[name='orderId']").val();
    	 //询问框
        layer.confirm('确认退票吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
        	//确认执行
        	 $.ajax({
       			url: basePath+"user/userOrderDelete",
       	        type: "POST",
       	        dataType: "json",
       	        async: false,
       	        data:{
       	        	orderId:orderId
       	        },
       	        success: function (json) {
       	        	if(json.result == 1){
       	        		alert("退票成功");
       	        		layer.closeAll();
       	        	}
       	        	else{
       	        		alert(json.errorInfo);
       	        		layer.closeAll();
       	        	}
       	        },
       	        error:function(){
       	        	alert("连接失败");
       	        }
       		}); 
        }, function(){  //取消执行

        });
    }
    
   
