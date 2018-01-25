///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
///<reference path="../singleDateReport/entry.ts"/>
module framework.templates.whszfb {
    import router = framework.router;
    import Node = JQTable.Node;
    import BasicEndpoint = framework.basic.BasicEndpoint;
    import FrameEvent = framework.basic.FrameEvent;
    import IFormula = JQTable.IFormula;
    import Cell = JQTable.Cell;
    import Formula = JQTable.Formula;

    export function createInstance() : EntryView{
        return new EntryView();
    }

    interface EntryOption extends framework.templates.singleDateReport.EntryOption{
        itemNodes:Util.IDataNode[];
        itemId:string;
    }

    export class EntryView extends framework.templates.singleDateReport.EntryView{

        unitedSelector : Util.UnitedSelector;

        onInitialize(opt:EntryOption):void{
            this.unitedSelector = new Util.UnitedSelector(opt.itemNodes,opt.itemId);
            if (opt.itemNodes.length == 1){
                this.unitedSelector.hide();
                if($("#" + opt.dtId).parent().hasClass("hidden")){
                    $("#grid-update").hide();
                    $("#" + opt.dtId).hide();
                }
            }
            super.onInitialize(opt);
        }

        getParams(date:Util.Date):any{
            return {
                date: this.getDate(date),
                item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
            };
        }

        createJqassist():JQTable.JQGridAssistant{
            super.createJqassist();
            class MyFormula implements IFormula{
                grid:any;
                update(): boolean {
                    let ids = this.grid.jqGrid('getDataIDs');
                    if (ids.length > 0){
                        let formulas : IFormula[] = [];
                        for (let i = 0; i < ids.length; ++i){
                            formulas = formulas.concat(this.collectFormula(ids[i]));
                        }
                        for (let i = 0; i < formulas.length; ++i){
                            formulas[i].setGrid(this.grid);
                            formulas[i].update();
                        }
                    }
                    return false;
                }

                setGrid(grid: any): void {
                    this.grid = grid;
                }

                private getVal(cell : Cell):number{
                    if(cell.getVal() != undefined && cell.getVal() != "") {
                        return parseFloat(cell.getVal());
                    }
                    return 0;
                }

                private sumFormula():any{
                    return (dest:Cell, srcCells:Cell[])=>{
                        return (this.getVal(srcCells[0]) + this.getVal(srcCells[1]) - this.getVal(srcCells[2])).toFixed(4);
                    };
                }

                private collectFormula(rid: string): IFormula[] {
                    return [
                        new Formula(
                            Cell.create(rid, 8),
                            [Cell.create(rid, 5), Cell.create(rid, 6), Cell.create(rid, 7)],
                            this.sumFormula())
                    ];
                }
            }

            if (this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id <= 1){
                this.mTableAssist.addFormula(new MyFormula());
            }
            return this.mTableAssist;
        }

        submit(date:Util.Date): void{
            this.mAjaxSubmit.post(
                $.extend(this.getParams(this.getUDate()), {
                    data : JSON.stringify(this.onLoadSubmitData())
                }))
                .then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        this.update(this.getUDate());
                        Util.Toast.success("提交 成功");
                    } else {
                        Util.Toast.failed(resp.message);
                    }
                }, (text)=>{
                    Util.Toast.failed('提交 失败');
                });
        }

        updateTable():void {
            this.createJqassist();

            let opt = {
                datatype: "local",
                dataWithId : this.resp.data,
                multiselect: false,
                drag: false,
                resize: false,
                assistEditable:true,
                //autowidth : false,
                cellsubmit: 'clientArray',
                //editurl: 'clientArray',
                cellEdit: true,
                // height: data.length > 25 ? 550 : '100%',
                // width: titles.length * 200,
                rowNum: 15,
                height: '100%',
                width: $("#" + this.opt.host).width(),
                shrinkToFit: this.resp.shrinkToFit == "false" ? false : true,
                autoScroll: true,
                pager: '#' + this.jqgridName() + "pager",
                viewrecords: true
            };
            if (this.resp.pager == 'none'){
                opt.pager = undefined;
                opt.rowNum = 2000;
            }
            this.mTableAssist.create(opt);
            this.adjustSize();
        }
    }
}