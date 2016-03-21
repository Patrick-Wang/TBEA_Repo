function Promise(){}
Promise.prototype.then = function(callBack){
	this.callBack = callBack;
}
Promise.prototype.complete = function(code){
	this.callBack(code);
}

promises = {};
camera = {};
camera.scan = function(){
	var timestamp = new Date().getTime() + "";
	promises[timestamp] = new Promise();
	window.Scanner.scan(timestamp);
	return promises[timestamp];
};

camera.onScanned = function(id, code){
	promises[id].complete(code);
}