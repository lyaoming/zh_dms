
/**************************************************设置与宿舍有关数据变量************************************************/
var dormName=[];//地址名称
var usage;//楼层宿舍房间使用数量
var total;//楼层宿舍房间总数量
var nodeParent=0;//判断父节点是否为空
var pervParent=0;
var roomNum=1;//一个宿舍的房间数（根据是否拆分分为1 2 3）
var roomUse=0;
var count=0;//查子节点数
var zTreeNodes;
var treeObj;
var setting = {
	view: {
		showLine:false,
		addDiyDom: addDiyDom
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		onClick: clickNode
	}
};

init();

/****************************************function***********************************************************/

/**
 * 初始化树
 */
function init() {
	var data = null;

	//加载所有的资产分类信息
	$.ajax({
		type: "get",
		url: "../dorm/list",
		data: "",
		success: function (res) {
			if (res.code == 0) {
				data = Dorm(res.dormList);
				//初始化列表
				zTreeNodes = data;
				//初始化树
				$.fn.zTree.init($("#dormTree"), setting, zTreeNodes);

				treeObj = $.fn.zTree.getZTreeObj("dormTree");

				var nodes = treeObj.getNodes();

				if (nodes.length > 0) {
					for(var i=0;i<nodes.length;i++) {
						treeObj.expandNode(nodes[i],true,false,false);
					}
				}

			}
		}
	});
}
/*********************************************设置节点数据*********************************************************/
function Dorm(dorm) {
	var res = [];
	for (var i = 0; i < dorm.length; i++) {
		res.push({
			name: dorm[i].dAddress,
			id: dorm[i].dId,
			pId:dorm[i].parentId,
			searchId:dorm[i].dId,
			nowNum:dorm[i].dNum,
			aLLNum:dorm[i].dAllnum,
		});
	}
	return res;
}

/**
 * 添加dom
 * @param treeId
 * @param treeNode
 */
function addDiyDom(treeId, treeNode) {
	var spaceWidth =10;
	var liObj = $("#" + treeNode.tId);
	var aObj = $("#" + treeNode.tId + "_a");
	var switchObj = $("#" + treeNode.tId + "_switch");
	var icoObj = $("#" + treeNode.tId + "_ico");
	var spanObj = $("#" + treeNode.tId + "_span");
	aObj.attr('title', '');
	aObj.append('<div class="diy switch"></div>');
	var div = $(liObj).find('div').eq(0);
	switchObj.remove();
	spanObj.remove();
	icoObj.remove();
	div.append(switchObj);
	div.append(spanObj);
	var spaceStr = "<span style='height:0px;display: inline-block;width:" + (spaceWidth * treeNode.level) + "px'></span>";
	switchObj.before(spaceStr);
}

/*****************************************************点击生成树节点事件**************************************************/
function clickNode(event, treeId, treeNode) {
	//置空以避免节点间的数据混合
	total=[];
	usage=[];
	var parent=treeNode.getParentNode();
	if(parent!=null) {
		pervParent = parent.pId;
	}
	nodeParent = treeNode.pId//获取父节点
		if (treeNode.isParent) {
			count = treeNode.children.length;//获取点击节点的子节点长度
			for (var i = 0; i < count; i++) {
				dormName[i] = treeNode.children[i].name;//遍历子节点名字放于集合中用于视图呈现
				var Allchildren = treeObj.transformToArray(treeNode.children[i]);
				var sum1 = 0
				var sum2 = 0;
				for (var j = 0; j < Allchildren.length; j++) {
					sum1 += Allchildren[j].nowNum;
					sum2 += Allchildren[j].aLLNum;
				}
				usage[i] = sum1;
				total[i] = sum2;
			}
		} else {
			count = 0;//若点击节点不是父节点设置子节点数为0；
		}
		if (count > 0) {
		createDorm();
		} else if (count == 0) {
			$(".dorm_floor").html(' ');
		}
		getInfo(treeNode.searchId);
		getPersonnel(treeNode.searchId);
};
/****************************************************获取节点信息**********************************************************/
function getInfo(dId){
	$.get("../dorm/info/"+dId, function(r){
		vm.dorm = r.res[0];
		vm.room=[];
		if(r.res.length>1){
			vm.room=r.res[1];
		}
		vm.dprelationship.dAddress=vm.dorm.dAddress;
		vm.dprelationship.dId=vm.dorm.dId;
		roomNum=vm.dorm.dAllnum;
		roomUse=vm.dorm.dNum;
		createRoom();
	});
};
function getPersonnel(dId) {
	$.get("../dorm/personnel/"+dId, function(r){
		vm. dormPersonnel=r.personnel;
	});
}

/*******************************************************创建宿舍房间**************************************************/
function createRoom(){
	var roomWidth=100/roomNum;
	$("#room").html(" ");
	if(roomNum>0){
		$(".dorm_main").css("display","block");
		$(".dorm_other").css("display","block");
		$(".dorm_room").css("display","block");
		$(".dorm_floor").css("display","none");
		$("#info").css("display","block");
	}else{
		$(".dorm_main").css("display","none");
		$(".dorm_other").css("display","none");
		$(".dorm_room").css("display","none");
		$(".dorm_floor").css("display","block");
		$("#info").css("display","none");
	}
	var roomClass='dorm_rooms';
	var roomShow;
	for(var i=0;i<roomNum;i++){
		if(roomUse==roomNum){roomClass='dorm_rooms dorm_full'}
		if(vm.room.length>0) {
			if(vm.room[i].pId>0){roomClass='dorm_rooms dorm_full'}
			else if(vm.room[i].pId==null||vm.room[i].pId==0){roomClass='dorm_rooms dorm_empty'}
			roomShow = "<div class='" + roomClass + "' style='width:" + roomWidth + "% '>" + "<p>" + vm.room[i].roomName + "</p>" + vm.room[i].roomAera + "&nbsp;m2" + "</div>";
		}else{
			roomShow = "<div class='" + roomClass + "' style='width:" + roomWidth + "% '>" + "<p>全套</p>" + "</div>";
		}
		$("#room").append(roomShow);

	}
};

function  createDorm() {
	var space=2;
	var dormShow='';
	var classStyle="dorm";
	$(".dorm_floor").html(" ");
	// if(nodeParent>0) {

		for (var i = 0; i < count; i++) {

			if (usage[i] == total[i] && usage[i]> 0) {
				classStyle = "dorm_over"
			} else if (usage[i] == 0 && total[i] > 0) {
				classStyle = "dorm"
			} else if ((usage[i] / total[i]) == 0.5) {
				classStyle = "dorm_half"
			} else if ((usage[i] / total[i]) == (1 / 3)) {
				classStyle = "dorm_one"
			} else if ((usage[i] / total[i]) == (2 / 3)) {
				classStyle = "dorm_two"
			}else if((usage[i] / total[i]) == (1 / 4)){
				classStyle = "dorm_one1"
			}else if((usage[i] / total[i]) == (3 / 4)){
				classStyle = "dorm_three"
			}

			dormShow="<div class='"+classStyle+"' style='margin:"+space+"%'>"+"<p>"+dormName[i]+"</p>"+"<p style='font-size:18px;font-weight: bold;margin-top: 5px;'>"+usage[i]+"/"+total[i]+"</p>"+"</div>";
			$(".dorm_floor").append(dormShow);
		}
	// }
	// 	if(nodeParent==null){
	// 		for (var i = 0; i <count; i++) {
	// 			dormShow="<div class='dorm_building' style='margin:"+space+"%'>"+"<p>"+dormName[i]+"</p>"+"<i class='fa fa-building fa-2x'></i>"+"<p id='date'>"+usage[i]+"/"+total[i]+"</p>"+"</div>";
	// 			$(".dorm_floor").append(dormShow);
	// 		}
	// 	}
}
/************************************************************VUE***************************************************/
var vm = new Vue({
	el:'#rrapp',
	data:{
		costshow:'',
		pkeyword:'',
		costshow:'',
		ckeyword:'',
		select1:'',
		select2:'',
		modaltitle:'',
	    dorm:{},
	    dprelationship:{
			dAddress:'',
			dId:'',
			pName:'',
			pId:'',
			cName:'',
		},
		personnel:{
			pDepartment:'',
			pName:'',
			pSex:null,
		},
		room:[],
	    dormPersonnel:[],
		costs:[],
		department:{},
		checkedCosts:[],
		classion:{}
	},
	created:function(){
		$.get("../department/list",function (r) {
			vm.department=r.departmentList;
		});
		$.get("../classion/list",function (r) {
			vm.classion=r.classionList;
		});
	},
	methods: {
		read:function(){
			vm.connect();
		},
		//入住
		into:function (event) {
			/*************************************入住数据置空**************************************/
				    vm.dprelationship.rId=null;
				    vm.costs=[];
			        vm.checkedCosts=[];
			        vm.dprelationship.pName='';
					vm.dprelationship.pId=null;
					vm.dprelationship.cName='';
					vm.dprelationship.deposit=null;
					vm.dprelationship.depositMoney=null;
					$("#beingTime").val("");
			        $("#endTime").val("");
                    $("#Modal").modal("show");

		},
		//退租
		out:function (event) {
			obj=document.getElementsByName('checkItem');
		    var index=[];
		    for(k in obj){
		     	if(obj[k].checked){index.push(obj[k].value)}
		    }
			var pIds=[];
		    for(i in index){
		   	   pIds.push(vm.dormPersonnel[i].pId)
		    }
		    if(pIds.length>0) {
				vm.saveleft(pIds);
			}else{
		    	layer.msg("至少选择一个要退租的人员！")
			}
		},
		iFempty:function(){
			for(var i=0;i<vm.room.length;i++){
				if(vm.room[i].rId==vm.dprelationship.rId){
					if(vm.room[i].pId!=null){
						layer.msg("该房间已经住人！",{offset:"200px"});
					}
				}
			}
			if(vm.dprelationship.rId!=null) {
				$.get("../cprelationship/info/" +vm.dprelationship.rId, function (r) {
					if (r.res !== null && r.res.length > 0) {
						vm.costs = r.res;
					}else{
						vm.costs=[];
					}
				});
			}
		},
		addDpm:function(index){
			vm.personnel.pDepartment=vm.department[index].dpmName;
		},
		saveOrUpdate:function(event) {
			vm.dprelationship.costs=vm.checkedCosts;
			vm.dprelationship.checkInTime=$("#beingTime").val();
			vm.dprelationship.expectedDueTime=$("#endTime").val();
			vm.dprelationship.status=1;
			var dId=vm.dprelationship.dId;
			var url = vm.dprelationship.pdId == null ? "../dprelationship/save" : "../dprelationship/update";
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(vm.dprelationship),
				success: function(r){
					if(r.code === 0){
						layer.msg('入住成功',{offset:'100px'}, function(index){
							vm.disconnect();
							getPersonnel(dId);
							getInfo(dId);
							init();
							var node = treeObj.getNodeByParam("searchId", dId);
							if (node != null) {
								setTimeout(()=>{
									treeObj.selectNode(node)
								},500)
							}
							$("#Modal").modal("hide");
						});
					}else{
						layer.msg(r.msg,{offset:'100px'});
					}
				},
				error:function (r) {
					layer.msg("入住失败！请检查信息是否有误或是漏填！",{offset: '100px;'});
				}
			});
		},
		psaveOrUpdate:function(event) {
			vm.personnel.initiationTime=$("#pTime").val();
			vm.dprelationship.pName=vm.personnel.pName;

			var url = vm.personnel.pId == null ? "../personnel/save" : "../personnel/update";
			if(vm.personnel.pName!=null&&vm.personnel.pSex!=null&&vm.personnel.pCategroy!=null&&vm.personnel.pDepartment!=null&&vm.personnel.initiationTime!=null&&vm.personnel.pPhone!=null) {
				$.ajax({
					type: "POST",
					url: url,
					data: JSON.stringify(vm.personnel),
					success: function (r) {
						if (r.code === 0) {
							layer.msg('操作成功', {offset: '100px'}, function (index) {
								vm.disconnect();
								$("#jqGrid").trigger("reloadGrid");
								$("#apModal").modal("hide");
								vm.dprelationship.pId=r.id;
							});
						}
					},
					error:function (r) {
						layer.msg("录入失败！请检查信息是否有误或是漏填！",{offset: '100px;'});
					}
				});
			}else{
				layer.msg("提交失败！请完善带<span style=\"color:red;\">&nbsp;*&nbsp;</span>必填项信息！",{offset: '100px;'});
			}
		},
		addp:function (event) {
			/*************************************人员数据置空**************************************/
			vm.personnel.pName='';
			vm.personnel.pDepartment='';
			vm.personnel.pSex='';
			vm.personnel.pPhone='';
			vm.personnel.initiationTime='';
			vm.personnel.pCategroy='';
			vm.personnel.pNumber='';
			$("#pTime").val("");
            $("#apModal").modal("show");
		},
		toJson:function(str)
		{
			//var obj = JSON.parse(str);
			//return obj;
			return eval('('+str+')');
		},
		connect:function(){
			var result={};
			var CertCtl = document.getElementById("CertCtl");
			try {
				result = CertCtl.connect();
				result=vm.toJson(result);
				if(result.resultFlag==0){
					vm.getStatus();
				}else{
					layer.mg("请检查设备是否连接正常!",{offset:"100px"})
				}
			} catch (e)
			{
			}
		},
		disconnect:function(){
			var result ={};
			var CertCtl = document.getElementById("CertCtl");
			try
			{
				result = CertCtl.disconnect();
				result=vm.toJson(result);
				console.log(result);
				if(result.resultFlag==1){
					layer.msg("设备已断开!",{offset:'100px'});
				}
			} catch (e)
			{

			}
		},
		getStatus:function(){
			var result={};
			var CertCtl = document.getElementById("CertCtl");
			try {
				result = CertCtl.getStatus();
				result=vm.toJson(result);
				if(result.resultFlag==0){
					vm.readCert();
				}else {
					layer.msg("读卡设备连接异常，请检查设备!",{offset:'100px'})
				}
			} catch(e) {

			}
		},
		readCert:function(){

			var CertCtl = document.getElementById("CertCtl");
			try {
				let result=[];
				result= CertCtl.readCert();
				var resultObject=vm.toJson(result);
				if(resultObject!=null) {
					if (resultObject.resultFlag == -1) {
						layer.msg("读卡发生异常!", {offset: '100px'})
					}
					if (resultObject.resultFlag == 0) {
						vm.personnel.pName = resultObject.resultContent.partyName;
						if(resultObject.resultContent.gender=="男"){
							vm.personnel.pSex = 1;
						}else{
							vm.personnel.pSex=0
						}
						vm.personnel.pNumber = resultObject.resultContent.certNumber;
						layer.msg("读取完成,可以移开身份证!", {offset: '100px'});
					}
				}
			} catch(e)
			{
			}
		},
		saveleft: function (pIds) {
			var dId=vm.dprelationship.dId;
			var url="../dprelationship/leave";
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(pIds),
				success: function(r){
					if(r.code === 0){
						layer.msg('退租成功',{offset:'100px'}, function(index){
							getPersonnel(dId);
							getInfo(dId);
							init();
							var node = treeObj.getNodeByParam("searchId", dId);
							if (node != null) {
								setTimeout(()=>{
									treeObj.selectNode(node)
								},500)
							}
						});
					}else{
						layer.msg(r.msg,{offset:'100px'});
					}
				}
			});
		},
	},
});
