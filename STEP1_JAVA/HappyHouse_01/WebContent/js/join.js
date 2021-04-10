$(function() {	
		// 회원 가입 처리
	$('#join-submit').click(function() {
		if ($("#inputName").val() == '') {
			alert('이름을 입력하세요');
			$("#inputName").focus();
			return false;
		}

		if ($("#inputPw").val() == '') {
			alert('비밀번호를 입력하세요');
			$("#inputPw").focus();
			return false;
		}

		if ($("#inputAddress").val() == '') {
			alert('비밀번호를 둘다 동일하게 입력하세요');
			return false;
		}

		if ($("#inputPhone").val() == '') {
			alert('휴대폰 번호를 입력하세요');
			$("#inputPhone").focus();
			return false;
		}
		alert(' 회원가입 성공 ! ');
	});
	

	// 로그인처리
	$('#login-btn').click(function() {
		loginId = $('#loginId').val();
		loginPw = $('#loginPw').val();
		$.ajax({
			url : 'userlist.xml',
			type : 'GET',
			dataType : 'xml',
			success : function(response) {
				checkLogin(response);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("error : " + textStatus + "\n" + errorThrown);
			}
		});
	});

	// 로그아웃 처리
	$('#logout').click(function() {
		$('#logout').hide();
		$('#login').show();
	});
	// 아이디-비밀번호 매치 확인
	function checkLogin(data) {
		var canLogin = false;
		$(data).find('user').each(
				function() {
					if ($(this).find("id").text() == loginId
							&& $(this).find("password").text() == loginPw) {
						alert(' 로그인 성공 ! ');
						$('#logInfo').text(loginId);
						$('#logout').show();
						$('#login').hide();
						canLogin = true;
						return false;
					}
				});
		if (!canLogin) {
			alert(' 아이디와 비밀번호를 확인하세요 ! ');
		}
	}
});