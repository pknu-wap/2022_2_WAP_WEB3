var password = document.getElementById('password');
var confirmPassword = document.getElementById('confirmPassword');

// password와 confirmPassword의 일치 여부 확인
function psReCheck() {
    if (password.value != confirmPassword.value) {
        confirmPassword.setCustomValidity("비밀번호가 일치하지 않습니다.");
    }
    else {
        confirmPassword.setCustomValidity("");
    }
}
confirmPassword.onkeyup = psReCheck();


// password는 4자 이상 ~ 16자 이하의 값
function psLengthCheck() {
    if (password.value.length < 4) {
        password.setCustomValidity("네 글자 이상 입력해주세요.");
    }
    else {
        password.setCustomValidity("");
    }
}
password.onkeyup = psLengthCheck();

