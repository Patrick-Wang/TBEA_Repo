/// <reference path="util.ts" />
declare var $;

module Util {
    export class MessageBox {

        private static getContainer() {
            var container: any = $("#self_msgBox");
            if (0 == container.length) {
                $("body").append('<div id="self_msgBox"></div>');

            }
            return $("#self_msgBox");
        }

        private static isMSIE: boolean = navigator.appName == "Microsoft Internet Explorer";

        static tip(msg: string): void {
            var container: any = MessageBox.getContainer();
            if (container.children().length > 0) {
                $("#self_tip").children().eq(0).text(msg);
            } else {
                container.append('<div id="self_tip" class="block modal2" align="center" >' +
                    '<span style="display:block;line-height:170px;height:100%;font-size:20px;color:blue;">' + msg + '</span></div>');
            }
            $("#self_tip").modal('view', {
                speed: 500,
                easing: 'easeOutBack',
                animation: 'top',
                position: '10% auto',
                //overlayColor : '#ccc',
                overlayClose: true,
                //overlayOpacity : .9,
                on: 'click'
            });

            //if (MessageBox.isMSIE) {
                $(window).resize(() => {
                    $("#self_tip").trigger('close');
                });
           // }
        }

        static selfOk() {
            MessageBox.promise.succeed();
        }

        static selfCancel() {
            MessageBox.promise.failed();
        }

        static promise: Util.Promise;

        static confirm(msg: string): Util.Promise {
            var container: any = MessageBox.getContainer();
            if (container.children().length > 0) {
                $("#self_tip").children().eq(0).text(msg);
            } else {
                container.append('<div id="self_confirm" class="block modal2" align="center"><span style="font-size:20px;text-align:center">' + msg + '</span></div>');
                var confirm: any = container.children().eq(0);
                confirm.append('<div style="margin: 100px"> </div>' +
                    '<div id="self_fill" style="margin-left:-12px; margin-right:-12px;background: #ccc;overflow: hidden;padding: 5px;margin-bottom:5px">' +
                    '<input type="submit" class="button gray close medium saveQuestion right" value="    确 认    " onclick="Util.MessageBox.selfOk()"></input>' +
                    '<input type="submit" class="button gray close medium saveQuestion right" value="    取 消    " onclick="Util.MessageBox.selfCancel()"></input> </div>'
                    );
            }

            confirm.modal('view', {
                speed: 500,
                easing: 'easeOutBack',
                animation: 'top',
                position: '10% auto',
                overlayClose: false,
                //overlayColor : '#ccc',
                // overlayOpacity : .9,
                close: '.close'
            });

            $(window).resize(() => {
                confirm.trigger('close');
            });
            
            return MessageBox.promise = new Util.Promise();
        }
    }
}