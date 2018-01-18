$(function () {
	function checklogin(){
	    if($('#user').val() == "" || $('#user').val().length > 10){
	        $('.error').html("请输入0-10字符的用户名！");
	        return false;
	    }else if($('#password').val() == "" || $('#password').val().length > 20){
	        $('.error').html("请输入0-20字符的密码！");
	        return false;
	    }else {
	        return true;
	    }
	}
	
	function registercheck(){
	    if($('#r_user').val() == "" || $('#r_user').val().length > 10){
	        $('.error').html("请输入0-10字符的用户名！");
	        return false;
	    }else if($('#r_password').val() == "" || $('#r_password').val().length > 20){
	        $('.error').html("请输入0-20字符的密码！");
	        return false;
	    }else if($('#nickname').val() == "" || $('#nickname').val().length > 20){
	        $('.error').html("请输入0-10字符的昵称!");
	        return false;
	    }else {
	        return true;
	    }
	}
	

/*控制提登录通过AJAX提交数据的代码*/
 $('#userLoginAlert_btn').click(function(){
     layer.open({
         type: 1,
         title: "",
         anim: 1,
         area: ['500px', '400px'], //宽高
         content: '' +
             '<div class="wrapper">'+
                 '<div id="content">' +
                     '<h1>欢迎登录</h1>' +
                     '<div class="article">'+
                         '<form id="userLogin_form">' +
                             '<div class="item">' +
                                 '<label>帐号</label>' +
                                 '<input id="user" name="userAccount" type="text" class="basic-input" placeholder="邮箱/手机号/用户名"/>' +
                             '</div>'+
                             '<div class="item">' +
                                 '<label>密码</label>' +
                                 '<input id="password" name="userPassword" type="password" class="basic-input"/>' +
                             '</div>'+
                             '<div class="item btn">' +
                                 '<label>&nbsp;</label>' +
                                 '<input id="userLogin_btn" type="button" value="登录" class="btn-submit"/>' +
                             '</div>'+
                             '<div class="item btn error"></div>'+
                         '</form>'+
                     '</div>' +
                 '</div>'+
             '</div>'
     });
     
  
     $("#userLogin_btn").on("click",function(){
     	if(checklogin()){
        		 $.ajax({
         			url: "user/userLogin",
         	        type: "POST",
         	        dataType: "json",
         	        async: false,
         	        data:$("#userLogin_form").serialize(),
         	        success: function (json) {
         	        	if(json.result == 1){
         	        		location.replace("front");
         	        	}
         	        	else{
         	        		alert(json.errorInfo);
         	        	}
         	        },
         	        error:function(){
         	        	alert("连接失败");
         	        }
         		}); 
     	}
     	
     });
     
 });

 /*控制提注册通过AJAX提交数据的代码*/
 $('#userRegAlert_btn').click(function(){
     layer.open({
         type: 1,
         title: "",
         anim: 1,
         area: ['500px', '450px'], //宽高
         content: '' +
         '<div class="wrapper">'+
             '<div id="content">' +
                 '<h1>欢迎注册</h1>' +
                 '<div class="article">'+
                     '<form id="userReg_form">' +
                         '<div class="item">' +
                             '<label>帐号</label>' +
                             '<input id="r_user" name="userAccount" type="text" class="basic-input" placeholder="邮箱/手机号/用户名"/>' +
                         '</div>'+
                         '<div class="item">' +
                             '<label>密码</label>' +
                             '<input id="r_password" name="userPassword" type="password" class="basic-input"/>' +
                         '</div>'+
                         '<div class="item">' +
                             '<label>昵称</label>' +
                             '<input id="nickname" name="userNickname" type="password" class="basic-input"/>' +
                         '</div>'+
                         '<div class="item btn">' +
                             '<label>&nbsp;</label>' +
                             '<input id="userReg_btn" type="button" value="注册" class="btn-submit"/>' +
                         '</div>'+
                         '<div class="item btn error"></div>'+
                     '</form>'+
                 '</div>' +
             '</div>'+
         '</div>'
     });
     
     $("#userReg_btn").on("click",function(){
     	if(registercheck()){
     		 $.ajax({
      			url: "user/userRegister",
      	        type: "POST",
      	        dataType: "json",
      	        async: false,
      	        data:$("#userReg_form").serialize(),
      	        success: function (json) {
      	        	if(json.result == 1){
      	        		 $('.error').html("注册成功！ 请登录。。");
      	        	}
      	        	else{
      	        		$('.error').html(json.errorInfo);
      	        	}
      	        },
      	        error:function(){
      	        	alert("连接失败");
      	        }
      		}); 
     	}
     	
     });
     
 });
 
});