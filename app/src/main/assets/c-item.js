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
/******/ 	return __webpack_require__(__webpack_require__.s = 434);
/******/ })
/************************************************************************/
/******/ ({

/***/ 172:
/***/ (function(module, exports) {

module.exports = {
  "border": {
    "borderStyle": "solid",
    "borderWidth": 2
  },
  "item": {
    "flexDirection": "row",
    "justifyContent": "space-between",
    "alignItems": "center",
    "width": 600,
    "height": 100
  },
  "item-head": {
    "flexDirection": "row",
    "justifyContent": "flex-start",
    "alignItems": "center"
  },
  "label-text": {
    "color": "#0F0F0D",
    "fontSize": 30,
    "textAlign": "left",
    "textOverflow": "ellipsis",
    "lineHeight": 35
  },
  "error": {
    "color": "#FF4500"
  },
  "wrning": {
    "color": "#FFB800"
  },
  "agree": {
    "color": "#1E9FFF"
  },
  "input": {
    "height": 70,
    "width": 300,
    "lineHeight": 35,
    "fontSize": 30,
    "backgroundColor": "#f2f2f2",
    "marginLeft": 25,
    "borderRadius": 15,
    "paddingLeft": 5
  }
}

/***/ }),

/***/ 178:
/***/ (function(module, exports) {

module.exports = {
  "type": "div",
  "classList": [
    "item"
  ],
  "children": [
    {
      "type": "div",
      "classList": [
        "item-head"
      ],
      "children": [
        {
          "type": "text",
          "classList": [
            "label-text"
          ],
          "attr": {
            "value": function () {return '坐标' + (this.head)}
          }
        },
        {
          "type": "input",
          "classList": [
            "input",
            "border"
          ],
          "attr": {
            "type": "text",
            "placeholder": "请输入表达式",
            "value": function () {return this.value}
          },
          "events": {
            "focus": "onFocus",
            "blur": "onBlur",
            "input": "onInput"
          },
          "style": {
            "borderColor": function () {return this.color}
          }
        }
      ]
    },
    {
      "type": "text",
      "classList": function () {return ['label-text', this.style]},
      "attr": {
        "value": function () {return this.bottom}
      }
    }
  ]
}

/***/ }),

/***/ 184:
/***/ (function(module, exports) {

module.exports = function(module, exports, __weex_require__){"use strict";

module.exports = {
    data: function () {return {
        style: "wrning",
        color: "#f2f2f2",
        head: "",
        value: "",
        flag: "",
        input: function input(flag, head, value) {},
        isagree: function isagree(flag, head, value) {}
    }},
    methods: {
        onFocus: function onFocus() {
            this.color = "#FF5722";
        },
        onBlur: function onBlur() {
            this.color = "#f2f2f2";
        },
        onInput: function onInput(e) {
            this.value = e.value;
            this.input(this.flag, this.head, this.value);
        }
    },
    computed: {
        bottom: function bottom() {
            var value = this.value;
            if (value == null || value.replace(/(^\s*)|(\s*$)/g, "") == "") {
                this.style = "wrning";
                this.isagree(this.flag, this.head, false);
                return "请输入内容";
            }

            if (isNaN(value)) {
                value = value.replace(/(^x)|(^y)/g, "");
                if (value.replace(/(^\s*)|(\s*$)/g, "") == "" || isNaN(value)) {
                    this.style = "error";
                    this.isagree(this.flag, this.head, false);
                    return "不可用";
                } else {
                    this.style = "agree";
                    this.isagree(this.flag, this.head, true);
                    return "可用";
                }
            } else {
                this.style = "agree";
                this.isagree(this.flag, this.head, true);
                return "可用";
            }
        }
    }
};}
/* generated by weex-loader */


/***/ }),

/***/ 434:
/***/ (function(module, exports, __webpack_require__) {

var __weex_template__ = __webpack_require__(178)
var __weex_style__ = __webpack_require__(172)
var __weex_script__ = __webpack_require__(184)

__weex_define__('@weex-component/4ad03fdb90970670e944442f2d434d1b', [], function(__weex_require__, __weex_exports__, __weex_module__) {

    __weex_script__(__weex_module__, __weex_exports__, __weex_require__)
    if (__weex_exports__.__esModule && __weex_exports__.default) {
      __weex_module__.exports = __weex_exports__.default
    }

    __weex_module__.exports.template = __weex_template__

    __weex_module__.exports.style = __weex_style__

})

__weex_bootstrap__('@weex-component/4ad03fdb90970670e944442f2d434d1b',undefined,undefined)

/***/ })

/******/ });