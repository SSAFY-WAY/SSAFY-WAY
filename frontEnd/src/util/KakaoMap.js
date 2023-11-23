import getImageUrl from "@/util/getUrl.js";

const loadMap = (buildingInfo, clickMarker) => {
  if (buildingInfo.buildingList.length === 0) return;
  const firstEl = buildingInfo.buildingList[0];
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(firstEl.lat, firstEl.lng),
    level: 5,
  };
  const map = new kakao.maps.Map(container, options);
  // 마커를 표시할 위치와 title 객체 배열입니다

  // 마커 이미지의 이미지 주소입니다
  const colorMap = {};
  const colorFontMap = {};
  const colorFont = ["#8BDE72", "#916ECB", "#BA5E68", "#0FCD83", "#17C6EA"];
  if (buildingInfo.setDistance) {
    for (let i = 0; i < buildingInfo.subwayList.length; i++) {
      const name = buildingInfo.subwayList[i].name;
      colorMap[name] = getImageUrl(`${5 - i}.png`);
      colorFontMap[name] = colorFont[4 - i];
    }
  }
  /*******************************************매물 마커 */
  var selectedInfowindow = null;
  for (let i = 0; i < buildingInfo.buildingList.length; i++) {
    const building = buildingInfo.buildingList[i];
    // 마커 이미지의 이미지 크기 입니다
    const imageSize = new kakao.maps.Size(33, 33);

    // 마커 이미지를 생성합니다
    let imageSrc = buildingInfo.setDistance
      ? colorMap[building.nearestSubwayName]
      : getImageUrl("5.png");

    const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
    const housePosition = new kakao.maps.LatLng(building.lat, building.lng);
    // 마커를 생성합니다
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: housePosition, // 마커를 표시할 위치
      title: building.buildingName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      image: markerImage, // 마커 이미지
    });
    kakao.maps.event.addListener(marker, "click", () => {
      // 인포윈도우 띄우기
      showInfoWindow(housePosition, marker, marker.getTitle());
      // 디테일 보여주기
      clickMarker(building);
      // 지도 이동
      moveMap(housePosition);
    });
  }
  /**************** buildingInfo[지하철]이 존재하면? (지하철 검색이면) -> 지하철역 마커 띄우기*/
  if (buildingInfo.setDistance) {
    const imageSrc = getImageUrl("metro.png");
    const points = [];
    var bounds = new kakao.maps.LatLngBounds();
    for (let i = 0; i < buildingInfo.subwayList.length; i++) {
      const subway = buildingInfo.subwayList[i];
      // 중심 좌표를 잡기 위해 points만 따로 저장하기
      const subwayPoint = new kakao.maps.LatLng(
        subway.points.lat,
        subway.points.lng
      );
      points.push(points);

      // 마커 이미지의 이미지 크기 입니다
      const imageSize = new kakao.maps.Size(33, 33);

      // 마커 이미지를 생성합니다
      const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      // 마커를 생성합니다
      const stationMarker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        // clickable: true,
        position: subwayPoint, // 마커를 표시할 위치
        title: subway.name, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
      });
      stationMarker.setMap(map);
      // 마커에 클릭이벤트 할당

      bounds.extend(subwayPoint);
      setBounds();

      let $customOverlay = document.createElement("div");
      $customOverlay.className = "customoverlay";
      $customOverlay.addEventListener("click", () => {
        moveMap(subwayPoint);
      });
      $customOverlay.innerHTML =
        `  <a target="_blank" style="background-color : ${
          colorFontMap[`${subway.name}`]
        }" >` +
        `    <span class="title">${subway.name}</span>` +
        "  </a>";

      // 커스텀 오버레이가 표시될 위치입니다

      // 커스텀 오버레이를 생성합니다
      var customOverlay = new kakao.maps.CustomOverlay({
        map: map,
        position: stationMarker.getPosition(),
        content: $customOverlay,
        yAnchor: 1,
      });
      customOverlay.setMap(map);
      // ****************************************원 그리기
      var circle = new kakao.maps.Circle({
        center: new kakao.maps.LatLng(subway.points.lat, subway.points.lng), // 원의 중심좌표 입니다
        radius: buildingInfo.setDistance, // 미터 단위의 원의 반지름입니다
        strokeWeight: 1, // 선의 두께입니다
        strokeColor: "#75B8FA", // 선의 색깔입니다
        strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: "line", // 선의 스타일 입니다
        fillColor: "#E3F2FD", // 채우기 색깔입니다
        fillOpacity: 0.5, // 채우기 불투명도 입니다
      });

      // 지도에 원을 표시합니다
      circle.setMap(map);
    }

    function setBounds() {
      // LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
      // 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
      map.setBounds(bounds);
    }
  }
  function moveMap(subwayPoint) {
    map.panTo(subwayPoint);
  }
  function showInfoWindow(position, marker, name) {
    if (selectedInfowindow) {
      // 이전에 선택된 마커를 원래대로 되돌립니다.
      selectedInfowindow.close();
    }

    // 인포윈도우를 생성합니다
    let infowindow = new kakao.maps.InfoWindow({
      position: position,
      content: `<div
      style="
      display : flex;
      width:180px;
      height:100%;
      justify-content:center;
      align-items:center;
      margin : 0 auto;
      color : white;
      font-weight : bold;
      background-color :  #2196F3;
      "
      >${name}</div>`,
    });

    // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
    infowindow.open(map, marker);
    selectedInfowindow = infowindow;
  }
};

const loadScript = (buildingInfo, clickMarker) => {
  if (buildingInfo.buildingList.length === 0) return;
  const script = document.createElement("script");
  script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${
    import.meta.env.VITE_KAKAO_KEY
  }&autoload=false`;
  script.onload = () =>
    window.kakao.maps.load(() => {
      loadMap(buildingInfo, clickMarker);
    });
  document.head.appendChild(script);
};

export { loadMap, loadScript };
