var shijian;
var zhi=-1;
function go(){
    var count = $("#count").val();
    $.ajax({
        type:"get",
        url:"/jisuan/countP",
        data: {"count":count},
        dataType:"text",
        success:function (result){
            if (result == "false") {
                alert("请选择口算题数量！")
            }else{
                $("#shijian").show();
                $("#mubiao").show();
                $(".zan").show();
                $("#guan").show();
                $("#yingshen").hide();
                $("#tishu").text(count).show();
                $("#go").hide();
                $("#xian").show();
                $("#zhi").val("1");
                $("#zheng").val("0");
                $("#ti").text("第1道题")
                $.ajax({
                    type:"get",
                    url:"/jisuan/ti",
                    dataType:"text",
                    success:function(jie){
                        var guo = jie.split("-");
                        $("#first").text(guo[0])
                        $("#second").text(guo[1])
                        $("body").setAttribute("onload",shi())
                    },
                    error:function(){
                        alert("服务器已关闭！")
                    }
                })
            }
        },
        error:function (){
            alert("服务器已关闭！")
        }
    })
}
function shi(){
    zhi=zhi+1;
    $("#miao").text(zhi)
    shijian=setTimeout("shi()",1000);
}
function fan(){
    $("#tishu").text("")
    $("#shijian").hide();
    $("#miao").text("")
    $("#mubiao").hide();
    $("#yingshen").show();
    $("#xian").hide();
    $("#zhi").val("")
    $("#lv").text("-----")
    $("#ti").val("")
    $("#first").val("")
    $("#second").val("");
    $("#count").val("")
    $("#go").show();
    $("#zhanJi").hide();
    zhi=-1;
    clearTimeout(shijian)
    $("body").removeAttribute("onload")
}
function xuan(obj){
    var tishu=$("#tishu").text();
    var fu = $(obj).val();
    var first = $("#first").text();
    var second = $("#second").text();
    var shu = $("#zhi").val();
    var zheng = $("#zheng").val();
    $.ajax({
        type: "get",
        url:"/jisuan/xuan",
        data:{"fu":fu,"first":first,"second":second,"shu":shu,"zheng":zheng,"tishu":tishu},
        dataType: "text",
        success:function (result){
            var jieGuo = result.split("-");
            $("#lv").text(jieGuo[0]);
            $("#ti").text("第"+jieGuo[1]+"道题")
            $("#zhi").val(jieGuo[1])
            $("#zheng").val(jieGuo[2])
            $.ajax({
                type:"get",
                url:"/jisuan/ti",
                dataType:"text",
                success:function (jie){
                    var guo = jie.split("-");
                    $("#first").text(guo[0])
                    $("#second").text(guo[1])
                    if (jieGuo[3]=="good") {
                        alert("您已完成所有的口算题！")
                        $("#zhanJi").show();
                        $(".zan").hide();
                        $("#guan").hide();
                        $("#shijian").hide();
                        clearTimeout(shijian);
                    }
                },
                error:function (){
                    alert("服务器已关闭！")
                }
            })
        },
        error:function (){
            alert("服务器已关闭！")
        }
    })
}
function zan(){
    var val = $("#guan").val();
    if (val == "暂停") {
        $(".zan").hide();
        $("#guan").val("继续")
        clearTimeout(shijian);
    }else{
        $(".zan").show();
        $("#guan").val("暂停")
        shijian=setTimeout("shi()",1000)
    }
}
function zhanJi(){
    var zhengL = $("#lv").text();
    var tS = $("#tishu").text();
    var zhengG=$("#zheng").text();
    $.ajax({
        type:"get",
        url:"/jisuan/print",
        data:{"zhengL":zhengL,"tS":tS,"zhengG":zhengG},
        dataType:"text",
        success:function (bor){
            alert("打印成功！")
        },
        error:function (){
            alert("服务器已关闭！")
        }

    })
}

$(document).ready(function(){
    $(".zan").hover(
        function (){
            var fuhaoZ = $(this).val();
            var fuhao = fuhaoZ.substring(0,1);
            $("#fuhao").text(fuhao)
        },
        function(){
            $("#fuhao").text("")
        }
    )
})