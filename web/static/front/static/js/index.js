/**
 * Created by Administrator on 2017/3/9.
 */
$(function () {
	
	 var unslider04 = $('#b04').unslider({
         dots: true
     }),
     data04 = unslider04.data('unslider');
	 
	 $('.unslider-arrow04').click(function() {
	     var fn = this.className.split(' ')[1];
	     data04[fn]();
	 });
	
	/*检测非空的代码*/
	function checkSearch(){
	    if($('#fromStationText').val() == ""){
	        alert("请输入出发城市！");
	        return false;
	    }else if ($('#toStationText').val() == ""){
	        alert("请输入到达城市！");
	        return false;
	    }else if ($('#train_date').val() == ""){
	        alert("请输入出发日期！");
	        return false;
	    }else {
	        return true;
	    }
	}
	
	/*搜素提交代码*/
	$("#search_trips_btn").on("click",function(){
		if(checkSearch()){
			$("#search_trips_form").submit();
		}
		else{
			return false;
		}
	});
	
	/*初始化时间选择器*/
	$("#train_date").jeDate({
	 	format: 'YYYY-MM-DD',
	    minDate: $.nowDate(0), //设定最小日期为当前日期
	    festival:true,
	    ishmsVal:false, 
	    fixed:false,  
	    isToday:false,   
	    maxDate:'2020-06-16 23:59:59', //最大日期
	    choosefun:function(elem, val) {
	    	$("#train_date").attr("value",val);
	    }
	});
	
    
});

