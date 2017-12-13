//upload 컨트롤러의 upload 메소드의 리턴 값 
function getFileInfo(fullName){
	
	var fileName, imgSrc, getLink;//getLink 클릭했을 때 불러오는 URL
	
	if(checkImgType(fullName)){
		imgSrc = "/displayFile?fileName=" + fullName;
		
		var front = fullName.substr(0, 12);
		var end = fullName.substr(14);
		//_s(썸네일 표시) 제외한 원본 이미지 파일 
		getLink = "/displayFile?fileName=" + front + end;
	}else{
		imgSrc = "/resources/dist/img/file.png";
		getLink = "/displayFile?fileName=" + fullName;
	}
	
	fileName = fullName.substr(fullName.lastIndexOf("_") + 1);//UploadUtil에서 실제 파일명 앞에 _ 붙여서 uid표시
	
	return {fileName : fileName, imgSrc : imgSrc, getLink : getLink, fullName : fullName};
}

function checkImgType(fileName){
	var pattern = /jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);
}