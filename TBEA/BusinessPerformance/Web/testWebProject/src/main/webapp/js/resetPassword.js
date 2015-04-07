$(document).ready(function() {
	$("#btnPasswordOK").click(function() {
		submitNewPassword();
	});
	$("#loadNewPassword").keydown(function(e) {
		if (e.keyCode == 13) {
			submitNewPassword();
		}
	});
	$("#reloadNewPassword").keydown(function(e) {
		if (e.keyCode == 13) {
			submitNewPassword();
		}
	});
	$("#j_username").focus(function() {
		$("#sameAsOldPassword").hide();
		$("#newPasswordNotSame").hide();
		$("#checkPassword").hide();
	});
	$("#j_password").focus(function() {
		$("#sameAsOldPassword").hide();
		$("#newPasswordNotSame").hide();
		$("#checkPassword").hide();
	});
	$("#loadNewPassword").focus(function() {
		$("#sameAsOldPassword").hide();
		$("#newPasswordNotSame").hide();
		$("#checkPassword").hide();
	});
	$("#reloadNewPassword").focus(function() {
		$("#sameAsOldPassword").hide();
		$("#newPasswordNotSame").hide();
		$("#checkPassword").hide();
	});
	$("#btnPasswordCancel").click(function() {
		$("#j_username").val("");
		$("#j_password").val("");
		$("#loadNewPassword").val("");
		$("#reloadNewPassword").val("");
	});
});
function afterLoadPage() {
	var result = $("#result").val();
	var message = $("#message").val();
	if (result == "true") {
		Util.MessageBox.confirm(message, true).then(function() {
			window.location.href = '../jsp/login.jsp';
		});
	} else if (result != "") {
		Util.MessageBox.tip(message);
	}
}
function checkPassword(pw) {
	return /^[a-zA-Z0-9]+$/.test(pw);
}
function submitNewPassword() {
	var loadOldPassword = $("#j_password").val();
	var loadNewPassword = $("#loadNewPassword").val();
	var reloadNewPassword = $("#reloadNewPassword").val();
	if (checkPassword(loadOldPassword) && checkPassword(loadNewPassword)
			&& checkPassword(reloadNewPassword)) {
		if (loadOldPassword == reloadNewPassword) {
			$("#sameAsOldPassword").show();
		} else if (loadNewPassword != reloadNewPassword) {
			$("#newPasswordNotSame").show();
		} else {
			$("#resetPasswordFrom").submit();
		}
	} else {
		$("#checkPassword").show();
	}
}
