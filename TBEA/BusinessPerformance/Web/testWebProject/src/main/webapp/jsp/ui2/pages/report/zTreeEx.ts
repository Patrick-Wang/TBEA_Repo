/**
 * Created by Floyd on 2016/7/27.
 */

module zTreeEx{

    interface TreeNodeEx{
        rm : string[];
        rmClick : (node:any, rmi:number) => void;
    }

    class RightMenu{
        setting:any;
        getRightClick(){
            if (this.setting.callback != undefined){
                return this.setting.callback.onRightClick;
            }
            return undefined;
        }

        setRightClick(rightClick){
            if (this.setting.callback == undefined){
                this.setting.callback = {};
            }
            this.setting.callback.onRightClick = rightClick;
        }

        constructor(setting){
            this.setting = setting;
        }

        getSetting(){
            let rightClick = this.getRightClick();
            this.setRightClick((event,treeId,treeNode) => {
                let curNode:TreeNodeEx = treeNode;
                if (curNode.rm != undefined){
                    this.showRM(curNode, event.clientX, event.clientY);
                }else{
                    this.hideRM();
                }
                if (rightClick != undefined){
                    rightClick();
                }
            });
            return this.setting;
        }


        private showRM(curNode:TreeNodeEx, x:number, y:number):void {

            let rm = $('#ztreeEx_rMenu_div ul');
            if (rm.length == 0){
                $(document.body).append('<div id="ztreeEx_rMenu_div" style="border:1px solid darkgray;display:none;position:absolute"><ul></ul></div>');

                rm = $('#ztreeEx_rMenu_div ul');
            }
            rm.empty();
            for (var i = 0; i < curNode.rm.length; ++i){
                rm.append('<li style="width:80px;background-color: white;cursor:default;text-align: center;" id="' + i + '">' + curNode.rm[i] + '</li>');
                if (curNode.rmClick != undefined){
                    $('#ztreeEx_rMenu_div #' + i).bind("click",function(e){
                        $('#ztreeEx_rMenu_div').hide();
                        curNode.rmClick(curNode, e.target.id);
                    }).mouseover((e) => {
                        $(e.target).css("background-color","lightBlue");
                    }).mouseout((e) => {
                        $(e.target).css("background-color","white");
                    });
                }
            }
            rm = $('#ztreeEx_rMenu_div');
            rm.show().css({//设置右键菜单的位置
                top: y + "px",
                left: (x + 2) + "px"
            });

            $('html').bind('click', (e)=> { //用于点击其他地方保存正在编辑状态下的行
                $('#ztreeEx_rMenu_div').hide();
            });

        }

        private hideRM():void {
            $('#ztreeEx_rMenu_div').hide();
        }
    }



    export function init(elem, setting, nodes){
        let rm : RightMenu = new RightMenu(setting);
        return $.fn.zTree.init(elem, rm.getSetting(), nodes);
    }
}
