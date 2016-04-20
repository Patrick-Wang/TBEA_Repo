/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../../js/jquery/jquery.d.ts" />
module framework.route {

    export interface Event {
        from:number;
        to:Endpoint;
        id:number;
        data:any;
    }

    export interface Endpoint {
        getId():number;
        onEvent(e:Event):any;
    }

    export class Router {
        static OK:number = 887291;
        static FAILED:number = 887292;

        private mEndpoints:any = {};
        private mCurEvent:Event;

        register(endpoint:Endpoint) {
            this.mEndpoints[endpoint.getId()] = endpoint;
        }

        unregister(endpoint:Endpoint) {
            this.mEndpoints[endpoint.getId()] = undefined;
        }

        private sendInternal(e:Event):any {
            let targetId = e.to.getId();
            if (this.mEndpoints[targetId] != undefined) {
                return e.to.onEvent(e);
            }
            return Router.FAILED;
        }

        public from(src:Endpoint):Router {
            this.mCurEvent = <Event>{};
            this.mCurEvent.from = src.getId();
            return this;
        }

        public to(target:number):Router {
            if (this.mCurEvent == undefined) {
                this.mCurEvent = <Event>{};
            }
            this.mCurEvent.to = this.getEndpoint(target);
            return this;
        }

        public broadcast(id:number, data?:any):any {
            if (this.mCurEvent != undefined) {
                for (let i in this.mEndpoints) {
                    let event = {
                        from: this.mCurEvent.from,
                        to: this.mEndpoints[i],
                        id: id,
                        data: data
                    }
                    event.to.onEvent(event);
                }
                this.mCurEvent = undefined;
                return Router.OK;
            }
            return Router.FAILED;
        }

        public send(id:number, data?:any):any {
            if (this.mCurEvent != undefined) {
                this.mCurEvent.id = id;
                this.mCurEvent.data = data;
                let event = this.mCurEvent;
                this.mCurEvent = undefined;
                return this.sendInternal(event);
            }
            return Router.FAILED;
        }

        private getEndpoint(id:number):Endpoint {
            return this.mEndpoints[id];
        }
    }

    export let router:Router = new Router();
}
