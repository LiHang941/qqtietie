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
/******/ 	return __webpack_require__(__webpack_require__.s = 437);
/******/ })
/************************************************************************/
/******/ ({

/***/ 100:
/***/ (function(module, exports, __webpack_require__) {

var core = __webpack_require__(4),
    $JSON = core.JSON || (core.JSON = { stringify: JSON.stringify });
module.exports = function stringify(it) {
  // eslint-disable-line no-unused-vars
  return $JSON.stringify.apply($JSON, arguments);
};

/***/ }),

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

/***/ 199:
/***/ (function(module, exports, __webpack_require__) {

var __weex_template__ = __webpack_require__(178)
var __weex_style__ = __webpack_require__(172)
var __weex_script__ = __webpack_require__(184)

__weex_define__('@weex-component/c-item', [], function(__weex_require__, __weex_exports__, __weex_module__) {

    __weex_script__(__weex_module__, __weex_exports__, __weex_require__)
    if (__weex_exports__.__esModule && __weex_exports__.default) {
      __weex_module__.exports = __weex_exports__.default
    }

    __weex_module__.exports.template = __weex_template__

    __weex_module__.exports.style = __weex_style__

})


/***/ }),

/***/ 249:
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
  "btn": {
    "color": "#FFFFFF",
    "fontSize": 30,
    "textAlign": "center",
    "lineHeight": 50,
    "backgroundColor": "#009688",
    "width": 250,
    "height": 50,
    "borderRadius": 10
  },
  "body": {
    "flexDirection": "column",
    "alignItems": "center",
    "backgroundColor": "#f5f5f9",
    "width": 750
  },
  "items": {
    "flexDirection": "column",
    "alignItems": "center",
    "width": 650,
    "marginTop": 10,
    "borderRadius": 30,
    "backgroundColor": "#e2e2e2"
  },
  "head": {
    "marginTop": 5,
    "color": "#0F0F0D",
    "fontSize": 25,
    "textAlign": "center",
    "lineHeight": 40,
    "height": 40,
    "width": 590
  },
  "rightbtn": {
    "marginTop": 0,
    "backgroundColor": "#FF5722",
    "width": 60
  },
  "headdiv": {
    "flexDirection": "row",
    "alignItems": "center",
    "justifyContent": "flex-start",
    "width": 650
  }
}

/***/ }),

/***/ 300:
/***/ (function(module, exports) {

module.exports = {
  "type": "div",
  "classList": [
    "body"
  ],
  "children": [
    {
      "type": "scroller",
      "classList": [
        "body"
      ],
      "style": {
        "backgroundColor": "#FFFFFF"
      },
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
            "marginTop": 20
          },
          "attr": {
            "value": "--x表示前一个表情的x坐标,y表示前一个表情的y坐标"
          }
        },
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
            "value": "格式:"
          }
        },
        {
          "type": "text",
          "classList": [
            "label-text"
          ],
          "style": {
            "fontSize": 25,
            "color": "#FF0000",
            "marginTop": 20
          },
          "attr": {
            "value": "--注意:不包含花括号{}"
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
            "marginBottom": 20
          },
          "attr": {
            "value": "--例如:{50.5},{x+1},{y+1},{100},"
          }
        },
        {
          "type": "div",
          "classList": [
            "items"
          ],
          "repeat": {
            "expression": function () {return this.items},
            "value": "item"
          },
          "children": [
            {
              "type": "div",
              "classList": [
                "headdiv"
              ],
              "children": [
                {
                  "type": "text",
                  "classList": [
                    "head"
                  ],
                  "attr": {
                    "value": function () {return '第' + (this.$index+1) + '个表情'}
                  }
                },
                {
                  "type": "text",
                  "shown": function () {return this.$index!=0},
                  "classList": [
                    "head",
                    "rightbtn"
                  ],
                  "events": {
                    "click": "remove"
                  },
                  "attr": {
                    "index": function () {return this.$index},
                    "value": "移除"
                  }
                }
              ]
            },
            {
              "type": "c-item",
              "attr": {
                "head": function () {return this.item.x.head},
                "flag": function () {return this.$index},
                "value": function () {return this.item.x.value},
                "input": function () {return this.input},
                "isagree": function () {return this.isagree}
              }
            },
            {
              "type": "c-item",
              "attr": {
                "head": function () {return this.item.y.head},
                "flag": function () {return this.$index},
                "value": function () {return this.item.y.value},
                "input": function () {return this.input},
                "isagree": function () {return this.isagree}
              }
            }
          ]
        }
      ]
    },
    {
      "type": "div",
      "style": {
        "flexDirection": "row",
        "justifyContent": "space-between",
        "alignItems": "center",
        "width": 600,
        "height": 100
      },
      "children": [
        {
          "type": "text",
          "classList": [
            "btn"
          ],
          "events": {
            "click": "save"
          },
          "attr": {
            "value": "保存"
          }
        },
        {
          "type": "text",
          "classList": [
            "btn"
          ],
          "style": {
            "backgroundColor": "#1E9FFF"
          },
          "events": {
            "click": "push"
          },
          "attr": {
            "value": "新增"
          }
        }
      ]
    }
  ]
}

/***/ }),

/***/ 352:
/***/ (function(module, exports, __webpack_require__) {

module.exports = function(module, exports, __weex_require__){'use strict';

var _stringify = __webpack_require__(96);

var _stringify2 = _interopRequireDefault(_stringify);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var navigator = __weex_require__('@weex-module/navigator');
var globalEvent = __weex_require__('@weex-module/globalEvent');
var modal = __weex_require__('@weex-module/modal');
var self = null;
module.exports = {
    data: function () {return {
        items: [{
            x: {
                head: "X",
                value: "x+0",
                flag: false
            },
            y: {
                head: "Y",
                value: "y+0",
                flag: false
            }
        }],
        settting: function settting() {
            __weex_require__('@weex-module/configModule').getTemplate(function (res) {
                if (res == null) {
                    return;
                }
                var items = JSON.parse(res);
                self.items = items;
            });
        }
    }},
    methods: {
        input: function input(flag, head, value) {
            var item = self.items[flag];
            if (head == "X") {
                item.x.value = value;
            } else {
                item.y.value = value;
            }
        },
        push: function push(e) {
            if (self.items.length >= 5) {
                modal.toast({ 'message': "最多发5个表情", 'duration': 2 });
                return;
            }
            var temp = {
                x: {
                    head: "X",
                    value: "x+0",
                    flag: false
                },
                y: {
                    head: "Y",
                    value: "y+0",
                    flag: false
                }
            };
            self.items.push(temp);
        },
        remove: function remove(e) {
            var index = e.target.attr.index;
            var temp = [];
            for (var i = 0; i < this.items.length; i++) {
                if (i != index) {
                    temp.push(this.items[i]);
                }
            }
            this.items = temp;
        },
        isagree: function isagree(flag, head, value) {
            var item = self.items[flag];
            if (head == "X") {
                item.x.flag = value;
            } else {
                item.y.flag = value;
            }
        },
        save: function save() {
            var temp = [];
            for (var i = 0; i < this.items.length; i++) {
                var paresDto = {};
                if (this.items[i].x.flag == false || this.items[i].y.flag == false) {
                    modal.toast({ 'message': "请检查错误", 'duration': 2 });
                    return;
                }
                paresDto.xProperty = this.items[i].x.value;
                paresDto.yProperty = this.items[i].y.value;
                temp.push(paresDto);
            }
            __weex_require__('@weex-module/configModule').save((0, _stringify2.default)(this.items), temp, function (res) {
                if (res == true) {
                    modal.toast({ 'message': "保存成功", 'duration': 2 });
                    navigator.pop({ 'animated': 'true' }, function () {});
                } else {
                    modal.toast({ 'message': "保存错误,请检查内容", 'duration': 2 });
                }
            });
        },
        parseBundleUrl: function parseBundleUrl(name, url) {
            var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
            var r = url.substr(url.indexOf('?') + 1).match(reg);
            if (r != null) {
                return unescape(r[2]);
            }
            return null;
        }
    },

    created: function created() {
        self = this;

        globalEvent.addEventListener('androidback', function () {
            navigator.pop({ 'animated': 'true' }, function () {});
        });

        var bundleUrl = self.$getConfig().bundleUrl;
        var type = self.parseBundleUrl("type", bundleUrl);
        if (type != null) {
            var x = '+0';
            var y = '+0';
            if (type == 1) {
                y = '+5';
            } else if (type == 2) {
                x = '+5';
            } else if (type == 3) {
                x = '+5';
                y = '-5';
            } else if (type == 4) {
                x = '+5';
                y = '+5';
            }
            var temp = [];
            for (var i = 0; i < 5; i++) {
                var item = {
                    x: {
                        head: "X",
                        value: "x" + x,
                        flag: false
                    },
                    y: {
                        head: "Y",
                        value: "y" + y,
                        flag: false
                    }
                };
                temp.push(item);
            }
            self.items = temp;
        } else {
            self.settting();
        }
    }
};}
/* generated by weex-loader */


/***/ }),

/***/ 4:
/***/ (function(module, exports) {

var core = module.exports = { version: '2.4.0' };
if (typeof __e == 'number') __e = core; // eslint-disable-line no-undef

/***/ }),

/***/ 437:
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(199)
var __weex_template__ = __webpack_require__(300)
var __weex_style__ = __webpack_require__(249)
var __weex_script__ = __webpack_require__(352)

__weex_define__('@weex-component/01162173884fcc68b7f99c5afac680cf', [], function(__weex_require__, __weex_exports__, __weex_module__) {

    __weex_script__(__weex_module__, __weex_exports__, __weex_require__)
    if (__weex_exports__.__esModule && __weex_exports__.default) {
      __weex_module__.exports = __weex_exports__.default
    }

    __weex_module__.exports.template = __weex_template__

    __weex_module__.exports.style = __weex_style__

})

__weex_bootstrap__('@weex-component/01162173884fcc68b7f99c5afac680cf',undefined,undefined)

/***/ }),

/***/ 96:
/***/ (function(module, exports, __webpack_require__) {

module.exports = { "default": __webpack_require__(100), __esModule: true };

/***/ })

/******/ });