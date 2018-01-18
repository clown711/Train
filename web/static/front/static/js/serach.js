	
	
/*控制预定的代码*/
function yuding(dom){
		  layer.open({
	            type: 1,
	            title: "",
	            area: ['250px', '200px'], //宽高
	            anim: 1,
	            content:
	            		"<div style='text-align: center;margin-top:60px'>" +
	            				"<form>"+
	            					"<p style='float:left;margin-left:10px'><strong>座位类型：</strong></p>"+
	            					"<div style='margin-bottom:20px;clear:both'>"+
	            						"<label><input name='ticketSeattype' type='radio' value='软卧'>软卧</label>"+
	            						"<label><input name='ticketSeattype' type='radio' value='硬卧'>硬卧</label>"+
	            						"<label><input name='ticketSeattype' type='radio' value='软座'>软座</label>"+
	            						"<label><input name='ticketSeattype' type='radio' value='硬座'>硬座</label>"+
	            						"<label><input name='ticketSeattype' type='radio' value='无座'>无座</label>"+
	            					"</div>"+
	            					"<div style='float:right;margin-right:40px'>"+
	            						"<input type='button' id='order_btn' value='预定'>"+
	            					"</div>"+
	            				"</form>"+
	            		"</div>"
	        });
		
		var tripsId = $(dom).parent().parent().children("[name='tripsId']").attr("value");
		
		$("#order_btn").on("click",function(){
			 var ticketSeattype = $("input:radio[name='ticketSeattype']:checked").val();
			 if(ticketSeattype == null){
				 alert("请选择座位类型");
				 return false;
			 }
			 $.ajax({
			        url:"user/userOrderAdd",
			        type: "POST",
			        dataType: "json",
			        async: false, 
			        data: {
			        	"tripsId":tripsId,
			        	"ticketSeattype":ticketSeattype
			        },
			        success: function (json) {
			        	if(json.result == "1"){
			        		alert("预定成功");
			        	}
			        	else{
			        		alert(json.errorInfo);
			        	}
			        },
			        error:function(){
			        	alert("连接失败");
			        }
			});
		});
	
	}
