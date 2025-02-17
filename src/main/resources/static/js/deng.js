function yan(){
    var name = $("#username").val();
    $.ajax({
        type:"get",
        url:"/jisuan/getName",
        data:{"name":name},
        dataType:"text",
        success:function (result){
            if(result=="false"){
                alert("请输入正确的用户名！");
            }else{
                alert("验证码为："+result);
            }
        },
        error:function (){
            alert("服务器已关闭！")
        }
    })
}
function deng(){
    var ming = $("#username").val();
    var yan = $("#yan").val();
    $.ajax({
        type: "get",
        url:"/jisuan/Deng",
        data: {"username":ming,"yan":yan},
        dataType: "text",
        success:function (result){
            if(result=="false"){
                alert("请先获取验证码！");
            }else if(result=="mG"){
                alert("用户名不存在或验证码有误！")
            }else{
                alert("登录成功！")
                location.href="/zhuan/qishi"
            }
        },
        error:function(){
            alert("服务器出错！")
        }
    })
}