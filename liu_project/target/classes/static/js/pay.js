
$(function () {
f();
f1();

$('#pay').on('click',function () {
    var address =$('#address').text();
    var name = $('#name').text();
    var phone = $('#phone').text();
   $.ajax({
       url:"http://localhost:8099/addMoney",
       data:{address:address,name:name,phone:phone},
       success:function (data) {
            if (data=='1') {
                window.location.href="success.html";
            }
            else {
                alert('提交失败')
            }
       },
       error:function () {
           alert('服务器异常');
       }
   })
});
});
function f() {
    $.ajax({
        url:"http://localhost:8099/getAddress",
        success:function (data) {
            var html = "";
            for (var i = 0;i<data.length;i++) {
                //defaultAddr  注意这个这是选择哪个的
                html+="<li class=\"user-addresslist\" onclick='address(this)'>\n" +
                    "                    <div class=\"address-left\">\n" +
                    "                        <div class=\"user DefaultAddr\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t<span class=\"buy-address-detail\">\n" +
                    "                   <span class=\"buy-user\">"+data[i].name+"</span>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<span class=\"buy-phone\">"+data[i].phone+"</span>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</span>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"default-address DefaultAddr\">\n" +
                    "                            <span class=\"buy-line-title buy-line-title-type\">收货地址：</span>\n" +
                    "                            <span class=\"buy--address-detail\">\n" +
                    "                                "+data[i].address_+"\n" +
                    "                            </span>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </li>";
            }
            $('#ul001').html(html);
        },
        error:function () {
            alert('服务器异常')
        }
    })
};
function address(th) {
    $(th).addClass("defaultAddr").siblings().removeClass("defaultAddr");
    alert($(th).find('.buy--address-detail').text());
    $('#address').text($(th).find('.buy--address-detail').text());
    $('#name').text($(th).find('.buy-user').text());
    $('#phone').text($(th).find('.buy-phone').text());
};
function f1() {
    $.ajax({
        url: "http://localhost:8099/gem",
        async:false,
        success:function (data) {
            $('#J_ActualFee').text(data);
            f3();
        },
        error:function () {
            alert("服务器异常")
        }
    })
};

function f3() {
    $.ajax({
        url: "http://localhost:8099/getYu",
        async:false,
        success:function (data) {
            alert(data);
            alert($('#J_ActualFee').text());
            $('.pay-sum').text(data);
            if (parseInt($('.pay-sum').text()) < parseInt($('#J_ActualFee').text())) {
                alert($('.pay-sum').text());
                alert($('#J_ActualFee').text());
                alert('余额不足无法提交--------------');
                $('#pay').attr("disabled","disabled");
                // $('#pay').css("color","black");
            }
        },
        error:function () {
            alert("服务器异常")
        }
    })
}