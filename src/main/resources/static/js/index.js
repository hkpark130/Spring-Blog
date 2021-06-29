var main = {
    init : function () {
        var _this = this;

        if((typeof $('#contents')[0] != "undefined") && (typeof $('#result')[0] != "undefined")) {
            $('#contents')[0].onkeyup =
                function(e) {
                    $('#result')[0].innerHTML =
                    marked($('#contents').val());
                };
        }

        if((typeof $('#result')[0] != "undefined") && ($('#result')[0].innerHTML != "")){
            $('#result')[0].innerHTML = marked($('#result')[0].innerHTML);
        }

        $('#btn-save').on('click', function() {
            _this.save();
        });
        $('#btn-update').on('click', function() {
            _this.update();
        });
        $('#btn-delete').on('click', function() {
            var flag = confirm("진짜 삭제하시겠습니까?");

            if(flag == true){
              _this.delete();
            }
        });

    },
    save : function () {
        var data = {
            title: $('#title').val(),
            contents: $('#contents').val(),
            category: $('#category').val(),
        };
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            type: 'POST',
            url: '/api/posts/save',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            beforeSend : function(xhr)
            {
                xhr.setRequestHeader(header, token);
            }
        }).done(function() {
            alert("등록");
            window.location.href = '/';
        }).fail(function (error) {
           alert("글 등록에 실패하였습니다.");
       });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            contents: $('#contents').val(),
            category: $('#category').val(),
        };
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        var post_id = $('#post_id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/posts/update/'+post_id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            beforeSend : function(xhr)
            {
                xhr.setRequestHeader(header, token);
            }
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/posts/'+post_id;
        }).fail(function (error) {
           alert("글 수정에 실패하였습니다.");
       });
    },
    delete : function () {
        var post_id = $('#post_id').val();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            type: 'DELETE',
            url: '/api/posts/delete/'+post_id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            beforeSend : function(xhr)
            {
                xhr.setRequestHeader(header, token);
            }
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
           alert("글 삭제에 실패하였습니다.");
       });
    }
};

main.init();

function comment_save() {
    if(!$('#comment')[0].checkValidity()){
        alert("댓글을 입력해주세요.");
        return ;
    }

    var post_id = $('#post_id').val();
    var data = {
        post_id: post_id,
        user_id: $('#user_id').val(),
        parent_id: null,
        comment: $('#comment').val(),
        password: $('#password').val(),
    };
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        type: 'POST',
        url: '/api/comments/save',
        dataType: 'json',
        contentType:'application/json; charset=utf-8',
        data: JSON.stringify(data),
        beforeSend : function(xhr)
        {
            xhr.setRequestHeader(header, token);
        }
    }).done(function(data, textStatus, xhr) {
        alert("댓글이 등록되었습니다.");
        window.location.href = '/posts/'+post_id;
    }).fail(function (error) {
       alert("댓글 등록에 실패하였습니다.");
   });
}

function comment_edit(comment_id){
    var comment_user_id = $('#comment_user_id_'+comment_id).val();
    var comment = $('#comment_'+comment_id)[0].innerHTML;
    sessionStorage.setItem("comment_"+comment_id, $('#ul_comment_'+comment_id)[0].innerHTML);

    $('#ul_comment_'+comment_id)[0].innerHTML = `
    <div class="row">
        <div class="col-md-12">
            <label for="comment"> 댓글 쓰기 </label>
            <input type="text" class="form-control" id="edit_comment_`+comment_id+`" value="`+comment+`" style="margin-bottom:5px;">
            <div class="row justify-content-end">
                <button type="button" class="btn btn-success" onclick="comment_update('`+comment_id+`', '`+comment_user_id+`');">수정</button>
                <button type="button" class="btn btn-secondary" onclick="comment_edit_cancel('`+comment_id+`');">취소</button>
            </div>
        </div>
    </div>
    `;
}

function comment_edit_cancel(comment_id){
    $('#ul_comment_'+comment_id)[0].innerHTML = sessionStorage.getItem("comment_"+comment_id);
    sessionStorage.removeItem("comment_"+comment_id);
}

function comment_update(comment_id, comment_user_id) {
    if(comment_user_id == 'Guest' && !check_password(comment_id)){
        return ;
    }

    var post_id = $('#post_id').val();
    var data = {
        comment: $('#edit_comment_'+comment_id).val(),
    };
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    sessionStorage.removeItem("comment_"+comment_id);

    $.ajax({
        type: 'PUT',
        url: '/api/comments/update/'+comment_id,
        dataType: 'json',
        contentType:'application/json; charset=utf-8',
        data: JSON.stringify(data),
        beforeSend : function(xhr)
        {
            xhr.setRequestHeader(header, token);
        }
    }).done(function() {
        alert('댓글이 수정되었습니다.');
        window.location.href = '/posts/'+post_id;
    }).fail(function (error) {
        alert("댓글 수정에 실패하였습니다.");
    });
}

function comment_delete(comment_id) {
    var comment_user_id = $('#comment_user_id_'+comment_id).val();

    if(comment_user_id == 'Guest' && !check_password(comment_id)){
        return ;
    }

    var post_id = $('#post_id').val();
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        type: 'DELETE',
        url: '/api/comments/delete/'+comment_id,
        dataType: 'json',
        contentType:'application/json; charset=utf-8',
        beforeSend : function(xhr)
        {
            xhr.setRequestHeader(header, token);
        }
    }).done(function() {
        alert('댓글이 삭제되었습니다.');
        window.location.href = '/posts/'+post_id;
    }).fail(function (error) {
       alert("댓글 삭제에 실패하였습니다.");
   });
}

function check_password(comment_id) {
    var flag = false;
    var data = {
        password: prompt('패스워드를 입력하세요.',''),
    };
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    if(data.password != null){
        $.ajax({
            type: 'POST',
            url: '/api/comments/check_password/'+comment_id,
            async: false,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            beforeSend : function(xhr)
            {
                xhr.setRequestHeader(header, token);
            }
        }).done(function(data, textStatus, xhr) {
            if (data == 1) {
                flag = true;
            } else {
                alert('비밀번호가 틀렸습니다.');
                flag = false;
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
            flag = false;
        });
    }

    return flag;
}

function session_check(user_id){
    var flag = false;
    var data = {
            user_id: user_id,
        };
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        type: 'POST',
        url: '/api/session_check',
        async: false,
        dataType: 'json',
        contentType:'application/json; charset=utf-8',
        data: JSON.stringify(data),
        beforeSend : function(xhr)
        {
            xhr.setRequestHeader(header, token);
        }
    }).done(function(data, textStatus, xhr) {
        if (data == 1) {
            flag = true;
        } else {
            flag = false;
        }
    });

    return flag;
} // 해당 유저가 접속중인지 체크
