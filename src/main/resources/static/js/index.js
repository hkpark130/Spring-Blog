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
        }).fail(function() {
            alert(JSON.stringify(error));
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
            alert(JSON.stringify(error));
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
            alert(JSON.stringify(error));
        });
    }
};

main.init();

function comment_save() {
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
    }).fail(function() {
        alert(JSON.stringify(error));
    });
}

function comment_update(comment_id) {
    var post_id = $('#post_id').val();
    var data = {
        comment: $('#comment_'+comment_id)[0].innerHTML,
    };
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var comment_id = comment_id;

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
        alert(JSON.stringify(error));
    });
}

function comment_delete(comment_id) {

    if( !check_password(comment_id) ){
        return ;
    }

    var post_id = $('#post_id').val();
    var comment_id = comment_id;
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
        alert(JSON.stringify(error));
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