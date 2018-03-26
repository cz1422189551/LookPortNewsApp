function imgreset() {
    var objs = document.getElementsByTagName('img');
    for (var i = 0; i < objs.length; i++) {
        var img = objs[i];
        img.style.maxWidth = '100%';
        img.style.height = 'auto';
    }
}

//function changePage(json) {
//
//        var obj = eval('(' + json + ')');
//
//
//    document.getElementById('title2').innerHTML='标题';
//    document.getElementById('orign').innerHTML='来源';
//    document.getElementById('time').innerHTML='时间';
//    document.getElementById('pageSource').innerHTML='原文';
//      var content = document.getElementById('content_body');
//        content.innerHTML='原文';
//}
//changePage();

function changePage(title , orign , time, content_body,pageSource,editor) {
    document.getElementById('title2').innerHTML=title;
    document.getElementById('orign').innerHTML=orign;
    document.getElementById('time').innerHTML=time;
    document.getElementById('pageSource').href=pageSource
     document.getElementById('editor').innerHTML=editor
      var content = document.getElementById('content_body');
        content.innerHTML=content_body;

}


function changeFont() {
                 $('.content').attr("class","color")
             }
             changeFont();