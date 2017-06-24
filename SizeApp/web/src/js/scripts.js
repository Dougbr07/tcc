$(document).ready(function () {
  $("#foto").change(function (e) {
    var img = URL.createObjectURL(e.target.files[0]);
    $("#imgField").attr('src', img);
  });
  $("#imgField").click(function () {
    $("#foto").click();
  });
});



