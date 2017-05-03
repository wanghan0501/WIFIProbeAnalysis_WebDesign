/*
---------------------------
-Click on the Button Join !-
---------------------------
*/


var t = 0;

function join_1(){
    if(t === 0){
        document.querySelectorAll('.cont_letras > p')[0].style.left = '320px';
        document.querySelectorAll('.cont_letras > p')[1].style.left = '-320px';
        document.querySelectorAll('.cont_letras > p')[2].style.left = '200px';
        setTimeout(function(){
            document.querySelector('.cont_join').className = 'cont_join cont_join_form_act';
        },1000);
    t++;
    }
    else{
        t++;
        $.ajax({
            url:"/userLogin.action",
            type:"get",
            data:{userName:$("#userName").val(),
                    password:$("#password").val()},
            success:function(data){
                alert(data);
            }
        });
        // document.querySelector('.cont_form_join').style.bottom = '-420px';
        // setTimeout(function(){
	     //    document.querySelector('.cont_join').className = 'cont_join cont_join_form_act cont_join_finish';
        // },500);


  }

}



jQuery(document).ready(function () {
    /*
     register.html
     */
    $('#userNameReg').blur(function(){
        var userNameReg = $('#userNameReg');
        var tip = $('#userName_tip');
        if(!(/^1[34578]\d{9}$/.test(userNameReg.val()))){
            tip.addClass("reg-warning");
            tip.text("错误的手机号");
        }else{
            tip.removeClass("reg-warning");
            tip.text("");
        }
        $.ajax({
            url:"/userRegister.action",
            type:"get",
            data:{userName:userNameReg.val()},
            success:function (data) {
                if(data === 0){
                    $('#userName_tip').style.color = '#FF3030';
                }
            }
        });
    });

    $("#identify").onclick(function(){
        
        $.ajax({
            url:"/identifyCode",
            type:"get",
            data:{userName:$("#userNameReg").val()},
            success:function () {

            }
        })
    });
    $('#passwordReg').blur(function () {
        var tip = $('#password_tip');
        if($('#passwordReg').val().length < 6){
            tip.text("密码不能短于6位");
            tip.addClass("reg-warning");

        }else{
            tip.removeClass("reg-warning");
            tip.text("");

        }
    });

    $("#password_confirm").blur(function () {
        var tip = $("#password_confirm_tip");
        if($("#password_confirm").val() === $("#passwordReg").val()){
            tip.removeClass("reg-warning");
            tip.text("");
        }else{
            tip.addClass("reg-warning");
            tip.text("两次密码不一致");
        }
    });
    /************************
     * register end
     ***********************/

    /**********************
     * index.html, login
     ************************/
    $("#userName").blur(function () {
        var tip = $("#login_username_tip");
        $.ajax({
            url:"/verifyUser",
            type:"get",
            data:{userName:$("#userName").val()},
            success: function (data) {
                if(data === "failed"){
                    tip.addClass("reg-warning");
                    tip.text("该用户不存在");
                }
                else{
                    tip.addClass("login-welcome");
                    tip.text("欢迎您,"+data);
                }
            }
        });
    });

});