module framework.route {

    export interface Event {
        id:number;
        from:number;
        to:number;
        road?:number[];
        data:any;
        isBroadcast?:boolean;
    }

    export let nextId : ()=>number = (function(idBase:number){
        return function(){
            return ++idBase;
        };
    })(9988392);


    export interface Endpoint {
        getId():number;
        onEvent(e:Event):any;
    }

    export class Router {
        static OK:string = "framework.route.OK";
        static FAILED:string = "framework.route.FAILED";

        private mEndpoints:any = {};
        private mEplist:number[] = [];
        private mCurEvent:Event;

        register(endpoint:Endpoint)  {
            let id = endpoint.getId();
            this.mEndpoints[id] = endpoint;
            this.mEplist.push(id);
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
            for (let i in this.mEplist) {
                let event = {
                    from: this.mCurEvent == undefined ? undefined : this.mCurEvent.from,
                    to: undefined,
                    id: id,
                    data: data,
                    isBroadcast:true
                }
                this.mEndpoints[this.mEplist[i]].onEvent(event);
            }
            this.mCurEvent = undefined;
            return Router.OK;
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
