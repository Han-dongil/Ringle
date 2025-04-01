수강신청 과제입니다.
튜터의 id : tutor , tutor2
학생의 id : student , student2
password :1234
로그인을 하게되면 jwt 토큰이 반환됩니다. 반환된 토큰은 각 api 오른쪽 상단에 있는 자물쇠 버튼을 눌러 입력하게되면 모든 api 요청의 헤더에 Authorization : Bearer 토큰값이 추가되어 로그인인증을 받을수있습니다.
포스트맨등의 툴을 이용해서 사용할떄는 해당 헤더값을 추가해주셔야 사용이 가능합니다.
/api/v1/student로 시작하는 api는 학생만 사용이 가능합니다.
/api/v1/tutor로 시작하는 api는 튜터만 사용이 가능합니다.
로그인이 되지않은 상태에서는 /api/v1/login api만 사용이 가능합니다.
