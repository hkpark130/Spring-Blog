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
            alert(JSON,stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            contents: $('#contents').val(),
        };
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/posts/update/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            beforeSend : function(xhr)
            {
                xhr.setRequestHeader(header, token);
            }
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/posts/'+id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            type: 'DELETE',
            url: '/api/posts/delete/'+id,
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