/**
 * Created by 博正 on 2016/11/5.
 */

/*EP-填充表格数据*/
function getData(){
    var methodEdit=$("#EP_table").attr("method-edit");
    var methodDelete=$("#EP_table").attr("method-delete");
    var methodStop=$("#EP_table").attr("method-stop");
    var methodAdvocacy=$("#EP_table").attr("method-advocacy");
    var methodAdd=$("#EP_table").attr("method-add");
    var methodOrder=$("#EP_table").attr("method-order");
    var methodPass=$("#EP_table").attr("method-Pass");
    
    var url =  $("#EP_table").attr("url");
    var page = $("#EP_table").attr("current_page");
    var fields = [];
    var temp;
    $("#EP_table th").each(function(){
        fields.push($(this).attr("field"));
    });

    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        async: false,
        data: {
        	"page":page,//当前页码
        	"limit":$("#select_limit").val()//分页（每页几条信息）
        },
        success: function (json) {
        	 	$("#EP_table_body").html("");
                for(var i=0;i<json.data.length;i++){
                    temp=temp+"<tr class='text-c'>";

                    for(var j=0;j<fields.length;j++){

                        if(fields[j] == "EP_opation"){
                            temp=temp+"<td class='td-manage'>";
                            var buttonType = [];
                            buttonType = $("[field = 'EP_opation']").attr("button-type").split("_");
                            for (var k=0;k<buttonType.length;k++){
                                    switch (buttonType[k]){
                                        case '1'://修改
                                            temp = temp+"<a title='编辑' href='javascript:;' class='ml-5' style='text-decoration:none' onclick='"+methodEdit+"(this)'> <i class='Hui-iconfont'> &#xe6df;</i> </a>";
                                            break;
                                        case '2'://删除
                                            temp = temp+"<a title='删除' href='javascript:;' class='ml-5' style='text-decoration:none' onclick='"+methodDelete+"(this)'> <i class='Hui-iconfont'>&#xe6e2;</i> </a>";
                                            break;
                                        case '3'://启用
                                            temp = temp+"<a title='启用' href='javascript:;' class='ml-5' style='text-decoration:none' onclick='"+methodAdvocacy+"(this)'> <i class='Hui-iconfont'>&#xe615;</i> </a>";
                                            break;
                                        case '4'://停用
                                            temp = temp+"<a title='停用' href='javascript:;' class='ml-5' style='text-decoration:none' onclick='"+methodStop+"(this)'> <i class='Hui-iconfont'> &#xe631;</i> </a>";
                                            break;
                                        case '5'://添加
                                        	temp = temp+"<a title='添加' href='javascript:;' class='ml-5' style='text-decoration:none' onclick='"+methodAdd+"(this)'> <i class='Hui-iconfont'> &#xe600;</i> </a>";
                                            break;
                                        case '6'://添加
                                        	temp = temp+"<a title='订单' href='javascript:;' class='ml-5' style='text-decoration:none' onclick='"+methodOrder+"(this)'> <i class='Hui-iconfont'> &#xe627;</i> </a>";
                                            break;
                                        case '7'://添加
                                        	temp = temp+"<a title='通过' href='javascript:;' class='ml-5' style='text-decoration:none' onclick='"+methodPass+"(this)'> <i class='Hui-iconfont'>&#xe6e1;</i> </a>";
                                            break;
                                        default:
                                            temp = temp+"错误";
                                            break;
                                    }
                            }
                            temp=temp+"</td>";
                        }
                        else if(fields[j] == "EP_checkbox"){
                            temp=temp+"<td><input type='checkbox' value='' name='EP_checkbox_children'></td>";
                        }
                        else{
                            var obj = json.data[i];
                            temp = temp+"<td name='"+fields[j]+"'>"+obj[fields[j]]+"</td>";
                        }


                    }

                    temp=temp+"</tr>";
                }
                /*添加表格body*/
                $("#EP_table_body").append(temp);

                /*设置分页信息*/
                $("#current_page").val(json.pageInfo.page);
                $("#totalPage").html(json.pageInfo.totalPage);
                $("#input_page").val(json.pageInfo.page);
                $("#allCount").html(json.pageInfo.totalCount);
                initPageInfo();
        },

        error: function () {
        	initPageInfo();
            alert("连接失败");
        }
    });
}
/*为显示条数绑定事件*/
$("#select_limit").on("change",function(){
	$("#EP_table").attr("current_page","1");
	getData();
	showImg();
	bindMethod()
});

/*为上一页绑定事件*/
$("#prevPage_btn").on("click",function(){
	var page = $("#EP_table").attr("current_page");
	page = parseInt(page)-1;
	$("#EP_table").attr("current_page",page);
	getData();
	showImg();
	bindMethod()
});

/*为下一页绑定事件*/
$("#nextPage_btn").on("click",function(){
	var page = $("#EP_table").attr("current_page");
	page = parseInt(page)+1;
	$("#EP_table").attr("current_page",page);
	getData();
	showImg();
	bindMethod()
});

/*为跳转指定页绑定事件*/
$("#jumpPage_btn").on("click",function(){
	var page = $("#input_page").val();
	$("#EP_table").attr("current_page",page);
	getData();
	showImg();
	bindMethod()
});


/*初始化分页数据*/
function initPageInfo(){
	$("#prevPage").removeClass("hidden");
	$("#nextPage").removeClass("hidden");
	$("#jumpPage").removeClass("hidden");
	var page = $("#EP_table").attr("current_page");
	var totalPage = $("#totalPage").html();
	if(page == "1"){
		$("#prevPage").addClass("hidden");
	}
	if(page == totalPage){
		$("#nextPage").addClass("hidden");
	}
	if(totalPage == "1"){
		$("#jumpPage").addClass("hidden");
	}
}

/*EP-展示图片	*/
function showImg(){
    var index = 0;
    $("#EP_table th").each(function(){
            if ($(this).attr("format-type") == "img"){
                $("#EP_table tbody tr").each(function(){
                    $(this).children("td").eq(index).each(function(){
                        $(this).html("<img src='"+$(this).text()+"' width='100px' height='60px' />");

                    });
                });
            }
            index++;
    });
}

/*EP-绑定事件*/
function bindMethod(){
    var index = 0;
    $("#EP_table th").each(function(){
            if ($(this).attr("format-type") == "bindMethod"){
            	
            	var methodType_methodName = [];
            	methodType_methodName = $(this).attr("methodType_methodName").split("_");
            	var methodType = methodType_methodName[0];
            	var methodName = methodType_methodName[1];
                $("#EP_table tbody tr").each(function(){
                    $(this).children("td").eq(index).each(function(){
                    	$(this).html("<u style='cursor:pointer'"+ methodType+"='"+methodName+"(this)'"+"class='text-primary' >"+$(this).text()+"</u> ");
                    });
                });
            }
            index++;
    });
}