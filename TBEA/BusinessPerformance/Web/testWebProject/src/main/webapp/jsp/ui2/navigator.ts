module navi{

    import ITreeNode = tree.ITreeNode;
    Array.prototype.pushAll = function(items){
        for (var i = 0; i < items.length; ++i){
            this.push(items[i]);
        }
        return this;
    }

    export class Builder{
        builders = {};
        register(group, builder:()=>ITreeNode[]){
            if (this.builders[group] == undefined){
                this.builders[group] = [];
            }
            this.builders[group].push(builder);
        }

        build(group) {
            let ret = [];
            if (this.builders[group] != undefined) {
                for (let i = 0; i < this.builders[group].length; ++i) {
                    let nodes = this.builders[group][i]();
                    if (nodes instanceof Array) {
                        ret.pushAll(nodes);
                    } else {
                        ret.push(nodes);
                    }
                }
            }
            return ret;
        }
    }



    export let uid = ((seed) => {
        return () => {
            ++seed;
            return seed + "";
        }
    })(new Date().getTime());
}

let builder:navi.Builder = new navi.Builder();