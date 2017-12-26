///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
///<reference path="../singleDateReport/entry.ts"/>
module framework.templates.dateReport {
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

                private sumCells(srcCells:Cell[]){
                    let tmpResult = 0;
                    let result = undefined;
                    for (let j = 0; j < srcCells.length; ++j){
                        if(srcCells[j].getVal() != undefined && srcCells[j].getVal() != ""){
                            tmpResult += parseFloat(srcCells[j].getVal());
                            result = tmpResult;
                        }
                    }
                    return result;
                }

                private sumFormula():any{
                    return (dest:Cell, srcCells:Cell[])=>{
                        let ret = this.sumCells(srcCells);
                        return ret;
                    };
                }

                private collectFormula(rid: string): IFormula[] {
                    return [
                        new Formula(
                            Cell.create(rid, 4),
                            [Cell.create(rid, 3), Cell.create(rid, 2)],
                            this.sumFormula()),
                        new Formula(
                            Cell.create(rid, 7),
                            [Cell.create(rid, 5),Cell.create(rid, 6)],
                            this.sumFormula()),
                        new Formula(
                            Cell.create(rid, 8),
                            [Cell.create(rid, 7),Cell.create(rid, 4)],
                            this.sumFormula()),
                        new Formula(
                            Cell.create(rid, 12),
                            [Cell.create(rid, 11),Cell.create(rid, 10),Cell.create(rid, 9)],
                            this.sumFormula()),
                        new Formula(
                            Cell.create(rid, 17),
                            [Cell.create(rid, 16),Cell.create(rid, 15),Cell.create(rid, 14),Cell.create(rid, 13)],
                            this.sumFormula()),
                        new Formula(
                            Cell.create(rid, 18),
                            [Cell.create(rid, 12),Cell.create(rid, 8),Cell.create(rid, 17)],
                            (dest:Cell, srcCells:Cell[])=>{
                                let ret = this.sumCells(srcCells.slice(0, 2));
                                let val = srcCells[2].getVal();
                                if (val != undefined && val != ""){
                                    val = parseFloat(val);
                                    if (undefined != ret){
                                        return ret - val;
                                    }else{
                                        return -val;
                                    }
                                }else{
                                    if (undefined != ret){
                                        return ret;
                                    }
                                }
                                return undefined;
                            })
                    ];
                }
            }

            this.mTableAssist.addFormula(new MyFormula());
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
    }
}