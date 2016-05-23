(function() {
	if (!Function.prototype.bind) {
		Function.prototype.bind = function(me) {
			var other = this;
			return function() {
				return other.apply(me, Array.prototype.slice
						.call(arguments));
			};
		};
	}

	function _createObject(parent, name) {
		if (parent[name] == undefined) {
			parent[name] = {}
		}
		return parent[name];
	}

	function _cloneMember(dest, src) {
		if (src == null) {
			return;
		}
		for ( var m in src) {
			dest[m] = src[m];
		}
	}

	function _cloneMemberMethod(dest, src) {
		if (src == null) {
			return;
		}
		for ( var m in src) {
			if (typeof src[m] == "function") {
				dest[m] = src[m];
			}
		}
	}

//	function _cloneMemberVar(dest, src) {
//		if (src == null) {
//			return;
//		}
//		for ( var m in src) {
//			if (typeof src[m] != "function") {
//				dest[m] = src[m];
//			}
//		}
//	}

	function _constructorBroker() {
	}

	Namespace = {
		define : function(namespace, _class) {
			if (namespace != "") {
				var className = _class._className();
				if (undefined == window[className]) {
					throw "could not find class " + className;
				}
				window[className] = undefined;
				_class.prototype._className = _class._className = function() {
					return namespace + '.' + className;
				}

				var nsObject = window;
				var nsArray = namespace.split('.');
				for (var i = 0; i < nsArray.length; ++i) {
					nsObject = _createObject(nsObject, nsArray[i]);
				}
				nsObject[className] = _class;
			}
		}
	};

	Class = {
		define : function(className, constructor, members, staticMembers) {
			function _class(broker) {
				if (!(broker instanceof _constructorBroker)) {
//					_cloneMemberVar(this, members);
					var argues = Array.prototype.slice.call(arguments);
					constructor.apply(this, argues);
				}
			}
			window[className] = _class
			_class.prototype = {};
			_cloneMemberMethod(_class.prototype, members);
			_cloneMember(_class, staticMembers);
			_class.prototype._className = _class._className = function() {
				return className;
			}
			_class.prototype._class = function() {
				return _class;
			}
			return _class;
		},

		extend : function(_super, className, constructor, members,
				staticMembers) {
			function _extend(broker) {
				var superFuncObject = {_super : _extend.prototype._super};
//				for ( var mf in _super.prototype) {
//					if (typeof _super.prototype[mf] == "function") {
//						superFuncObject[mf] = _super.prototype[mf].bind(this);
//					}
//				}
				superFuncObject._invoke = function(me, funName){
					var argues = Array.prototype.slice.call(arguments);
					argues.splice(0, 2);
					return _super.prototype[funName].apply(me, argues);
				}

				if (!(broker instanceof _constructorBroker)) {
					var argues = Array.prototype.slice.call(arguments);
					_super.apply(this, argues);
					this._super = superFuncObject;
//					_cloneMemberVar(this, members);
					constructor.apply(this, argues);
				}
				
				if (undefined == this._super) {
					this._super = superFuncObject;
				}
			}
			window[className] = _extend;
			_extend.prototype = new _super(new _constructorBroker());
			_cloneMemberMethod(_extend.prototype, members);
			_cloneMember(_extend, staticMembers);
			_extend.prototype._className = _extend._className = function() {
				return className;
			}
			_extend.prototype._class = function() {
				return _extend;
			}
			return _extend;
		}
	};
})();
