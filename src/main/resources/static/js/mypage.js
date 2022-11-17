
const artistName = document.getElementById('artistName');
const genre = document.getElementById('genre');
const message = document.getElementById('message');
const changeBtn = document.getElementById('changeBtn');
const topName = document.getElementById('topName');


var uploadImg = document.getElementById('imgUpload');  
var printImg = document.querySelector('.profileImg');

/*
myModal.addEventListener('hidden.bs.modal', event => {
    document.getElementById('resultName').value = artistName.value; 
    document.getElementById('resultGenre').value = genre.value;
    document.getElementById('resultMsg').value = message.value;
  })
*/

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

}

changeBtn.addEventListener('click', print_profile);



