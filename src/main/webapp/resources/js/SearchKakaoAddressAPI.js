const theaLoc_ = document.querySelector('#theaLoc_').value.split(',');
const theaLoc = theaLoc_[1];

let theaName = document.querySelector('#theaName').textContent;
if(theaName.length === 0)
    theaName = document.querySelector('#theaName').value;


//let searchLocation = {x:0,y:0};

//var infowindow = new kakao.maps.InfoWindow({zIndex:1});

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

var callback = function (result, status) {
    // 정상적으로 검색이 완료됐으면
    if (status === kakao.maps.services.Status.OK) {

     /*  console.log(result[0].x,result[0].y);
        searchLocation.x = result[0].x;
        searchLocation.y = result[0].y;
        console.log(searchLocation.x, searchLocation.y);*/

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        
        
        var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 

        
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(30, 40); 
        
        // 마커 이미지를 생성합니다    
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        
        

        // 결과값으로 받은 위치를 마커로 표시합니다
        var mainMarker = new kakao.maps.Marker({
            map: map,
            position: coords,
            image : markerImage
        });
        
        //커스텀 인포
        var content = `<div class ="overlay_info"><span class="text">${theaName}</span></div>`;
        var customOverlay = new kakao.maps.CustomOverlay({
            position: coords,
            content: content,
            xAnchor: 0.5,
            yAnchor: 2.7
        });
        customOverlay.setMap(map);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);


        var ps = new kakao.maps.services.Places(map);
        // 음식점 검색
        ps.categorySearch('FD6', placesSearchCB, {useMapBounds:true});

        // 검색 완료 시 호출되는 콜백함수
        function placesSearchCB (data, status) {
            if (status === kakao.maps.services.Status.OK) {
                for (var i=0; i<data.length; i++) {
                    displayMarker(data[i]);
                }
            }
        }
        var infowindow = new kakao.maps.InfoWindow();

        // 지도에 마커를 표시하는 함수입니다
        function displayMarker(place) {
            // 마커를 생성하고 지도에 표시합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: new kakao.maps.LatLng(place.y, place.x)
            });

            // 마커에 클릭이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'mouseover', function() {
                // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
                infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
                infowindow.open(map, marker);
            });
        }
    }
}

function getContextPath() {

   var hostIndex = location.href.indexOf( location.host ) + location.host.length;

   return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );

}


// 주소로 좌표를 검색합니다
geocoder.addressSearch(theaLoc, callback);