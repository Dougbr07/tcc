var map;
var marker = null;
//initialize the map on DOM
function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -9.6458271, lng: -35.7199795},
    zoom: 12
  });
  google.maps.event.addListener(map, 'click', function(event) {
    addMarker(event.latLng, map);
  });
}
// Adds a marker to the map.
function addMarker(location, map) {
  if(marker!=null){
  	marker.setMap(null);
  }
  marker = new google.maps.Marker({
    position: location,
    map: map
  });
}
var drawingManager = new google.maps.drawing.DrawingManager();
drawingManager.setMap(map);
google.maps.event.addDomListener(window, 'load', initMap);