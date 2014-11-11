Class.define("JQGridSorter", function(source, col, sortFunc) {
	this.mSorter = new std.vector();
	this.mLockItem = {};
	this.mAsc = true;
	for (var i = 0; i < source.length; ++i) {
		this.mSorter.push(source[i][col]);
	}
	this.mSorter.sort(sortFunc);
}, {
	weight : function(chineseText, asc) {
		if (this.mLockItem.name != undefined) {
			if (this.mAsc != asc) {
				var pos = this.mLockItem.pos;
				this.mSorter.erase(this.mSorter.find(this.mLockItem.name));
				this.mSorter.insert(asc ? this.mLockItem.pos : this.mSorter.size()
						- this.mLockItem.pos, this.mLockItem.name);
				this.mAsc = asc;
			}
		}

		for (var i = 0; i < this.mSorter.size(); ++i) {
			if (this.mSorter.get(i) == chineseText) {
				return i;
			}
		}
		return -1;
	},

	moveToTail : function(name) {
		for (var i = 0; i < this.mSorter.size(); ++i) {
			if (this.mSorter.get(i) == name) {
				var item = this.mSorter.get(i);
				this.mSorter.erase(i);
				this.mSorter.push(item);
				break;
			}
		}
		return this;
	},

	lockPos : function(name) {
		for (var i = 0; i < this.mSorter.size(); ++i) {
			if (this.mSorter.get(i) == name) {
				this.mLockItem.name = name;
				this.mLockItem.pos = i;
				break;
			}
		}
		return this;
	}

}, {
	createChineseSorter : function(source, col, grid) {
		return new JQGridSorter(source, col, function(a, b) {
			return a.localeCompare(b);
		});
	},
	createFloatSorter : function(source, col, grid) {
		return new JQGridSorter(source, col, function(a, b) {
			if (typeof a == 'string') {
				a = a.replace(',', '');
				b = b.replace(',', '');
				return parseFloat(a) > parseFloat(b) ? 1 : -1;
			} else {
				return a > b ? 1 : -1;
			}
		});
	},
	createIntSorter : function(source, col, grid) {
		return new JQGridSorter(source, col, function(a, b) {
			if (typeof a == 'string') {
				return parseInt(a) > parseInt(b) ? 1 : -1;
			} else {
				return a > b ? 1 : (a == b ? 0 : -1);
			}
		});
	},
	createSorter : function(source, col, grid) {
		return new JQGridSorter(source, col, function(a, b) {
			return a > b ? 1 : (a == b ? 0 : -1);
		});
	}
});