var Util;
(function (Util) {
    var MessageBox = (function () {
        function MessageBox() {
        }
        MessageBox.getContainer = function () {
            var container = $("#self_msgBox");
            if (0 == container.length) {
                $("body").append('<div id="self_msgBox"></div>');
            }
            return $("#self_msgBox");
        };
        MessageBox.tip = function (msg) {
            var container = MessageBox.getContainer();
            if (container.children().length > 0) {
                $("#self_tip").children().eq(0).text(msg);
            }
            else {
                container.append('<div id="self_tip" class="block modal2" align="center" >' + '<span style="display:block;line-height:170px;height:100%;font-size:20px;color:blue;">' + msg + '</span></div>');
            }
            $("#self_tip").modal('view', {
                speed: 500,
                easing: 'easeOutBack',
                animation: 'top',
                position: '10% auto',
                overlayClose: true,
                on: 'click'
            });
            $(window).resize(function () {
                $("#self_tip").trigger('close');
            });
        };
        MessageBox.selfOk = function () {
            MessageBox.promise.succeed();
        };
        MessageBox.selfCancel = function () {
            MessageBox.promise.failed();
        };
        MessageBox.confirm = function (msg) {
            var container = MessageBox.getContainer();
            if (container.children().length > 0) {
                $("#self_tip").children().eq(0).text(msg);
            }
            else {
                container.append('<div id="self_confirm" class="block modal2" align="center"><span style="font-size:20px;text-align:center">' + msg + '</span></div>');
                var confirm = container.children().eq(0);
                confirm.append('<div style="margin: 100px"> </div>' + '<div id="self_fill" style="margin-left:-12px; margin-right:-12px;background: #ccc;overflow: hidden;padding: 5px;margin-bottom:5px">' + '<input type="submit" class="button gray close medium saveQuestion right" value="    确 认    " onclick="Util.MessageBox.selfOk()"></input>' + '<input type="submit" class="button gray close medium saveQuestion right" value="    取 消    " onclick="Util.MessageBox.selfCancel()"></input> </div>');
            }
            confirm.modal('view', {
                speed: 500,
                easing: 'easeOutBack',
                animation: 'top',
                position: '10% auto',
                overlayClose: false,
                close: '.close'
            });
            $(window).resize(function () {
                confirm.trigger('close');
            });
            return MessageBox.promise = new Util.Promise();
        };
        MessageBox.isMSIE = navigator.appName == "Microsoft Internet Explorer";
        return MessageBox;
    })();
    Util.MessageBox = MessageBox;
})(Util || (Util = {}));
