
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
       function validateForm(form){
           if(form.title.value==""){
               alert("제목을 입력하세요");
               form.title.focus();
               return false;
           }
           if(form.ofile.value==""){
               alert("파일을 선택하세요");
               return false;
           }
       }
    </script>
</head>


<body>
<h2>다중 파일 업로드</h2>
<span style="color : red;"> ${errorMessage}</span>
<form name="fileForm" method="post" enctype="multipart/form-data"
      action="/upload/multiUpload.do" onsubmit="return validateForm(this);">
    제목 : <input type="text" name="title"><br/>
    카테고리(선택사항) :
    <input type="checkbox" name="cate" value="사진" checked>사진
    <input type="checkbox" name="cate" value="과제">과제
    <input type="checkbox" name="cate" value="워드">워드
    <input type="checkbox" name="cate" value="음원">음원 <br/>
    첨부파일 : <input type="file" name="ofile" multiple><br/>
    <input type="submit" value="전송">

</form>
</body>
</html>
