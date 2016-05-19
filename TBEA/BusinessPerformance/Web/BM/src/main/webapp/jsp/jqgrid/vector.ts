module std{
   export class vector<T>{
        private mContainer: T[] = [];

        public back(): T {
            if (this.mContainer.length > 0) {
                return this.mContainer[this.mContainer.length - 1];
            }
            return null;
        }

        public pop(): T {
            if (this.mContainer.length > 0) {
                return this.erase(this.mContainer.length - 1);
            }
            return null;
        }

        public push(e: T): vector<T> {
            this.mContainer.push(e);
            return this;
        }

        public erase(index: number): T {
            if (index >= this.mContainer.length) {
                return null;
            }
            return this.mContainer.splice(index, 1)[0];
        }

        public find(item: T) {
            for (var i = 0; i < this.mContainer.length; i++) {
                if (this.mContainer[i] == item) {
                    return i;
                }
            }
            return -1;
        }

        public reverse() {
            var newContainer = new vector<T>();
            for (var i = 0; i < this.mContainer.length; i++) {
                newContainer.insert(0, this.mContainer[i]);
            }
            this.mContainer = <T[]>newContainer.toArray();
            return this;
        }

        public insert(index: number, item: T) {
            this.mContainer.splice(index, 0, item);
            return this;
        }

        public size() {
            return this.mContainer.length;
        }

        public get(index: number) {
            return this.mContainer[index];
        }

        public set(index: number, e: T) {
            this.mContainer[index] = e;
            return this;
        }

        public sort(callback: any) {
            this.mContainer.sort(callback);
            return this;
        }

        public contains(obj: T) {
            return this.find(obj) >= 0;
        }

        public concat(arr: any) {
            if (arr instanceof Array) {
                this.mContainer = this.mContainer.concat(arr);
            }
            else if (arr instanceof vector) {
                this.mContainer = this.mContainer.concat(arr.container);
            }
            return this;
        }

        toArray(): T[] {
            return this.mContainer;
        }

        isEmpty() {
            return this.mContainer.length == 0;
        }
    }
}