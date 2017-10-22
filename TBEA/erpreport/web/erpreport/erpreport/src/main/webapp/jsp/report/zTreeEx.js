/**
 * Created by Floyd on 2016/7/27.
 */
var zTreeEx;
(function (zTreeEx) {
    var RightMenu = (function () {
        function RightMenu(setting) {
            this.setting = setting;
        }
        RightMenu.prototype.getRightClick = function () {
            if (this.setting.callback != undefined) {
                return this.setting.callback.onRightClick;
            }
            return undefined;
        };
        RightMenu.prototype.setRightClick = function (rightClick) {
            if (this.setting.callback == undefined) {
                this.setting.callback = {};
            }
            this.setting.callback.onRightClick = rightClick;
        };
        RightMenu.prototype.getSetting = function () {
            var _this = this;
            var rightClick = this.getRightClick();
            this.setRightClick(function (event, treeId, treeNode) {
                var curNode = treeNode;
                if (curNode.rm != undefined) {
                    _this.showRM(curNode, event.clientX, event.clientY);
                }
                else {
                    _this.hideRM();
                }
                if (rightClick != undefined) {
                    rightClick();
                }
            });
            return this.setting;
        };
        RightMenu.prototype.showRM = function (curNode, x, y) {
            var rm = $('#ztreeEx_rMenu_div ul');
            if (rm.length == 0) {
                $(document.body).append('<div id="ztreeEx_rMenu_div" style="border:1px solid darkgray;display:none;position:absolute"><ul></ul></div>');
                rm = $('#ztreeEx_rMenu_div ul');
            }
            rm.empty();
            for (var i = 0; i < curNode.rm.length; ++i) {
                rm.append('<li style="width:80px;background-color: white;cursor:default;text-align: center;" id="' + i + '">' + curNode.rm[i] + '</li>');
                if (curNode.rmClick != undefined) {
                    $('#ztreeEx_rMenu_div #' + i).bind("click", function (e) {
                        $('#ztreeEx_rMenu_div').hide();
                        curNode.rmClick(curNode, e.target.id);
                    }).mouseover(function (e) {
                        $(e.target).css("background-color", "lightBlue");
                    }).mouseout(function (e) {
                        $(e.target).css("background-color", "white");
                    });
                }
            }
            rm = $('#ztreeEx_rMenu_div');
            rm.show().css({
                top: y + "px",
                left: (x + 2) + "px"
            });
            $('html').bind('click', function (e) {
                $('#ztreeEx_rMenu_div').hide();
            });
        };
        RightMenu.prototype.hideRM = function () {
            $('#ztreeEx_rMenu_div').hide();
        };
        return RightMenu;
    }());
    function init(elem, setting, nodes) {
        var rm = new RightMenu(setting);
        return $.fn.zTree.init(elem, rm.getSetting(), nodes);
    }
    zTreeEx.init = init;
})(zTreeEx || (zTreeEx = {}));
