/**
 * /post/modify.html 파일에 포함.
 * 포스트 업데이트, 삭제 버튼 이벤트 처리
 */
document.addEventListener('DOMContentLoaded', () => {

    // 버튼 찾기 영역
    const contentId = document.querySelector('input#id');
    const btnDelete = document.querySelector('button#btnDelete');
    const btnUpdate = document.querySelector('button#btnUpdate');

    // 삭제 버튼에 이벤트 리스너를 설정
    btnDelete.addEventListener('click', () => {
        const result = confirm('해당 게시물을 삭제하시겠습니까?')
        if (result) {
            location.href = `delete?id=${contentId.value}`;
        }
    })





    // 찾은 업데이트 버튼에 클릭 이벤트 리스너를 삽입한다.
    btnUpdate.addEventListener('click', () => {
        const result = confirm('해당 게시물을 수정하시겠습니까?')
        const title = document.querySelector('input#title').value.trim();
        const content = document.querySelector('textarea#content').value.trim();
        if (title === '' || content === '') {
            alert('제목과 내용을 작성해주세요.')
            return;
        }
        
        if (result) {
            const updateForm = document.querySelector('form#updateForm');
            updateForm.submit();
            alert('수정이 완료되었습니다.');
        }

    })








});