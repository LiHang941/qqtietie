/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// identity function for calling harmony imports with the correct context
/******/ 	__webpack_require__.i = function(value) { return value; };
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 438);
/******/ })
/************************************************************************/
/******/ ({

/***/ 250:
/***/ (function(module, exports) {

module.exports = {
  "label-text": {
    "color": "#0F0F0D",
    "fontSize": 30,
    "textAlign": "left",
    "textOverflow": "ellipsis",
    "lineHeight": 35,
    "width": 600
  },
  "body": {
    "flexDirection": "column",
    "alignItems": "center",
    "backgroundColor": "#f5f5f9"
  },
  "list": {
    "paddingLeft": 30,
    "paddingRight": 30
  },
  "list-div": {
    "borderBottomStyle": "solid",
    "borderBottomColor": "#c0c0c0",
    "borderBottomWidth": 1,
    "height": 110,
    "flexDirection": "row",
    "justifyContent": "space-between",
    "alignItems": "center",
    "padding": 20
  },
  "list-text": {
    "fontSize": 25,
    "lineHeight": 70
  }
}

/***/ }),

/***/ 301:
/***/ (function(module, exports) {

module.exports = {
  "type": "scroller",
  "classList": [
    "body"
  ],
  "children": [
    {
      "type": "text",
      "classList": [
        "label-text"
      ],
      "style": {
        "color": "#FF4500",
        "marginTop": 20
      },
      "attr": {
        "value": "说明"
      }
    },
    {
      "type": "text",
      "classList": [
        "label-text"
      ],
      "style": {
        "fontSize": 25,
        "marginTop": 20,
        "marginBottom": 50
      },
      "attr": {
        "value": "--这里给出的仅仅是一些基础的配置模板,下个版本将会推出网络模板，"
      }
    },
    {
      "type": "div",
      "classList": [
        "list"
      ],
      "repeat": {
        "expression": function () {return this.items},
        "value": "item"
      },
      "attr": {
        "index": function () {return this.$index}
      },
      "events": {
        "click": "click"
      },
      "children": [
        {
          "type": "div",
          "classList": [
            "list-div"
          ],
          "children": [
            {
              "type": "text",
              "classList": [
                "label-text",
                "list-text"
              ],
              "attr": {
                "value": function () {return this.item.text}
              }
            }
          ]
        }
      ]
    }
  ]
}

/***/ }),

/***/ 353:
/***/ (function(module, exports) {

module.exports = function(module, exports, __weex_require__){"use strict";

var navigator = __weex_require__('@weex-module/navigator');
module.exports = {
    data: function () {return {
        items: [{ text: "垂直", type: 1 }, { text: "水平", type: 2 }, { text: "右上", type: 3 }, { text: "右下", type: 4 }]

    }},
    methods: {
        click: function click(e) {
            var index = e.target.attr.index;
            var type = this.items[index].type;
            var url = "assets://setting.js" + "?type=" + type;
            var params = { 'url': url, 'animated': 'true' };
            navigator.push(params, function () {});
        }
    }
};}
/* generated by weex-loader */


/***/ }),

/***/ 438:
/***/ (function(module, exports, __webpack_require__) {

var __weex_template__ = __webpack_require__(301)
var __weex_style__ = __webpack_require__(250)
var __weex_script__ = __webpack_require__(353)

__weex_define__('@weex-component/90c57ab48263f10d1d8e2f47f0410de5', [], function(__weex_require__, __weex_exports__, __weex_module__) {

    __weex_script__(__weex_module__, __weex_exports__, __weex_require__)
    if (__weex_exports__.__esModule && __weex_exports__.default) {
      __weex_module__.exports = __weex_exports__.default
    }

    __weex_module__.exports.template = __weex_template__

    __weex_module__.exports.style = __weex_style__

})

__weex_bootstrap__('@weex-component/90c57ab48263f10d1d8e2f47f0410de5',undefined,undefined)

/***/ })

/******/ });