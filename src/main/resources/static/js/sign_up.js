


    var password = document.getElementById('password');
    var confirmPassword = document.getElementById('confirmPassword');


    function psReCheck() {
        if (password.value != confirmPassword.value) {
            confirmPassword.setCustomValidity("비밀번호가 일치하지 않습니다.");
            
        }
        else {
            confirmPassword.setCustomValidity("");
            
        }

    }
    password.onchange = psReCheck();
    confirmPassword.onkeyup = psReCheck();


