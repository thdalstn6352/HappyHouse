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
      let positions = [];

      // eslint-disable-next-line prettier/prettier
      this.markers.forEach(marker => {
        console.log(marker);
        let position = new kakao.maps.LatLng(marker.y, marker.x);
        // let content = `<div>${marker.place_name}</div>`;
        let content = `<div class="wrap">
              <div class="info">
                  <div class="title">
                      ${marker.place_name}
                  </div>
                  <div class="body">
                      <div class="desc">
                          <div class="ellipsis">${marker.address_name}</div>
                          <div class="jibun ellipsis">${marker.phone}</div>
                      </div>
                  </div>
              </div>
          </div>`;
        positions.push([position, content]);
      });
      console.log(positions);
      for (let i = 0; i < positions.length; i++) {
        // let marker = this.createMarker(positions[i]);
        var marker = new kakao.maps.Marker({
          position: positions[i][0],
        });

        var infowindow = new kakao.maps.InfoWindow({
          content: positions[i][1],
        });
        kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(this.map, marker, infowindow)
        );
        kakao.maps.event.addListener(
          marker,
          "mouseout",
          this.makeOutListener(infowindow)
        );

        // var overlay = new kakao.maps.CustomOverlay({
        //   content: positions[i][1],
        //   map: this.map,
        //   position: positions[i][0],
        // });

        // kakao.maps.event.addListener(marker, "click", function () {
        //   overlay.setMap(this.map);
        // });

        this.markerList.push(marker);
      }

      for (let i = 0; i < this.markerList.length; i++) {
        this.markerList[i].setMap(this.map);
      }
    },
    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },
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

.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
</style>
