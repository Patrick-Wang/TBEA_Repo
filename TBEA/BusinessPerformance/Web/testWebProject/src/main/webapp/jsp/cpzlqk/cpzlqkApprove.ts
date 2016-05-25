///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicApprove.ts"/>
///<reference path="cpzlqkdef.ts"/>

module cpzlqk{

    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;


    class CpzlqkApproveFrameView extends framework.basic.ApproveFrameView {

        mBhglxSelector : Util.UnitedSelector;

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case Event.ZLFE_GET_BHGLX:
                    let node:Util.DataNode = this.mBhglxSelector.getDataNode(this.mBhglxSelector.getPath());
                    return node.getData().id;
            }
            return super.onEvent(e);
        }

        protected updateTypeSelector(width:number = 285) :boolean{
            let ret = super.updateTypeSelector(width);
            if (ret){
                this.mItemSelector.change(()=>{
                    let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
                    if (!router.to(this.plugin(node)).send(Event.ZLFE_IS_BHGLX_SUPPORTED)){
                        this.mBhglxSelector.hide();
                    }else {
                        this.mBhglxSelector.show();
                    }
                });
                let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
                if (!router.to(this.plugin(node)).send(Event.ZLFE_IS_BHGLX_SUPPORTED)){
                    this.mBhglxSelector.hide();
                }else {
                    this.mBhglxSelector.show();
                }
            }
            return ret;
        }


        protected init(opt:FrameOption):void {
            this.mBhglxSelector = new Util.UnitedSelector([{
                data:{id : ByqBhgType.YBYSQFJYS, value : "110kV及以上产品"}
            }, {
                data:{id : ByqBhgType.PBCP, value : "配变产品"}
            }], opt.bhglx);

            $("#" + opt.bhglx + " select")
                .multiselect({
                    multiple: false,
                    header: false,
                    minWidth: 140,
                    height: '100%',
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                })
                .css("padding", "2px 0 2px 4px")
                .css("text-align", "left")
                .css("font-size", "12px");
            super.init(opt);
        }
    }




    export abstract class ZlApprovePluginView extends framework.basic.ApprovePluginView {
        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case Event.ZLFE_IS_BHGLX_SUPPORTED:
                    return this.isSupportBhglb();
            }
            return super.onEvent(e);
        }

        isSupportBhglb():boolean{
            return false;
        }

        getBhglx():ByqBhgType{
            return router.from(this.getId()).to(framework.basic.endpoint.FRAME_ID).send(Event.ZLFE_GET_BHGLX);
        }
    }

    let ins = new CpzlqkApproveFrameView();
}