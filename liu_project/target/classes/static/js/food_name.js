    $(function () {
    f();
});
function f() {
    $.ajax({
        url:"http://localhost:8099/getfn",
        data:{name:'food_yinyongpin'},
        success:function (data) {
            var html = "<dt><span title=\"生命之源\">生命之源</span></dt>";
            for (var i = 0;i<data.length;i++) {
                html+="<dd><button onclick='ffn(this)' name='"+data[i].price+"' class='饮用品' value='"+data[i].total+"'>"+data[i].name_+"</button></dd>";
            }
            $('#yinyongpin').html(html);
        },
        error:function () {
            alert("服务器异常请联系管理员")
        }
    });

    $.ajax({
        url:"http://localhost:8099/getfn",
        data:{name:'food_binggan'},
        success:function (data) {
            var html = "<dt><span title=\"羞答答\">羞答答</span></dt>";
            for (var i = 0;i<data.length;i++) {
                html+="<dd><button onclick='ffn(this)' name='"+data[i].price+"' class='饼干' value='"+data[i].total+"'>"+data[i].name_+"</button></dd>";
            }
            $('#binggan').html(html);
        },
        error:function () {
            alert("服务器异常请联系管理员")
        }
    });
    $.ajax({
        url:"http://localhost:8099/getfn",
        data:{name:'food_shushi'},
        success:function (data) {
            var html = "<dt><span title=\"马饥达\">马饥达</span></dt>";
            for (var i = 0;i<data.length;i++) {
                html+="<dd><button onclick='ffn(this)' name='"+data[i].price+"' class='熟食' value='"+data[i].total+"'>"+data[i].name_+"</button></dd>";
            }
            $('#shushi').html(html);
        },
        error:function () {
            alert("服务器异常请联系管理员")
        }
    });
    $.ajax({
        url:"http://localhost:8099/getfn",
        data:{name:'food_sushi'},
        success:function (data) {
            var html = "<dt><span title=\"凉菜\">凉菜</span></dt>";
            for (var i = 0;i<data.length;i++) {
                html+="<dd><button onclick='ffn(this)' name='"+data[i].price+"' class='素食' value='"+data[i].total+"'>"+data[i].name_+"</button></dd>";
            }
            $('#sushi').html(html);
        },
        error:function () {
            alert("服务器异常请联系管理员")
        }
    });
    $.ajax({
        url:"http://localhost:8099/getfn",
        data:{name:'food_jianguo'},
        success:function (data) {
            var html = "<dt><span title=\"对对对\">对对对</span></dt>";
            for (var i = 0;i<data.length;i++) {
                html+="<dd><button onclick='ffn(this)' name='"+data[i].price+"' class='坚果' value='"+data[i].total+"'>"+data[i].name_+"</button></dd>";
            }
            $('#jianguo').html(html);
        },
        error:function () {
            alert("服务器异常请联系管理员")
        }
    });

    $.ajax({
        url:"http://localhost:8099/getfn",
        data:{name:'food_tangguo'},
        success:function (data) {
            var html = "<dt><span title=\"甜甜甜\">甜甜甜</span></dt>";
            for (var i = 0;i<data.length;i++) {
                html+="<dd><button onclick='ffn(this)' name='"+data[i].price+"' class='糖果' value='"+data[i].total+"'>"+data[i].name_+"</button></dd>";
            }
            $('#tangguo').html(html);
        },
        error:function () {
            alert("服务器异常请联系管理员")
        }
    });

    $.ajax({
        url:"http://localhost:8099/getfn",
        data:{name:'food_qiangkeli'},
        success:function (data) {
            var html = "<dt><span title=\"蛋糕\">巧克力</span></dt>";
            for (var i = 0;i<data.length;i++) {
                html+="<dd><button onclick='ffn(this)' name='"+data[i].price+"' class='巧克力' value='"+data[i].total+"'>"+data[i].name_+"</button></dd>";
            }
            $('#qiaokeli').html(html);
        },
        error:function () {
            alert("服务器异常请联系管理员")
        }
    });
    $.ajax({
        url:"http://localhost:8099/getfn",
        data:{name:'food_haiwei'},
        success:function (data) {
            var html = "<dt><span title=\"蓝色恐怖\">蓝色恐怖</span></dt>";
            for (var i = 0;i<data.length;i++) {
                html+="<dd><button onclick='ffn(this)' name='"+data[i].price+"' class='海味' value='"+data[i].total+"'>"+data[i].name_+"</button></dd>";
            }
            $('#haiwei').html(html);
        },
        error:function () {
            alert("服务器异常请联系管理员")
        }
    });
    $.ajax({
        url:"http://localhost:8099/getfn",
        data:{name:'food_huacha'},
        success:function (data) {
            var html = "<dt><span title=\"稳稳稳\">稳稳稳</span></dt>";
            for (var i = 0;i<data.length;i++) {
                html+="<dd><button onclick='ffn(this)' name='"+data[i].price+"' class='花茶' value='"+data[i].total+"'>"+data[i].name_+"</button></dd>";
            }
            $('#huacha').html(html);
        },
        error:function () {
            alert("服务器异常请联系管理员")
        }
    });

    $.ajax({
        url:"http://localhost:8099/getfn",
        data:{name:'food_zahuopu'},
        success:function (data) {
            var html = "<dt><span title=\"杂杂杂\">杂杂杂</span></dt>";
            for (var i = 0;i<data.length;i++) {
                html+="<dd><button onclick='ffn(this)' name='"+data[i].price+"' class='杂货铺' value='"+data[i].total+"'>"+data[i].name_+"</button></dd>";
            }
            $('#zahuopu').html(html);
        },
        error:function () {
            alert("服务器异常请联系管理员")
        }
    });

};

if ($(window).width() < 640) {
    function autoScroll(obj) {
        $(obj).find("ul").animate({
            marginTop: "-39px"
        }, 500, function() {
            $(this).css({
                marginTop: "0px"
            }).find("li:first").appendTo(this);
        })
    };
    $(function() {
        setInterval('autoScroll(".demo")', 3000);
    })
};

var i = 0;
$(function () {
    ff();
});
function ff() {

    setTimeout(function () {
        if (i<4) {
            i++;
            $('.lun').attr('src','../../newImages/'+i+'.jpg');
            ff();   //递归调用
        }
        else {
            i = 0;
            ff();  //递归调用
        }
    },4000)
};
function ffn(th) {
    alert('sssss');
    $('#foodTotal').append("<div class='divcon' style=\"width: 15%;height:130px;border: 1px solid brown;border-radius:25px \" >\n" +
        "        <div style=\"text-align: center\" class=\"divs\">\n" +
        "            <span>货品类：</span><span class='huopinlei'>"+$(th).attr('class')+"</span><br>\n" +
        "            <span>货品名：</span><span class='huopinming'>"+$(th).text()+"</span>\n" +
        "            <br>\n" +
        "            <span>价格：</span><span class='jiage'>"+$(th).attr('name')+"</span>\n" +
        "            <br>\n" +
        // "            <span>存货：</span><span class='cunhuo'>"+$(th).val()+"</span>\n" +
        "        </div>\n" +
        "        <div class=\"divb\">\n" +
        "            <button class=\"bb1\">立即购买</button>\n" +
        "            <button class=\"bb2\" onclick='addCar(this)'>加入购物车</button>\n" +
        "        </div>\n" +
        "    </div>");
};
function addCar(th) {
    var huopinlei = $(th).parent().parent().find('.divs').find('.huopinlei').text();
    var huopinming = $(th).parent().parent().find('.divs').find('.huopinming').text();
    var jiage = $(th).parent().parent().find('.divs').find('.jiage').text();
    var cunhuo = $(th).parent().parent().find('.divs').find('.cunhuo').text();
    $.ajax({
        url:"http://localhost:8099/addCar",
        data:{huopinlei:huopinlei,huopinming:huopinming,jiage:jiage,cunhuo:cunhuo},
        success:function (data) {
            if (data=='1') {
                alert('加入购物车成功')
            }
            else {
                alert('加入购物车失败')
            }
        },
        error:function () {
            alert('服务器异常，请联系管理员')
        }
    })
}