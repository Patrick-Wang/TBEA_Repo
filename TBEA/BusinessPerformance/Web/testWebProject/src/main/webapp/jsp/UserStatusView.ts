/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="dateSelector.ts" />
declare var echarts;
declare var $;
module userStatus {

    class JQGridAssistantFactory {

        public static createFlatTable(gridName: string, title: string[]): JQTable.JQGridAssistant {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                nodes.push(new JQTable.Node(title[i], "_" + i, true, JQTable.TextAlign.Left));
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }

    }


    export class View {
        public static instance: View = new View();
        public static getInstance(): View {
            return View.instance;
        }

        private mData: any;
        initInstance(data: any) {
            this.mData = data;
            this.updateTitle();
            this.updateUI();
        }

        public updateUI() {
             this.updateTitle();
             this.updateTable("table");
        }

        private updateTitle(){
             $('h1').text("用户状态 Dashboard");
             document.title = "用户状态 Dashboard"; 
        }

        private updateTable(tableId: string): void {
            var name = tableId + "_jqgrid";
            var tableAssist: JQTable.JQGridAssistant =
                JQGridAssistantFactory.createFlatTable(name, [
                "用户名", "sid", "登录时间", "最近访问时间"]);

            $('#text')[0].innerHTML = ("● 在线户数数 : " + this.mData.active_user_count + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp"+ 
                            "● 最近访问用户: " + this.mData.latest_active_user + "&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;"+
                            "● 最近访问时间: " + this.mData.last_accessed_time);
            
            var data = [];
            for (var i = 0; i < this.mData.users.length; ++i) {
                data.push([
                    this.mData.users[i].name,
                    this.mData.users[i].sid,
                    this.mData.users[i].login_time,
                    this.mData.users[i].last_accessed_time,
                ]);
            }
            

            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

            $("#" + name).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(data),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : true,
                  //  cellsubmit: 'clientArray',
                  //  cellEdit: true,
                    width:1000,
                    height: '100%',
                    shrinkToFit: true,
                    autoScroll: true,
                }));

        }
    }
}