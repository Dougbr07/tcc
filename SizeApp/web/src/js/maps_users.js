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
function showLocation(x, y, title, contentString) {
  var location = {lat: x, lng: y};
  addMarker(location, map, title);
  map.setZoom(17);
  map.panTo(location);
  infowindow = new google.maps.InfoWindow({
    content: contentString
  });
  marker.addListener('click', function () {
  infowindow.open(map, marker);
});
}
var drawingManager = new google.maps.drawing.DrawingManager();
drawingManager.setMap(map);
//google.maps.event.addDomListener(window, 'load', initMap);