$(function () {
    $('.renzhun').blur(function () {
        $(this).val($(this).val().replace(/</,'&lt').replace(/>/,'&gt').replace('|','或'))
    });
});