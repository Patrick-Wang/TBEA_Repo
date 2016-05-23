Namespace.define("std", Class.define("vector", function() {
	this.mContainer = [];
}, {

	back : function() {
		if (this.mContainer.length > 0) {
			return this.mContainer[this.mContainer.length - 1];
		}
		return null;
	},
	
	pop : function() {
		if (this.mContainer.length > 0) {
			return this.erase(this.mContainer.length - 1);
		}
		return null;
	},
	
	push : function(e) {
		this.mContainer.push(e);
		return this;
	},

	erase : function(index, count) {
		if (index >= this.mContainer.length){
			return null;
		}
		
		if (count != undefined) {
			return this.mContainer.splice(index, count);
		}
		return this.mContainer.splice(index, 1)[0];
	},

	find : function(item) {
		for (var i = 0; i < this.mContainer.length; i++) {
			if (this.mContainer[i] == item){
				return i;
			}
		}
		return -1;
	},
	
	reverse : function () {
		var newContainer = new std.vector();
		for (var i = 0; i < this.mContainer.length; i++) {
			newContainer.insert(0, this.mContainer[i]);
		}
		this.mContainer = newContainer.toArray();
		return this;
	},
	
	insert : function(index, item) {
		this.mContainer.splice(index, 0, item);
		return this;
	},

	size : function() {
		return this.mContainer.length;
	},

	get : function(index) {
		return this.mContainer[index];
	},

	set : function(index, e) {
		this.mContainer[index] = e;
		return this;
	},

	sort : function(callback) {
		this.mContainer.sort(callback);
		return this;
	},

	contains : function(obj) {
		return this.find(obj) >= 0;
	},
	
	concat : function(arr) {
		if (arr instanceof Array) {
			this.mContainer = this.mContainer.concat(arr);
		}
		else if (arr instanceof std.vector){
			this.mContainer = this.mContainer.concat(arr.container);
		}
		return this;
	},
	
	toArray : function(){
		return this.mContainer;
	},
	
	isEmpty : function() {
		return this.mContainer.length == 0;
	}

}));


Namespace.define("std", Class.extend(std.vector, "queue", function(){
	
}, {
	enqueue : function(item){
		this.push(item);
		return this;
	},
	
	dequeue : function() {
		if (!this.isEmpty()) {
			return this.erase(0)
		}
		return null;
	},
	
	peek : function() {
		if (!this.isEmpty()) {
			return this.get(0)
		}
		return null;
	},
	
	push : function(e) {
		//通过 _super对象的_invoke方法可以调用基类的方法
		return this._super._invoke(this, "push", e);
	},
}, {}))