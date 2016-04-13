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
        private mDivId:string;
        public hide(){
           this.mUnitedSelector.hide();
        }
        
        public show(){
           this.mUnitedSelector.show();
        }
        
        private getMaxWidth(opts : any) : number{
            var max = 0;
            var tmp = 0;
            var fontSize = Util.isMSIE() ? 14 : 13;
            for (var i = 0; i < opts.length; ++i){
                tmp = $(opts[i]).text().getWidth(fontSize) + 25;
                if (max < tmp){
                    max = tmp;    
                }
            }
            return max;
        }
        
        private updateSelect(sel : any, itemCount : number, multi : boolean) {
            sel = $(sel);
            var width = this.getMaxWidth(sel.children());
            var minWidth = 80;
            var itemHeight = Util.isMSIE() ? 27.5 : 27;
            sel.css("width", width);
            if (multi) {
                var text : any = "n个 项目公司被选中";
                minWidth = text.getWidth(13) + 60;
                sel.multiselect({
                    multiple: multi,
                    header: true,
                    minWidth: minWidth,
                    minHeight: 20,
                    noneSelectedText : this.mOpt.noneSelectedText,
                    selectedText: this.mOpt.selectedText,
                    height　: '100%',//itemCount * itemHeight + 3,
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                });
                
//                if (sel.multiselect("getChecked").length > 1) {
//                    width = text.getWidth(13) + 25;
//                }
            } else{
                sel.multiselect({
                    multiple: multi,
                    header: multi,
                    minWidth: minWidth,
                    height　: '100%',///*itemCount * 27 > 600 ? 600 :*/ itemCount * itemHeight + 3,
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                }); 
            }
            
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
           this.mDivId = divId;
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
      
            this.mUnitedSelector.change((sel : any, depth: number)=>{
                var compDepth = this.mUnitedSelector.getPath().length;
                if (this.mMulti && depth == compDepth){
                    
                }else {
                    this.useMultiSelect();
                }
            });
        }

        public getCompany(): Util.CompanyType {
            var selNodes = this.mUnitedSelector.getNodes();
            if (selNodes.length > 0) {
                return selNodes[selNodes.length - 1].getData().id;
            }
            return null;
        }
        
        public getCompanyName(): string {
            var selNodes = this.mUnitedSelector.getNodes();
            if (selNodes.length > 0) {
                return selNodes[selNodes.length - 1].getData().value;
            }
            return null;
        }
        
        public checkAll(){
            $(this.mUnitedSelector.getSelect()[1]).multiselect("checkAll");
        }

        public getRawCompanyData(): Util.IData[] {
            var ret : Util.IData[] = [];
            var path = this.mUnitedSelector.getPath();
            var parent: DataNode = this.mUnitedSelector.getDataNode(path, path.length - 1);
            var node: DataNode;
            var sels = this.mUnitedSelector.getSelect();
            var checkedOpt = $(sels[sels.length - 1]).multiselect("getChecked");
            
            for (var i = 0; i < parent.childCount(); ++i){
                node = parent.childAt(i);
                checkedOpt.each((j) =>{
                    if (node.getData().id == checkedOpt[j].value){
                        ret.push(node.getData());
                    }
                });
            }
            return ret;
        }
        
         public getCompanyNames(): string[] {
            var ret : string[] = [];
            var path = this.mUnitedSelector.getPath();
            var parent: DataNode = this.mUnitedSelector.getDataNode(path, path.length - 1);
            var node: DataNode;
            var sels = this.mUnitedSelector.getSelect();
            var checkedOpt = $(sels[sels.length - 1]).multiselect("getChecked");
            
            for (var i = 0; i < parent.childCount(); ++i){
                node = parent.childAt(i);
                checkedOpt.each((j) =>{
                    if (node.getData().id == checkedOpt[j].value){
                        ret.push(node.getData().value);
                    }
                });
            }
            return ret;
        }

        public getCompanys(): Util.CompanyType[] {
            var ret = [];
            var sels = this.mUnitedSelector.getSelect();
            var checkedOpt = $(sels[sels.length - 1]).multiselect("getChecked");
            checkedOpt.each((i) =>{
                ret.push(parseInt(checkedOpt[i].value));
            });
            return ret;
        }

    }
}