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
/******/ 	return __webpack_require__(__webpack_require__.s = 436);
/******/ })
/************************************************************************/
/******/ ({

/***/ 248:
/***/ (function(module, exports) {

module.exports = {
  "body": {
    "flexDirection": "column",
    "alignItems": "center",
    "backgroundColor": "#f5f5f9"
  },
  "company-item": {
    "flexDirection": "row",
    "position": "absolute",
    "bottom": 0,
    "width": 750
  },
  "company-title": {
    "flex": 1,
    "color": "#2F4056",
    "textAlign": "center",
    "fontSize": 25,
    "marginBottom": 15
  },
  "label-text": {
    "color": "#0F0F0D",
    "fontSize": 30,
    "textAlign": "left",
    "textOverflow": "ellipsis",
    "lineHeight": 35,
    "width": 600
  },
  "list": {
    "paddingLeft": 30,
    "paddingRight": 30,
    "width": 750
  },
  "list-div": {
    "borderBottomStyle": "solid",
    "borderBottomColor": "#c0c0c0",
    "borderBottomWidth": 1,
    "height": 100,
    "width": 690,
    "flexDirection": "row",
    "justifyContent": "flex-start",
    "alignItems": "center",
    "padding": 20
  },
  "input": {
    "height": 70,
    "width": 530,
    "lineHeight": 35,
    "fontSize": 25,
    "backgroundColor": "#F0F0F0",
    "marginLeft": 25,
    "borderRadius": 15,
    "paddingLeft": 5
  },
  "list-text": {
    "fontSize": 25,
    "lineHeight": 70,
    "width": 100
  }
}

/***/ }),

/***/ 299:
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
        "color": "#1E9FFF",
        "marginTop": 20,
        "width": 690
      },
      "attr": {
        "value": "更新:"
      }
    },
    {
      "type": "text",
      "classList": [
        "label-text"
      ],
      "style": {
        "fontSize": 25,
        "color": "#2F4056",
        "marginTop": 20
      },
      "attr": {
        "value": "--新增自定义贴表情位置"
      }
    },
    {
      "type": "text",
      "classList": [
        "label-text"
      ],
      "style": {
        "fontSize": 25,
        "color": "#2F4056",
        "marginTop": 20
      },
      "attr": {
        "value": "--新增自动更新"
      }
    },
    {
      "type": "text",
      "classList": [
        "label-text"
      ],
      "style": {
        "color": "#1E9FFF",
        "marginTop": 20,
        "width": 690
      },
      "attr": {
        "value": "计划:"
      }
    },
    {
      "type": "text",
      "classList": [
        "label-text"
      ],
      "style": {
        "fontSize": 25,
        "color": "#2F4056",
        "marginTop": 20
      },
      "attr": {
        "value": "--模板分享功能"
      }
    },
    {
      "type": "text",
      "classList": [
        "label-text"
      ],
      "style": {
        "color": "#1E9FFF",
        "marginTop": 20,
        "width": 690
      },
      "attr": {
        "value": "结尾:"
      }
    },
    {
      "type": "text",
      "classList": [
        "label-text"
      ],
      "style": {
        "fontSize": 25,
        "color": "#2F4056",
        "marginTop": 20
      },
      "attr": {
        "value": "--希望大家关注我的微信公众号,谢谢"
      }
    },
    {
      "type": "text",
      "classList": [
        "label-text"
      ],
      "style": {
        "fontSize": 25,
        "color": "#2F4056",
        "marginTop": 20,
        "marginBottom": 50
      },
      "attr": {
        "value": "--快去QQ里面愉快的装B吧^.^"
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
            },
            {
              "type": "input",
              "classList": [
                "input"
              ],
              "attr": {
                "value": function () {return this.item.value}
              }
            }
          ]
        }
      ]
    },
    {
      "type": "div",
      "classList": [
        "company-item"
      ],
      "children": [
        {
          "type": "text",
          "classList": [
            "company-title"
          ],
          "attr": {
            "value": function () {return '版本号: ' + (this.version)}
          }
        }
      ]
    }
  ]
}

/***/ }),

/***/ 351:
/***/ (function(module, exports) {

module.exports = function(module, exports, __weex_require__){"use strict";

module.exports = {
    data: function () {return {
        version: "未知"
    }},
    methods: {
        items: [{ text: "GitHub", value: "https://github.com/wawa2222/qqtietie" }, { text: "博客", value: "http://www.lihang.xyz" }, { text: "公众号", value: "微信搜索:lihangBlog" }]
    },
    created: function created() {
        var self = this;
        __weex_require__('@weex-module/configModule').getversion(function (res) {
            self.version = res;
        });
    }
};}
/* generated by weex-loader */


/***/ }),

/***/ 436:
/***/ (function(module, exports, __webpack_require__) {

var __weex_template__ = __webpack_require__(299)
var __weex_style__ = __webpack_require__(248)
var __weex_script__ = __webpack_require__(351)

__weex_define__('@weex-component/9976919ce5c3b9147aa74ae010073785', [], function(__weex_require__, __weex_exports__, __weex_module__) {

    __weex_script__(__weex_module__, __weex_exports__, __weex_require__)
    if (__weex_exports__.__esModule && __weex_exports__.default) {
      __weex_module__.exports = __weex_exports__.default
    }

    __weex_module__.exports.template = __weex_template__

    __weex_module__.exports.style = __weex_style__

})

__weex_bootstrap__('@weex-component/9976919ce5c3b9147aa74ae010073785',undefined,undefined)

/***/ })

/******/ });