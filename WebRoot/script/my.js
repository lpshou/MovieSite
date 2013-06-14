function addImage(){
	//alert("hahah");
	var $div = $("<div><input type='file' name='upload' /></div>");
	$("#upload_imagesUpload").append($div);
}

function addMovie(){
	//alert("hahah");
	var $div = $("<div><input type='file' name='upload' /></div>");
	$("#upload_moviesUpload").append($div);
}

function uploading(){
	//alert("hahah");
	document.getElementById("uploading").style.display="";
	//计算实际图片个数，放入hidden中传入UpLoadMovie
	var $child = $("#upload_imagesUpload");
	var sum = $child.children().length;
	var imagesum=0;
	for(var i=0;i<sum;i++)
	{
		var li = $child.children().eq(i).children()[0];
		var has = $(li).val();
		if(has!=""){
			imagesum+=1;
		}
	}
	$("#imageSum").val(imagesum);
	//alert("实际图片个数："+$("#imageSum").val());

	//计算实际电影个数,放入hidden中传入UpLoadMovie
	var $child1 = $("#upload_moviesUpload");
	var sum1 = $child1.children().length;
	var moviesum = 0;
	for(var i=0;i<sum1;i++)
	{
		var li = $child1.children().eq(i).children()[0];
		var has = $(li).val();
		if(has!=""){
			moviesum+=1;
		}
	}
	$("#movieSum").val(moviesum);
	//alert("实际电影个数: "+$("#movieSum").val());
}

function uploadimage(){
	var image = document.getElementById("uploadimages");
	image.innerHTML='<input name="fileUpload" title="请点击浏览"  type="file" value="aaaa">';
}

function upload(basicfile) {
	var Sys = {};
	var ua = navigator.userAgent.toLowerCase();
	var s;
	(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : (s = ua
			.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : (s = ua
			.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : (s = ua
			.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : (s = ua
			.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
	// 以下进行测试
	if (Sys.ie) {
		alert('IE: ' + Sys.ie);
		// ie浏览器
		var isIE = (document.all) ? true : false;
		var isIE7 = isIE && (navigator.userAgent.indexOf('MSIE 7.0') != -1);
		var isIE8 = isIE && (navigator.userAgent.indexOf('MSIE 8.0') != -1);
		var file = document.getElementById("fileupload1");
		if (isIE7 || isIE8) {
			file.select();
			var path = document.selection.createRange().text;
			document.selection.empty();
			alert("ie下path： " + path);
			document.getElementById("hid").value = path;
		}
	}
	if (Sys.firefox) {
		alert('Firefox: ' + Sys.firefox);
		// firefox
		try {
			netscape.security.PrivilegeManager
					.enablePrivilege("UniversalXPConnect");
		} catch (e) {
			alert(' 目前只支持ie和firefox浏览器。如果是firefox请更改浏览器设置' + '在浏览器中输入about:config,然后设置signed.applets.codebase_principal_support 的值得为true');
			return;
		}
		var fname = document.getElementById("fileupload1").value;
		var file = Components.classes["@mozilla.org/file/local;1"]
				.createInstance(Components.interfaces.nsILocalFile);
		try {
			// Back slashes for windows
			file.initWithPath(fname.replace(/\//g, "/"));
		} catch (e) {
			if (e.result != Components.results.NS_ERROR_FILE_UNRECOGNIZED_PATH)
				throw e;
			alert(' 无法加载文件 ');
			return;
		}
		// alert("foxfire下path: " + file.path); // 取得文件路径
		document.getElementById("hid").value = file.path;
	}
}