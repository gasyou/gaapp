$(function() {
	$("input#loginId").blur(function() {
		var inputTag = $(this);
		var loginId = inputTag.val();

		/* ログインIDの重複チェック */
		$.ajax({
			url : GAM.APP + "/checkDuplicateLoginId.x?loginId=" + loginId,
			cache : false,
			dataType : "json",
			success : function(json) {
				var isDuplicate = json.duplicate;

				/* (See) Custom Styles */
				/* https://getbootstrap.com/docs/4.0/components/forms/#custom-styles */
				if (isDuplicate == true) {
					inputTag.removeClass("is-valid").addClass("is-invalid");
				} else {
					inputTag.removeClass("is-invalid").addClass("is-valid");
				}
			}
		});
	});
});