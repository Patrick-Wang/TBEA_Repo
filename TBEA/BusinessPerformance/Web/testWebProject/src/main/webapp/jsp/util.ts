module Util {

	export function formatCurrency (val: string): string{
		
		if (val == "--" || val == ""){
			return val;
		}
		
		var dot : number = val.lastIndexOf('.');
		var intPart : string = "";
		var parts : string[] = [];
		var positive : boolean = (val.charAt(0) != '-');
		if (dot > 0){
			if (positive){
				intPart = val.substring(0, dot);
			} else{
				intPart = val.substring(1, dot);
			}
			parts.push(val.substring(dot));
		}
		else {
			if (positive){
				intPart = val;
			} else{
				intPart = val.substring(1);
			}
		}
		
		var leftLength : number = intPart.length;
		
		while (leftLength > 3){
			parts.push("," + intPart.substring(leftLength - 3, leftLength));
			leftLength -= 3;
		}
	
		parts.push(intPart.substring(0, leftLength));
		
		if (!positive){
			parts.push("-");
		}
		
		parts = parts.reverse();
		
		return parts.join("");
	}
}
