/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../../js/jquery/jquery.d.ts" />
module framework.route {

    export interface Event {
        id:number;
        from:number;
        to:number;
        road?:number[];
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

        register(endpoint:Endpoint)  {
            this.mEndpoints[endpoint.getId()] = endpoint;
        }

        unregister(endpoint:Endpoint) {
            this.mEndpoints[endpoint.getId()] = undefined;
        }

        private sendInternal(e:Event):any {
            let toEndpoint = this.mEndpoints[e.to];
            if (toEndpoint != undefined) {
                return toEndpoint.onEvent(e);
            }
            return Router.FAILED;
        }

        public fromEp(from:Endpoint):Router {
            return this.from(from.getId());
        }

        public toEp(to:Endpoint):Router{
            return this.to(to.getId());
        }

        public from(from:number):Router {
            this.mCurEvent = <Event>{};
            this.mCurEvent.from = from;
            return this;
        }

        public to(target:number):Router {
            if (this.mCurEvent == undefined) {
                this.mCurEvent = <Event>{};
            }
            this.mCurEvent.to = target;
            return this;
        }

        public broadcast(id:number, data?:any):any {
            if (this.mCurEvent != undefined) {
                for (let i in this.mEndpoints) {
                    let event = {
                        from: this.mCurEvent.from,
                        to: undefined,
                        id: id,
                        data: data,
                    }
                    this.mEndpoints[i].onEvent(event);
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

        public redirect(to:number, event:Event):any {
            if (to != undefined) {
                if (event.road == undefined){
                    event.road = [];
                }
                event.road.push(event.to);
                event.to = to;
                return this.sendInternal(event);
            }
            return Router.FAILED;
        }

        private getEndpoint(id:number):Endpoint {
            return this.mEndpoints[id];
        }
    }
}
module framework{
    export let router:route.Router = new route.Router();
}
