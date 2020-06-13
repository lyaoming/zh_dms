var dId = T.p("dId");
var head;
var rId;

var zTree;

var zTreeNodes;

var selectNode;

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
				zTree=$.fn.zTree.init($("#dormTree"), setting, zTreeNodes);

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

function Dorm(dorm) {
	var res = [];
	for (var i = 0; i < dorm.length; i++) {
		res.push({
			name: dorm[i].dAddress,
			id: dorm[i].dId,
			pId:dorm[i].parentId,
			searchId:dorm[i].dId,
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
	var spaceWidth = 10;
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

/******************************************************点击节点事件***************************************************/
function clickNode(event, treeId, treeNode) {
	if(treeNode.searchId!=null){
		getInfo(treeNode.searchId);
		selectNode=treeNode.searchId;
	}else{
		layer.msg("请选择一条信息！",{offset:'100px'})
	}
};
/***************************************************获取节点信息*****************************************************/
function getInfo(dId){
	$.get("../dorm/info/"+dId, function(r){
		vm.dorm = r.res[0];
		vm.room=[];
		if(vm.dorm.parentId>0) {
			vm.parentAddress = vm.dorm.dSuperiorAddress;
			vm.bfdType=vm.dorm.dSpecification;
		}
		if(r.res.length>1) {
			vm.room = r.res[1];
			var info = "";
			for (var i = 0; i < vm.room.length; i++) {
				info = info + vm.room[i].roomName + ":" + vm.room[i].roomAera + "m2  ";
			}
			vm.roomInfo = info;
			$("#room").css("display", "block");
		}else{
			$("#room").css("display", "none");
		}
	});
};
/************************************设置上级地址生成树setting*************************************************/
var settingDorm = {
	data: {
		simpleData: {
			enable: true,
			idKey: "dId",
			pIdKey: "parentId",
			rootPId:1
		},
		key: {
			url:"nourl",
			name:"dAddress"
		}
	}
};
var ztreeDorm;
var rId;
var vm = new Vue({
		el: '#rrapp',
		data: {
			title: "地址生成树",
			parentAddress:'',
			roomInfo:'',
			bfdType:null,
			dorm: {
				dSuperiorAddress:null
			},
			useAdmin:{},
			useUnit:{},
			dormRoom:{},
			room:[],
			modaltitle:'',
			type:'',
			costs:[],
		},
		created: function () {
			this.getAddress();
			this.getUseAdmin();
			this.getUseUnit();
		},
		methods: {
			getUseAdmin:function(){
				$.get("../useadmin/list",function (r) {
					vm.useAdmin=r.useAdminList;
				});
			},
			getUseUnit:function(){
				$.get("../useunit/list",function (r) {
					vm.useUnit=r.useUnitList;
				});
			},
			getInfoVillage:function(){
			vm.parentAddress=null;
			vm.modaltitle="修改小区信息";
			$("#VModal").modal("show");
			},
			getInfoHouse:function(){
			vm.modaltitle="修改房屋信息";
			$("#HModal").modal("show");
			},
			getInfoCost:function(rId){
				$.get("../cprelationship/info/"+rId, function(r){
					if(r.res!==null&&r.res.length>0){

						r.res.forEach( item => {
							vm.costs.forEach( cost=> {
								if (item.cId === cost.cId) {
									cost.rId=item.rId;
									cost.value=item.value;
									cost.id=item.id;
								}
							})
						})
						
						
						// for(var i=0;i<r.res.length;i++){
						// 	for(var j=0;j<vm.costs.length;j++){
						// 		if(vm.costs[j].cId===r.res[i].cId){
						// 			vm.costs[j].rId=r.res[i].rId;
						// 			vm.costs[j].value=r.res[i].value;
						// 			vm.costs[j].id=r.res[i].id;
						// 		}
						// 	}
						// }
					}else{
						vm.costs.forEach(cost=>{
							cost.rId=rId;
						});
					}
				});
			},
			update:function(){
			if(vm.dorm.parentId===0){
				if(selectNode===null){
					layer.msg("请选择一条信息！",{offset:'100px'})
				}else{
					vm.getInfoVillage();
				}
			}else{
				if(selectNode===null){
					layer.msg("请选择一条信息！",{offset:'100px'})
				}else{
					vm.getInfoHouse();
				}
				}
			},
			getAddress:function(){
				$.get("../dorm/list", function (res) {
					ztreeDorm = $.fn.zTree.init($("#AddressTree"), settingDorm, res.dormList);
					let nodes1 = ztreeDorm.getNodes();
					for(var i=0;i<nodes1.length;i++) {
						ztreeDorm.expandNode(nodes1[i],false,false,false);
					}
				});
			},
			area:function(index){
				$.get("../dormroom/info/"+vm.room[index].rId, function(r){
					vm.dormRoom=r.dormRoom;
					$("#ERModal").modal("show");
				});
			},
			charge:function(index){
				vm.costs=[];
				$.get("../cost/list",function (r) {
					r.costList.forEach( item=>{
						vm.costs.push({
							cName: item.cName,
							cId: item.cId,
							value: '',
							id: null,
							rId: null
						})
					});
					rId=vm.room[index].rId;
					vm.getInfoCost(rId);
					$("#cModal").modal("show");
				});
			},
			AddressTree: function () {
				$("#AddressLayer").show();
				$("#AddressTree").show();
				layer.open({
					type: 1,
					offset: '50px',
					skin: 'layui-layer-molv',
					title: "请选择地址",
					area: ['300px', '500px'],
					shade: 0,
					shadeClose: false,
					anim: 1,
					content: jQuery("#AddressLayer"),
					btn: ['确定', '取消'],
					btn1: function (index) {
						var node = ztreeDorm.getSelectedNodes();
						if (node.length > 0) {
							var parentNode = node[0].getParentNode();
						}//获取父节点
						vm.parentAddress =node[0].dAddress;
						vm.dorm.parentId = node[0].dId;
						$("#AddressLayer").hide();
						$("#AddressTree").hide();
						layer.close(index);
						console.log(node[0].dId);
					},
					btn2: function (index) {
						$("#AddressLayer").hide();
						$("#AddressTree").hide();
						layer.close(index);
					},
					cancel: function (index, layero) {
						$("#AddressLayer").hide();
						$("#AddressTree").hide();
						layer.close(index);
					}
				});
			},
			saveOrUpdateVillage: function (event) {
				var url = vm.dorm.dId == null ? "../dorm/save" : "../dorm/update";
				if(vm.dorm.dAddress!=null&&vm.dorm.orderNum!=null) {
					vm.dorm.parentId=0;
					$.ajax({
						type: "POST",
						url: url,
						data: JSON.stringify(vm.dorm),
						success: function (r) {
							if (r.code === 0) {
								layer.msg('操作成功', {offset: '100px;'}, function (index) {
									init();
									setTimeout(()=>{
										var node = treeObj.getNodeByParam("searchId",r.dId);
										if (node != null) {
											treeObj.selectNode(node)
										}
									},1000)

									vm.getAddress();
									getInfo(r.dId);
									$("#VModal").modal("hide");
								});
							} else {
								layer.msg("请完善必填项信息！", {offset: '100px;'});
							}
						}
					});
				}else{
					layer.msg("提交失败！请完善带<span style=\"color:red;\">&nbsp;*&nbsp;</span>必填项信息！",{offset: '100px;'});
				}
			},
			saveOrUpdateHouse: function (event) {
				var url = vm.dorm.dId == null ? "../dorm/save" : "../dorm/update";
				   if(vm.dorm.orderNum!=null&&(vm.dorm.parentId!=null||vm.dorm.parentId!=0)&&vm.dorm.uId!=null&&vm.dorm.tId!=null&&vm.dorm.dAddress!=null&&vm.dorm.dArea&&vm.dorm.dType&&vm.dorm.dSpecification){
					  if(vm.dorm.dSuperiorAddress!=vm.parentAddress) {
						  vm.dorm.dSuperiorAddress = vm.parentAddress;
						  vm.dorm.dAddress = vm.parentAddress + vm.dorm.dAddress
					  }
					   vm.dorm.bfdType=vm.bfdType;
					   $.ajax({
						   type: "POST",
						   url: url,
						   data: JSON.stringify(vm.dorm),
						   success: function (r) {
							   if (r.code === 0) {
								   layer.msg('操作成功', {offset: '100px;'}, function (index) {
									   init();
										   setTimeout(()=>{
											var node = treeObj.getNodeByParam("searchId",r.dId);
										   	if (node != null) {
											   treeObj.selectNode(node)
										      }
										   },1000)

									   vm.getAddress();
									   if (vm.dorm.dId==null){
										   vm.addRoom(r);
									   } else {
										   getInfo(r.dId);
									   }
									   $("#HModal").modal("hide");
								   });
							   } else {
								   layer.msg("请完善必填项信息！",{offset: '100px;'});
							   }
						   }
					   });
				   }else{
					   layer.msg("提交失败！请完善带<span style=\"color:red;\">&nbsp;*&nbsp;</span>必填项信息！",{offset: '100px;'});
				   }
				},
			saveOrUpdateRoom: function (event) {
				var url=vm.room[0].rId==null? "../dormroom/save":"../dormroom/update";
				$.ajax({
					type: "POST",
					url: url,
					data: JSON.stringify(vm.room),
					success: function (r) {
						if (r.code === 0) {
							layer.msg('操作成功', {offset: '100px;'}, function (index) {
								var info = "";
								for (var i = 0; i < vm.room.length; i++) {
									info = info + vm.room[i].roomName + ":" + vm.room[i].roomAera + "m2  ";
								}
								vm.roomInfo = info;
								if(vm.room[0].parentId!=undefined||vm.room[0].parentId!=null)
								getInfo(vm.room[0].parentId);

							});
						} else {
							layer.msg(r.msg, {offset: '100px;'});
						}
					}
				});
			},
			saveOrUpdateERoom: function (event) {
				var url="../dormroom/update";
				$.ajax({
					type: "POST",
					url: url,
					data: JSON.stringify(vm.dormRoom),
					success: function (r) {
						if (r.code === 0) {
							layer.msg('操作成功', {offset: '100px;'}, function (index) {
								getInfo(selectNode);
							});
						} else {
							layer.msg(r.msg, {offset: '100px;'});
						}
					}
				});
			},
			saveOrUpdateCost: function (event) {
				flag=0;
				for(var i=0;i<vm.costs.length;i++){
					vm.costs[i].rId=rId;
					if(vm.costs[i].id!=null){
						flag=flag+1;
					}
				}
				var url=flag==0?"../cprelationship/save":"../cprelationship/update";
				$.ajax({
					type: "POST",
					url: url,
					data: JSON.stringify(vm.costs),
					success: function (r) {
						if (r.code === 0) {
							layer.msg('操作成功', {offset: '100px;'}, function (index) {
								getInfo(selectNode);
							});
						} else {
							layer.msg(r.msg, {offset: '100px;'});
						}
					}
				});
			},
			del: function (event) {
				var dId = selectNode;
				if (dId>=0) {
					layer.confirm('确定要删除选中的记录？', {offset: '100px'}, function () {
						$.ajax({
							type: "POST",
							url: "../dorm/delete/" + dId,
							success: function (r) {
								if (r.code == 0) {
									layer.msg('删除成功', {offset: '100px'}, function (index) {
										location.href = "../sys/dorm_add.html"
									});
								} else {
									layer.msg(r.msg, {offset: '100px'});
								}
							}
						});
					});
				}else{
					layer.msg("请选择删除的小区！",{offset:'100px'});
				}
			},
			addVillage:function () {
				vm.dorm={};
				vm.modaltitle="新增小区";
				$("#useadmin").css("display", "none");
				$("#VModal").modal("show");
			},
			addHouse:function () {
				vm.modaltitle="新增房屋";
                 vm.dorm={
					 dSuperiorAddress:null
				 };

                 vm.room=[];
				 vm.parentAddress='';
				 vm.roomInfo='';
				 $("#useadmin").css("display","block");
                 $("#HModal").modal("show");
			},
			addRoom:function(r){
				vm.room=[];
				vm.room=[{rId:null,parentId:null,roomName:'A房间',roomAera:null},{rId:null,parentId:null,roomName:'B房间',roomAera:null}]
				if(vm.dorm.dSpecification==2){
						vm.room.push({rId:null,parentId:null,roomName:'C房间',roomAera:null});
					}else if(vm.dorm.dSpecification==3){
					vm.room.push({rId:null,parentId:null,roomName:'C房间',roomAera:null});
					vm.room.push({rId:null,parentId:null,roomName:'D房间',roomAera:null});
				   }
					for(var i=0;i<vm.room.length;i++){
						vm.room[i].parentId=r.dId;
					}
					$("#RModal").modal("show");
			}
		}

});