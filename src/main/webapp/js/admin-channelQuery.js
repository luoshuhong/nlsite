/**
 * Description: 后台渠道查询相关用
 * Company: NewLeader
 * author: luoshuhong
 * date 2015-9-23
 */
$(function() {
	loadData('');
});

/**
 * 查询数据
 */
function query(){
	var value = $("#value").val();
	if (isEmpty(value)) {
		return;
	}
	loadData(value);
}

//加载数据
function loadData(value) {
	var postData = {"value":value};
	$.ajax({
		type: "POST",
		url: "../admin/channel/query",
		data: postData,
		async:false,
		success : function(msg) {
			var json = eval("("+msg+")");;
			if (json.status == 'success') {
				$('#dataListTbody').html('');
				var dataList = json.data;
				 $.each(JSON.parse(json.data), function (idx,item) {
					 var freeTypeDes = "无免费";
					 if (0 == item.freeType) {
						 freeTypeDes = "限时免费";
					 } else if (1 == item.freeType) {
						 freeTypeDes = "免费1份68元";
					 } else if (2 == item.freeType) {
						 freeTypeDes = "免费3份168";
					 } else if (3 == item.freeType) {
						 freeTypeDes = "免费5份268元";
					 }
					 
					 $('#dataListTbody').append('<tr>' 
						+ '<td>' + item.name + '</td>'
						+ '<td>' + item.code + '</td>'
						+ '<td>' + item.totalSubscribe + '</td>'
						+ '<td>' + item.currSubscribe + '</td>'
						+ '<td>' + item.unSubscribe + '</td>'
						+ '<td>' + item.unSubscribeRate + '%</td>'
						+ '<td>' + item.shareCount + '</td>'
						+ '<td>' + item.virualCount + '</td>'
						+ '<td>' + freeTypeDes + '</td>'
						+ '<td>' + item.createTimeStr + '</td>'
						+ '<td><a href=\'javascript:qrCodeCreate(\"' + item.code + '\",\"' + item.id +'\")\'>二维码生成</a> | ' 
						+ ' <a href=\'javascript:cancel(\"' + item.id + '\")\'>删除</a>  | '
						+ ' <a href=\'javascript:update(\"' + item.id + '\",\"' + item.name  + '\",\"' + item.code + '\",\"' + item.freeType + '\",\"' + item.freeDes + '\",\"' + item.freeStartDate + '\",\"' + item.freeEndDate + '\")\'>更新</a></td>'
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
function qrCodeCreate(code,id) {
	if (isEmpty(code) || isEmpty(id)) {
		return;
	}
	$("#img_code").attr('src',''); //清除上一次图片
	var postData = {"id":id, "channelCode":code};
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
				$("#img_code").attr('src', json.data); 
				$('#imgModal').modal('show');  
			} else {
				$("#errMsg").val('添加失败，请重试！');
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
function update(id,name,code, freeType, freeDes, freeStartDate, freeEndDate) {
	if (isEmpty(id) || isEmpty(name) || isEmpty(code)) {
		return;
	}
	$('#uid').val(id);
	$('#uchannelName').val(name);
	$('#uchannelCode').val(code);
	$("#errMsg").html('');
	$('#updateModal').modal('show');  
	
	$('#freeType').val(freeType);
	$('#freeDes').val(freeDes);
	$('#freeStartDate').val(freeStartDate);
	$('#freeEndDate').val(freeEndDate);
	if (-1 == freeType) {
		$("#freeDesDiv").hide();
		$("#freeDateDiv").hide();
		return;
	}
	if (0 == freeType) {
		$("#freeDateDiv").show();
	} else {
		$("#freeDateDiv").hide();
	}
	$("#freeDesDiv").show();
	return;
}

function updateComfirm() {
	var id = $('#uid').val();
	var name = $('#uchannelName').val();
	var code = $('#uchannelCode').val();
	
	var freeDes = $("#freeDes").val();
	var freeStartDate = $("#freeStartDate").val();
	var freeEndDate = $("#freeEndDate").val();
	var freeType = $("#freeType").val();
	
	/******** 参数校验 start ******************/
	if (isEmpty(name) || isEmpty(code)) {
		$("#errMsg").html('参数不能为空！');
		$("#errMsg").show();
		return;
	}
	if (0 == freeType) {
		if (isEmpty(freeDes) || isEmpty(freeStartDate) || isEmpty(freeEndDate)) {
			$("#errMsg").html('参数不能为空！');
			$("#errMsg").show();
			return;
		}
	}
	if (1 == freeType || 2 == freeType || 3 == freeType) {
		if (isEmpty(freeDes)) {
			$("#errMsg").html('参数不能为空！');
			$("#errMsg").show();
			return;
		}
	}
	
	var postData = {"id":id,"channelName":name, "channelCode":code, "freeType":freeType, "freeDes":freeDes, "freeStartDate":freeStartDate, "freeEndDate":freeEndDate};
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
 * 选中免费类型  处理事件
 */
function choseFreeType() {
	var freeType = $("#freeType").val();
	if (-1 == freeType) {
		$("#freeDesDiv").hide();
		$("#freeDateDiv").hide();
		return;
	}
	if (0 == freeType) {
		$("#freeDateDiv").show();
	} else {
		$("#freeDateDiv").hide();
	}
	
	$("#freeDesDiv").show();
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