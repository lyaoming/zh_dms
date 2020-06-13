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
	vm.dgrelationship.dId=treeNode.searchId;
	vm.dgrelationship.dAddress=treeNode.name;
	if(treeNode.isParent||treeNode.pId==null) {
		layer.msg("请选择一间房屋！",{offset:"100px"})
	}else{
		$.get("../dgrelationship/room/"+vm.dgrelationship.dId,function(r){
			vm.room=r.room;
		});
		getInfo(treeNode.searchId);
	}
};
function getInfo(dId){
	vm.room=[];
	$("#jqGrid").jqGrid('setGridParam',{
		postData: {
			"dId":dId,
		},
		page:1
	}).trigger("reloadGrid");
}
$(function () {
	$("#jqGrid").jqGrid({
		url:"../dgrelationship/list",
		datatype: "json",
		colModel: [
			{ label: 'gdId', name: 'gdId', width: 50, key: true,hidden:true },
			{ label: '房屋号', name: 'dAddress', width: 80 },
			{ label: '房间', name: 'roomName', width: 80,formatter:function (value) {
				if(value==null){
					return "共用";
				}else{
					return value;
				}
				} },
			{ label: '房屋规格', name: 'dSpecification', width: 80,formatter:function(value){
				if(value==1){return '2房一厅'}
				if(value==2){return '3房一厅'}
				if(value==3){return '4房一厅'}
				else{return ''}
			}
			},
			{ label: '物品名称', name: 'gName', width: 80 },
			{ label: '物品类型', name: 'gType', width: 80 },
			{ label: '物品规格', name: 'gSpecification', width: 80 },
			{ label: '物品编号', name: 'gNumber', width: 80 ,formatter:function(value){
					if(value==null){return '非房卡无编号'}
					else{return value}
				}},
			{ label: '物品数量', name: 'gdNumber', width: 80 },
		],
		viewrecords: true,
		height: 400,
		rowNum: 10,
		rowList : [10,30,50],
		rownumbers: true,
		rownumWidth: 25,
		autowidth:true,
		multiselect: true,
		pager: "#jqGridPager",
		jsonReader : {
			root: "page.list",
			page: "page.currPage",
			total: "page.totalPage",
			records: "page.totalCount"
		},
		prmNames : {
			page:"page",
			rows:"limit",
			order: "order"
		},
		gridComplete:function(){
			//隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
		}
	});
	$("#gjqGrid").jqGrid({
		url: '../goods/list',
		datatype: "json",
		colModel: [
			{ label: 'gId', name: 'gId', width: 50, key: true ,hidden:true},
			{ label: '物品名称', name: 'gName', width:350 },
			{ label: '物品编号', name: 'gNumber', width:350 },
			{ label: '物品规格', name: 'gSpecification', width:350 },
			{ label: '物品类型', name: 'gType', width:340},

		],
		viewrecords: true,
		height: 400,
		rowNum: 10,
		rowList : [10,30,50],
		rownumbers: true,
		rownumWidth: 25,
		autowidth:true,
		multiselect: true,
		pager: "#gjqGridPager",
		jsonReader : {
			root: "page.list",
			page: "page.currPage",
			total: "page.totalPage",
			records: "page.totalCount"
		},
		prmNames : {
			page:"page",
			rows:"limit",
			order: "order"
		},
		gridComplete:function(){
			//隐藏grid底部滚动条
			$("#gjqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
		}
	});
});


var vm = new Vue({
	el:'#rrapp',
	data:{
		dgrelationship:{
			dId:null,
			rId:null,
			dAddress:'',
			gIds:null,
			gName:null,
			gNum:null,
			bgNum:null
		},
		room:[],
	    keyword:'',
	    modaltitle:'',
	},
	methods: {
		update: function (event) {
			var gdId = getSelectedRow();
			if(gdId == null){
				return ;
			}
			vm.getInfo(gdId);
		},
		getInfo: function(gdId){
			$.get("../dgrelationship/info/"+gdId, function(r){
				vm.dgrelationship = r.res[0];
				if(r.res.length>1){
					vm.room=r.res[1];
				}
				vm.dgrelationship.bgNum=vm.dgrelationship.gdNumber;
				$("#updateModal").modal("show");
			});
		},
		query:function(){
			$("#gjqGrid").jqGrid('setGridParam',{
				postData: {
					'keyword':vm.keyword,
				},
				page:1
			}).trigger("reloadGrid");
		},
		add:function(){
			vm.modaltitle="新增";
			vm.dgrelationship.gdId=null;
			vm.dgrelationship.gIds=null;
			vm.dgrelationship.rId=null;
			vm.dgrelationship.gName='';
			vm.dgrelationship.gName='';
			vm.dgrelationship.bgNum=null;
			vm.dgrelationship.gdNumber=null;
			$("#addModal").modal("show");

		},
		addg:function(){
			$("#gjqGrid").trigger("reloadGrid");
			$("#gModal").modal("show");
		},
		select:function(){
			vm.dgrelationship.gIds=getSelectedRows1();
			console.log(vm.dgrelationship.gIds);
			vm.dgrelationship.gName=getSelectedRowName();
			console.log(vm.dgrelationship.gName);
			vm.dgrelationship.gNum=getSelectedRowNum();
			if(vm.dgrelationship.gIds!=null){
				$("#gModal").modal("hide");
			}
		},
		saveOrUpdate: function (event) {
			var url = vm.dgrelationship.gdId == null ? "../dgrelationship/save" : "../dgrelationship/update";
				$.ajax({
					type: "POST",
					url: url,
					data: JSON.stringify(vm.dgrelationship),
					success: function (r) {
						if (r.code === 0) {
							layer.msg('操作成功', {offset: '100px'}, function (index) {
								$("#jqGrid").trigger("reloadGrid");
								$("#gjqGrid").trigger("reloadGrid");
								$("#addModal").modal("hide");
								$("#updateModal").modal("hide");
								vm.dgrelationship.gdId=null;
								vm.dgrelationship.gId =null;
								vm.dgrelationship.rId=null;
								vm.dgrelationship.gName =null;
								vm.dgrelationship.gName =null;
								vm.dgrelationship.bgNum =null;
								vm.dgrelationship.gdNumber =null;
							});
						} else {
							layer.msg(r.msg, {offset: '100px'});
						}
					},
				    error:function (r) {
					  layer.msg("提交失败！请检查信息是否有误或是漏填！",{offset: '100px;'});
				    }
				});
		},
		del: function (event) {
			var gdIds = getSelectedRows();
			if(gdIds == null){
				return ;
			}
			layer.confirm('确定要删除选中的记录？', {offset:'100px'},function(){
				$.ajax({
					type: "POST",
				    url: "../dgrelationship/delete",
				    data: JSON.stringify(gdIds),
				    success: function(r){
						if(r.code == 0){
							layer.msg('操作成功', {offset:'100px'},function(index){
								$("#jqGrid").trigger("reloadGrid");
								$("#gjqGrid").trigger("reloadGrid");
								vm.dgrelationship.gId='';
								vm.dgrelationship.gName='';
								vm.dgrelationship.gName='';
								vm.dgrelationship.bgNum='';
								vm.dgrelationship.gdNumber='';
							});
						}else{
							layer.msg(r.msg,{offset:"100px"});
						}
					}
				});
			});
		}
	}
});
function getSelectedRows1(){
	//获取员工ID
	var grid=$("#gjqGrid");
	var rowKey=grid.getGridParam("selrow");
	if(!rowKey){
		alert("请至少选择一条记录");
		return ;
	}
	return grid.getGridParam("selarrrow");
}
function getSelectedRowName() {
	//获取名字
	var names=[];
	//限制多选
	var selectedGname = $("#gjqGrid").getGridParam("selarrrow");
	for(var i=0;i<selectedGname.length;i++){
		var grid = selectedGname[i];
		var rowGname = $("#gjqGrid").getCell(grid,"gName");
		names.push(rowGname);
	}
	return names;
}
function getSelectedRowNum() {

	var grid = $("#gjqGrid").getGridParam("selrow");
	var rowGnum = $("#gjqGrid").getCell(grid,"gNum");

	var selectedGnum = $("#gjqGrid").getGridParam("selarrrow");
	if(selectedGnum.length > 1){
		return ;
	}
	return rowGnum;
}