/**
 * Description: 后台渠道查询相关用
 * Company: NewLeader
 * author: luoshuhong
 * date 2015-9-23
 */

$(function() {
	loadData();
});

//加载数据
function loadData() {
	$.ajax({
		type: "POST",
		url: "../admin/channel/query",
		async:false,
		success : function(msg) {
			var json = eval("("+msg+")");;
			if (json.status == 'success') {
				var dataList = json.data;
				 $.each(JSON.parse(json.data), function (idx,item) {
					 $('#dataListTbody').append('<tr>' 
						+ '<td>' + item.name + '</td>'
						+ '<td>' + item.code + '</td>'
						+ '<td>' + item.createTimeStr + '</td>'
						+ '<td><a href=\'javascript:qrCodeCreate(\"' + item.code + '\",\"perm\"' + ')\'>二维码生成</a></td>'
// 								+ '<a href=\'javascript:qrCodeCreate(\"' + item.code + '\",\"temp\"' + ')\'>临时二维码</a>  '
						+ '<td><a href=\'javascript:cancel(\"' + item.id + '\")\'>删除</a>  | '
						+ ' <a href=\'javascript:update(\"' + item.id + '\",\"' + item.name  + '\",\"' + item.code + '\")\'>更新</a></td>'
						+ '</tr>' 
					 );
				 });
			} else {
				alert('错误，请重试！');
			}
		},
		error : function(msg) {
			alert(msg);
		}
	});
}

//二维码生成
function qrCodeCreate(code,type) {
	if (isEmpty(code) || isEmpty(type)) {
		return;
	}
	
	if('temp' == type && isNaN(code)){
	   alert("生成临时二维码，编码必须是数字");
	   return;
	}
	var postData = {"type":type, "channelCode":code};
    $.ajax({
		type: "POST",
		url: "../admin/qrCode/create",
		data: postData,
		async:false,
		success : function(msg) {
			eval("var json=" + msg);
			if (json.status == 'success') {
				if (isEmpty(json.data)) {
					alert('生成二维码失败，请重试 ！');
					return;
				}
				var logo = "&logo=http://donottell.me/CSS/Images/homepage/logo.png";
				$("#img_code").attr('src',"http://qr.liantu.com/api.php?text=" + json.data + logo); 
				$('#imgModal').modal('show');  
			} else {
				$("#errMsg").val('添加失败，请重试！');
//				alert('添加失败，请重试！');
			}
		}
	});
}

//逻辑删除
function cancel(id) {
	if (isEmpty(id)) {
		return;
	}
	if(!confirm("是否确定删除！！")){
		return;
	}
	var postData = {"id":id};
    $.ajax({
		type: "POST",
		url: "../admin/channel/cancel",
		data: postData,
		async:false,
		success : function(msg) {
			eval("var json=" + msg);
			if (json.status == 'success') {
				$('#dataListTbody').html('');
				loadData();
			} else {
				alert('删除失败，请重试！');
			}
		}
	});
}

//更新
function update(id,name,code) {
	if (isEmpty(id) || isEmpty(name) || isEmpty(code)) {
		return;
	}
	$('#uid').val(id);
	$('#uchannelName').val(name);
	$('#uchannelCode').val(code);
	$("#errMsg").html('');
	$('#updateModal').modal('show');  
	return;
}

function updateComfirm() {
	var id = $('#uid').val();
	var name = $('#uchannelName').val();
	var code = $('#uchannelCode').val();
	if (isEmpty(id) || isEmpty(name) || isEmpty(code)) {
		return;
	}
	
	var postData = {"id":id, "channelCode":code, "channelName":name};
    $.ajax({
		type: "POST",
		url: "../admin/channel/update",
		data: postData,
		async:false,
		success : function(msg) {
			eval("var json=" + msg);
			if (json.status == 'success') {
				$('#dataListTbody').html('');
				loadData();
				$('#updateModal').modal('hide'); 
			} else {
				$("#errMsg").html('修改失败，请重试！');
				$("#errMsg").show();
			}
		}
	});
	
}

/**
 *校验是否为null
 */
function isEmpty(value) {
	if ('' == value || undefined == value || null == value) {
		return true;
	}
	return false;
}