import Vue from "vue";

import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import {
  faSubway,
  faStore,
  faMugHot,
  faPiggyBank,
  faHotel,
  faBaby,
  faChild,
  faSchool,
  faUniversity,
  faUserGraduate,
  faVideo,
  faUserShield,
  faArrowCircleLeft,
  faArrowCircleRight,
  faLink,
  faRedo,
  faUndo,
} from "@fortawesome/free-solid-svg-icons";
import { faTrashAlt } from "@fortawesome/free-regular-svg-icons";

library.add(faTrashAlt);
library.add(
  faLink,
  faRedo,
  faUndo,
  faSubway,
  faStore,
  faMugHot,
  faPiggyBank,
  faHotel,
  faBaby,
  faChild,
  faSchool,
  faUniversity,
  faUserGraduate,
  faVideo,
  faUserShield,
  faArrowCircleLeft,
  faArrowCircleRight
);

Vue.component("font-awesome-icon", FontAwesomeIcon);
