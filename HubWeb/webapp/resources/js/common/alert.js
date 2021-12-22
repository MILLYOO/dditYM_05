/**
 * 
 */
 $().ready(function () {
            $("#confirm").click(function () {
                Swal.fire({
                    title: '정말로 삭제하시겠습니까?',
                    text: "다시 한 번 확인해주세요",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    cancelButtonText: '취소'
                }).then((result) => {
                    if (result.isConfirmed) {
                        Swal.fire(
                            '삭제가 완료되었습니다.',
                            '',/*얘 없으면 위에 아이콘 안 떠서 그냥 이렇게라도 비워놔야됨*/
                            'success'
                        )
                    }
                })
            });
        });
 
 
 
 
 $().ready(function () {
     $("#alert").click(function () {
    	 Swal.fire('품목이 존재하지 않습니당')
     });
 });