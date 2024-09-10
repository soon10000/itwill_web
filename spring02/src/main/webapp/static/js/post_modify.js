/**
 * /post/modify.jsp에 포함
 */

 console.log('*****');
 
 /* 모든 HTML의  Document 들의 로딩이 끝나고 발생을 시켜주는 메서드 */
 document.addEventListener('DOMContentLoaded', () => {
    
    const modifyForm = document.querySelector('form#modifyForm');
    const inputId = document.querySelector('input#id');
    const inputTitle = document.querySelector('input#title');
    const textContent = document.querySelector('textarea#content');
    const btnDelete = document.querySelector('button#btnDelete');
    const btnUpdate = document.querySelector('button#btnUpdate');
    
    // 삭제 버튼에 클릭 이벤트 리스너:
    btnDelete.addEventListener('click', () => {
        const result = confirm('정말 삭제할까요?');
        if (result) { // 사용자가 [확인]을 클릭 했을 때
        // GET 방식의 delete 요청을 서버로 보냄.
            location.href = `delete?id=${inputId.value}`;
        }
    });
    
    
    btnUpdate.addEventListener('click', () => {
        // 제목과 내용이 비어있는 지 체크:
        if(inputTitle.value === '' || textContent.value === ''){
            alert('제목과 내용을 입력해주세요.');
            return;
        }
        
        
        // 업데이트 내용 저장 확인:
        const result = confirm('수정된 내용을 저장할까요?');
        if (result){
            modifyForm.action='update'; // 요청주소
            modifyForm.method = 'POST'; // 요청 방식
            modifyForm.submit(); // 폼 양식 데이터 제출(서버로 요청을 보냄)
        }
    });
    
    
    
 }); 