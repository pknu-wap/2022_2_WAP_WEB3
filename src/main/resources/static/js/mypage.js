
// 모달창에서 입력받은 input값들
const artistName = document.getElementById('artistName');
const genre = document.getElementById('genre');
const message = document.getElementById('message');
const changeBtn = document.getElementById('changeBtn');
const topName = document.getElementById('topName');

var uploadImg = document.getElementById('imgUpload');  // 모달창에서 업로드한 이미지 객체
var printImg = document.querySelector('.profileImg');  // 이미지 출력되는 프로필 부분 객체

//const mypageForm = document.getElementById('mypageForm');

function print_profile(event) {

  // 모달창에서 입력받은 내용 전달하기
  document.getElementById('resultName').value = artistName.value;
  document.getElementById('resultGenre').value = genre.value;
  document.getElementById('resultMsg').value = message.value;
  topName.textContent = artistName.value;
  $('#staticBackdrop').modal("hide");


  // 입력받은 파일로 프로필 이미지 띄우기
  var imgFile = uploadImg.files[0];
  printImg.src = URL.createObjectURL(imgFile);

  /*var jsonData = {
      "Name":  artistName.value,
      "Genre": genre.value,
      "Msg": message.value,
      "imgUrl": URL.createObjectURL(imgFile)
    };*/

  var form = $('#mypageForm')[0];
  var formData = new FormData(form);

  // ajax로 formdata 전송
  $.ajax({
    url: "/mypage/post",
    type: "POST",
    async: true,
    enctype: 'multipart/form-data',
    data: formData,
    contentType: "application/json; charset=utf-8",
    processData: false,
    contentType: false,

    success: function (data) {
      console.log("Success");
    },
    error: function (xhr, status, error) {
      console.log("Error");
    },
    complete: function (xhr, status) {
      console.log("Complete");
    }
  });


}
changeBtn.addEventListener('click', print_profile);



window.onload = function () {

  $.ajax({
    url: "/mypage/info",
    type: "GET",
    async: true,

    success: function (data) {
      // mypage 디폴트 값에 서버에서 받은 값 넣어주기
      document.getElementById('resultName').value = data.artistName;
      document.getElementById('resultGenre').value = data.genre;
      document.getElementById('resultMsg').value = data.message;
      printImg.src = "/image/?imageName=" + data.imageName;

    },
    error: function (xhr, status, error) {
      console.log("Error");
    },
    complete: function (xhr, status) {
      console.log("Complete");
    }
  });

};