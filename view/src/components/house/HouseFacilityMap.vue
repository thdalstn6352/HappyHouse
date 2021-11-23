/* eslint-disable prettier/prettier */
<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      markerList: [],
      // markers: [],
      infowindow: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
      // this.subwayMarker();
    } else {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0d37d49d78548a92b291d50d3cd53132";
      document.head.appendChild(script);
    }
  },
  watch: {
    markers: function () {
      this.displayMarker();
    },
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.house.y, this.house.x),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);

      var circle = new kakao.maps.Circle({
        center: new kakao.maps.LatLng(this.house.y, this.house.x), // 원의 중심좌표 입니다
        radius: 500, // 미터 단위의 원의 반지름입니다
        strokeWeight: 5, // 선의 두께입니다
        strokeColor: "#75B8FA", // 선의 색깔입니다
        strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: "dashed", // 선의 스타일 입니다
        fillColor: "#CFE7FF", // 채우기 색깔입니다
        fillOpacity: 0.7, // 채우기 불투명도 입니다
      });

      // 지도에 원을 표시합니다
      circle.setMap(this.map);
    },
    displayMarker() {
      if (this.markerList.length > 0) {
        for (var i = 0; i < this.markerList.length; i++) {
          this.markerList[i].setMap(null);
        }
        this.markerList = [];
      }
      // let MarkerImageSrc =
      // "https://www.flaticon.com/premium-icon/metro-station_1183133";
      // "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      // "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/category.png";
      let positions = [];

      // eslint-disable-next-line prettier/prettier
      this.markers.forEach(marker => {
        let position = new kakao.maps.LatLng(marker.y, marker.x);
        positions.push(position);
      });
      console.log(positions);
      // this.createMarkers(positions, this.markerList, MarkerImageSrc);
      this.createMarkers(positions, this.markerList);
      this.setMarkers(this.markerList);
    },
    createMarkers(locations, list) {
      for (var i = 0; i < locations.length; i++) {
        // var imageSize = new kakao.maps.Size(24, 35);

        // let marker = this.createMarker(locations[i], markerImage);
        let marker = this.createMarker(locations[i]);

        // 생성된 마커를 커피숍 마커 배열에 추가합니다
        list.push(marker);
      }
      console.log(list);
    },

    // 좌표와 마커이미지를 받아 마커를 생성하여 리턴하는 함수입니다
    createMarker(position) {
      var marker = new kakao.maps.Marker({
        position: position,
      });

      return marker;
    },
    // createMarker(position, image) {
    //   var marker = new kakao.maps.Marker({
    //     position: position,
    //     image: image,
    //   });

    //   return marker;
    // },
    setMarkers(markers) {
      for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(this.map);
      }
    },
    // clearMarkers(markers) {
    //   for (var i = 0; i < markers.length; i++) {
    //     markers[i].setMap(null);
    //   }
    // },
    // displayInfoWindow() {
    //   if (this.infowindow && this.infowindow.getMap()) {
    //     //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
    //     this.map.setCenter(this.infowindow.getPosition());
    //     return;
    //   }

    //   var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    //     iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
    //     iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    //   this.infowindow = new kakao.maps.InfoWindow({
    //     map: this.map, // 인포윈도우가 표시될 지도
    //     position: iwPosition,
    //     content: iwContent,
    //     removable: iwRemoveable,
    //   });

    //   this.map.setCenter(iwPosition);
    // },
  },
  computed: {
    ...mapState(houseStore, ["house", "markers"]),
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 70%;
  height: 600px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
