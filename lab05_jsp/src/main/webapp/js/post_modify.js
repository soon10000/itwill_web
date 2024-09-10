/**
 * modify.jsp에 포함
 */


// HTML DOM(Document Object Model) 모든 HTML 컨텐트 로딩이 끝났을 때 이벤트 리스너를 실행
// 해당 DOMContentLoaded 안에 함수를 작성해서 사용하는 경우 스크립트간 변수 충돌을 줄일 수 있어 프레임워크 등과 함께 사용할 때 안전하게 사용할 수 있다.
 document.addEventListener('DOMContentLoaded', () => {   
        // form 요소를 찾음:
        const modifyForm = document.querySelector('form#modifyForm');
        
        // 글 번호가 입력된 input#id 요소를 찾음:
        const inputId = document.querySelector('input#id');
        
        // 제목이 입력된 input#title 요소를 찾음:
        const inputTitle = document.querySelector('input#title');
        
        // 글 내용이 작성된 textarea#content 요소를 찾음:
        const textareaContent = document.querySelector('textarea#content');
        
        // 삭제 버튼을 찾음:
        const btnDelete = document.querySelector('button#btnDelete');
        
        // 업데이트 버튼 찾음
        const btnUpdate = document.querySelector('button#btnUpdate');
        
        // 삭제 버튼에 클릭 이벤트 리스너를 설정
        btnDelete.addEventListener('click', () => {
            const result = confirm('정말 삭제할까요?');  // confirm 이라는 메서드는 js 자체가 가지고 있는 메서드
//            console.log(result); // -> confirm()의 리턴값을 확인해보기 위해 콘솔창 이용 (true(확인)/false(취소) 리턴 확인함)
            if (result) {
                // 삭제(GET) 방식 요청을 서버로 보냄.
                location.href = `delete?id=${inputId.value}`; // 사용한 ${}의 경우 EL이 아니며 해당 $의 경우 자바스크립트 내 문자열 템플릿에 자바스크립트 변수의 속성을 넣는것 
                // location.href 의 경우 다른 URL로 보낸다는 메서드 = 이후 값은 상대경로로 보내는 URL링크
            }
        });
        
        // 업데이트 버튼에 클릭 이벤트 리스너를 설정
        btnUpdate.addEventListener('click', () => {
            // 제목과 내용이 비어 있는지 체크:
            const title = inputTitle.value; // input 요소에 입력된 값
            const content = textareaContent.value; // input 요소에 입력된 값
            if (title === ''|| content === ''){
                alert('제목과 내용은 반드시 입력해야 합니다.');
                return; // 함수 종료
            }
            
            const result = confirm('수정 하시겠습니까?');
            if (result){
                modifyForm.method = 'post'; // 폼 제출 방식 메서드 설정!!
                modifyForm.action = 'update'; // 폼 제출 요청 주소 설정
                modifyForm.submit(); // 품 제출(서버로 요청을 보냄)
            }
        });
        
});