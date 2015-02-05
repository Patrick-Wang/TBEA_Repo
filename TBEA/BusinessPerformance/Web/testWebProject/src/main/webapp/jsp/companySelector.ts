/// <reference path="util.ts" />
/// <reference path="unitedSelector.ts" />
declare var $;

module Util {
    String.prototype["getWidth"] = function(fontSize)
    {
        var span = document.getElementById("__getwidth");
        if (span == null) {
            span = document.createElement("span");
            span.id = "__getwidth";
            document.body.appendChild(span);
            span.style.visibility = "hidden";
            span.style.whiteSpace = "nowrap";
        }
        span.innerText = this;
        span.style.fontSize = fontSize + "px";
    
        return span.offsetWidth;
    }

    export class ICompanySelectorOption{ 
        noneSelectedText : string = '项目公司';
        selectedText: string = '# 个项目公司被选中';       
    }
    
    export class CompanySelector {
        private mUnitedSelector : UnitedSelector;
        private mOpt : ICompanySelectorOption = {
            noneSelectedText : '项目公司',
            selectedText: '# 个项目公司被选中'    
        };

        private mSelectedTop: string;
        private mMulti: boolean;
        
//        private updateTopSel(topSel : any, itemCount : number, multi : boolean) {
//            topSel = $(topSel);
//            topSel.css("width", topSel.children('option:selected').text().getWidth(13) + 25);
//            topSel.multiselect({
//                multiple: multi,
//                header: false,
//                minWidth: 80,
//                height　: itemCount * 27 > 600 ? 600 : itemCount * 27 + 3,
//                // noneSelectedText: "请选择月份",
//                selectedList: 1
//            });
//        }
        
        private updateSelect(sel : any, itemCount : number, multi : boolean) {
            sel = $(sel);
            var width = sel.children('option:selected').text().getWidth(13) + 25;
            var minWidth = 80;
            if (multi) {
                sel.multiselect({
                    multiple: multi,
                    header: true,
                    minWidth: 80,
                    noneSelectedText : this.mOpt.noneSelectedText,
                    selectedText: this.mOpt.selectedText,
                    height　: itemCount * 27 > 600 ? 600 : itemCount * 27 + 3,
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                });
                 var text : any = "n个 项目公司被选中";
                minWidth = text.getWidth(13) + 50;
                if (sel.multiselect("getChecked").length > 1) {
                    width = text.getWidth(13) + 25;
                }
            }
            sel.css("width", width);
            sel.multiselect({
                multiple: multi,
                header: multi,
                minWidth: minWidth,
                height　: itemCount * 27 > 600 ? 600 : itemCount * 27 + 3,
                // noneSelectedText: "请选择月份",
                selectedList: 1
            });

        }
        
        
        private useMultiSelect(){
            var sels = this.mUnitedSelector.getSelect();
            for (var i = 0; i < sels.length - 1; ++i){
                this.updateSelect(sels[i], $(sels[i]).children().length, false);
            }
         
            this.updateSelect(sels[sels.length - 1], $(sels[sels.length - 1]).children().length, this.mMulti);

        }
        
        public constructor(
            multi : boolean, 
            divId: string, 
            comps: IDataNode[], 
            firstComp?: number, 
            opt ? : ICompanySelectorOption) {
            this.mMulti = multi;
           
            if (isExist(opt)){
                this.mOpt = opt;    
            }
            
            var virtualRoot = new DataNode(null);
            var firstCompNode : DataNode;           
            virtualRoot.appendAll(DataNode.valueOfAll(comps));
            virtualRoot.accept({visit: (node: DataNode) =>{
                if (node != virtualRoot && node.getData().id + "" == firstComp + ""){
                    firstCompNode = node;
                    return true;
                }
                return false;
            }});
            
            if (isExist(firstCompNode)){
                var path = new std.vector<number>();
                var parent = firstCompNode.getParent();
                while (null != parent){
                    path.insert(0, parent.find(firstCompNode));  
                    firstCompNode = parent;  
                    parent = firstCompNode.getParent();
                }
                this.mUnitedSelector = new UnitedSelector(comps, divId, path.toArray());
            }
            else{
                this.mUnitedSelector = new UnitedSelector(comps, divId);
            }
            
            this.useMultiSelect();
            
            this.mUnitedSelector.change((sel : any)=>{
                this.useMultiSelect();
            });
        }

        public getCompany(): Util.CompanyType {
            var selNodes = this.mUnitedSelector.getNodes();
            return selNodes[selNodes.length - 1].getData().id;
        }
        
        public getCompanys(): Util.CompanyType[] {
            var ret = [];
            var checkedOpt = $(this.mUnitedSelector.getSelect()[1]).multiselect("getChecked");
            checkedOpt.each((i) =>{
                ret.push(checkedOpt[i].value);
            })
            return ret;
        }

    }
}