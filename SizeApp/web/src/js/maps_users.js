var map;
var marker = null;
var infowindow;
var contentString;
//initialize the map on DOM
function initMap(myLocation) {
  map = new google.maps.Map(document.getElementById('map'), {
    center: myLocation,
    zoom: 14
  });
  addMarker(myLocation, map);
}
function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -9.6458271, lng: -35.7199795},
    zoom: 12
  });
}
// Adds a marker to the map.
function addMarker(location, map, title) {

  if (marker != null) {
    marker.setMap(null);
  }
  marker = new google.maps.Marker({
    position: location,
    map: map,
    title: title
  });
}
function showLocation(x, y, title, endereco, telefone, foto, nota) {
  var location = {lat: x, lng: y};
  addMarker(location, map, title);
  map.setZoom(17);
  map.panTo(location);
  var notaNova = nota.toFixed(1);
  var contentString = '<div class="container-fluid">'+
      '<div class="col-md-3">'+
        '<img style="width: 100%;" class="img-responsive img-circle" src="/SizeApp/WebContent/imagens/estabelecimento/'+foto+'"/>'+
      '</div>'+
      '<div class="col-md-9">'+
        '<div class="col-md-8">'+
          '<h2>'+title+'</h2>'+
          '<p>'+endereco+'</p>'+
          '<a href="callto:+5582'+telefone+'"><h4><span class="glyphicon glyphicon-earphone" style="font-size: 12pt;"></span> '+telefone+'</h4></a>'+
        '</div>'+
        '<div class="col-md-4">'+
          '<h3>'+notaNova+'<span class="glyphicon glyphicon-star" style="font-size: 14pt;"></span></h3>'+
        '</div>'+
      '</div>'+
    '</div>';
  infowindow = new google.maps.InfoWindow({
    content: contentString
  });
  marker.addListener('click', function () {
  infowindow.open(map, marker);
});
}
function setPin(x, y, title) {
  var location = {lat: x, lng: y};
  addMarker(location, map, title);
  map.setZoom(17);
  map.panTo(location);
}
var drawingManager = new google.maps.drawing.DrawingManager();
drawingManager.setMap(map);
//google.maps.event.addDomListener(window, 'load', initMap);