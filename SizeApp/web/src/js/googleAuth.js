var googleClicked = false;
function onSignIn(response) {

  // Conseguindo as informações do seu usuário:
  var perfil = response.getBasicProfile();
  // Conseguindo o ID do Usuário
  var userID = perfil.getId();
  // Conseguindo o Nome do Usuário
  var userName = perfil.getName();
  // Conseguindo o E-mail do Usuário
  var userEmail = perfil.getEmail();
  // Conseguindo a URL da Foto do Perfil
  var userPicture = perfil.getImageUrl();
  // Recebendo o TOKEN que você usará nas demais requisições à API:
  var LoR = response.getAuthResponse().id_token;
  document.getElementById('idToken').value = LoR;
  if (googleClicked) {
    document.getElementById('googleForm:googleBtn').click();
  }
}
;
$("#googleButton").click(function () {
  googleClicked = true;
});