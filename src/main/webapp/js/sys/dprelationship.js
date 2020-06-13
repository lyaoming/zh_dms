$(function () {
    $("#jqGrid").jqGrid({
        url: '../dprelationship/list',
        datatype: "json",
        colModel: [			
			{ label: 'pdId', name: 'pdId', width: 50, key: true,hidden:true },
			{ label: '姓名', name: 'pName', width: 50},
			{ label: '入住房屋号', name: 'dAddress', width:110},
			{ label: '房间', name: 'roomName', width:40,formatter:function (value) {
					if(value!=null){return value};
					if(value==null)return "全套";

				}},
			{ label: '登记入住时间', name: 'checkInTime', width: 60 },
			{ label: '预期到期时间', name: 'expectedDueTime', width: 60 },
			{ label: '是否已交押金', name: 'deposit', width: 40,formatter:function (value, options, row) {
				if(value==1){return '<i class="fa fa-check" aria-hidden="true"></i>';}else if(value==0){return '<i class="fa fa-times" aria-hidden="true"></i>';}else{return '';}} },
			{ label: '押金金额', name: 'depositMoney', width:60 },
			{ label:'收费项和金额',name:'costList',width:150,formatter:function (value) {
					var show = ' '
					if (value !=null) {
						for (var i = 0; i < value.length; i++) {
							show = show + "<span>" + value[i].cName + ":" + value[i].value + "元</span>  ";
						}
						return show;
					}else{return '<span style="color: red;">停止收费</span>'}
				}
			},

        ],
		viewrecords: true,
		height:550,
		rowNum:15,
		rowList :[15,30,50,100,500],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect:true,
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
});
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		modaltitle:'',
		select1:null,
		dpkeyword:null,
        dpkeyword:'',
        dpdorm:'',
		pkeyword:null,
		ckeyword:null,
		dprelationship:{
			pdId:'',
			dAddress:'',
			pName:'',
			cName:'',
			dId:'',
			pId:'',
			bfpId:'',
			status:'',
			flag:'',
			costs:null,
			beforeDid:'',//原来宿舍（用来更改宿舍人数）,
			bfrId:null,
		},
		personnel:{
			pId:null,
			pName:'',
			pDepartment:'',
			pSex:'',
			initiationTime:$("#pTime").val(),
			pPhone:'',
			pCategroy:'',
			pNumber:''
		},
		department:{},
		room:[],
		costs:[],
		checkedCosts:[],
		classion:{}
	},
	created:function(){
		$.get("../dorm/list", function (res) {
			ztreeDorm = $.fn.zTree.init($("#dormTree"), settingDorm, res.dormList);
			let nodes1 = ztreeDorm.getNodes();
			for(var i=0;i<nodes1.length;i++) {
				ztreeDorm.expandNode(nodes1[i],false,false,false);
			}
		});
		$.get("../classion/list",function (r) {
			vm.classion=r.classionList;
		});
		this.getDpm();

	},
	methods: {
		read:function(){
		  vm.connect();
		},
		getDpm:function(){
			$.get("../department/list",function (r) {
				vm.department=r.departmentList;
			});
		},
		getInfo: function(pdId){
			$.get("../dprelationship/info/"+pdId, function(r){
				vm.room=[];
				vm.checkedCosts=[];
				vm.dprelationship = r.res[0];
				if(r.res.length>1){
					vm.room=r.res[1]
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
				vm.dprelationship.beforeDid=vm.dprelationship.dId;
				vm.dprelationship.bfpId=vm.dprelationship.pId;
				vm.dprelationship.bfrId=vm.dprelationship.rId;
				vm.checkedCosts=vm.dprelationship.costs;
				vm.modaltitle='修改'
				$("#entryTime").val(vm.dprelationship.checkInTime);
				$("#endTime").val(vm.dprelationship.expectedDueTime);
				$("#Modal").modal("show");
			});
		},
		update: function (event) {
			var pdId = getSelectedRow();
			var status=getSelectedStatus();
			if(pdId == null){
				return ;
			}
			if(status!='<span class="label label-danger">退租</span>')
			{vm.getInfo(pdId);}else{layer.msg("人员已退租，无法修改信息！",{offset:'100PX'})}
		},
		dormTree: function () {
			$("#dormLayer").show();
			$("#dormTree").show();
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "请选择宿舍",
				area: ['300px', '500px'],
				shade: 0,
				shadeClose: false,
				anim: 1,
				content: jQuery("#dormLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var node = ztreeDorm.getSelectedNodes();
					if (node.length > 0) {
						var parentNode = node[0].getParentNode();
					}//获取父节点
					if(node[0].isParent){
						vm.dprelationship.flag=1;
					}
					vm.dprelationship.dAddress= node[0].dAddress;
					vm.dprelationship.dId= node[0].dId;
					vm.room=[];
					$.get("../dprelationship/room/"+node[0].dId,function (r) {
						vm.room=r.room;
					});
					$("#dormLayer").hide();
					$("#dormTree").hide();
					layer.close(index);
				},
				btn2: function (index) {
					$("#dormLayer").hide();
					$("#dormTree").hide();
					layer.close(index);
				},
				cancel: function (index, layero) {
					$("#dormLayer").hide();
					$("#dormTree").hide();
					layer.close(index);
				}
			});
		},
		querydp:function(){
            $("#jqGrid").jqGrid('setGridParam',{
                postData: {
                    'dpkeyword':vm.dpkeyword,
					'sbgTime':$("#sbgTime").val(),
					'sendTime':$("#sendTime").val(),
                    'dpdorm':vm.dpdorm
                },
                page:1
            }).trigger("reloadGrid");
		},
		add:function(event){
			vm.modaltitle='新增';
/******************************************置空****************************************************************/
            vm.checkedCosts=[];
            vm.dprelationship.pdId=null;
            vm.dprelationship.dAddress=null;
            vm.dprelationship.pName=null;
            vm.dprelationship.cName=null;
            vm.dprelationship.dId=null;
            vm.dprelationship.pId=null;
            vm.dprelationship.bfpId=null;
            vm.dprelationship.cId=null;
            vm.dprelationship.checkInTime=null;
            vm.dprelationship.expectedDueTime=null;
            vm.dprelationship.status=null;
            vm.dprelationship.deposit=null;
            vm.dprelationship.costs=null;
            vm.dprelationship.depositMoney=null;
            $("#entryTime").val("");
            $("#endTime").val("");
			$("#Modal").modal("show");

		},
		addp:function(){
			vm.modaltitle='新增';
			vm.personnel.pName=null;
			vm.personnel.pDepartment=null;
			vm.personnel.pSex=null;
			vm.personnel.pPhone='';
			vm.personnel.initiationTime=null;
			vm.personnel.pCategroy=null;
			vm.personnel.pNumber=null;
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
				if(result.resultFlag==0){vm.readCert();
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
		addDpm:function(index){
        	console.log(vm.department[index].dpmName)
			vm.personnel.pDepartment=vm.department[index].dpmName;
		},
		addc:function(){
		    $("#cModal").modal("show");
		},
		selectc:function(){
              vm.costshow='';
              vm.cost.forEach(item=>{
                  if(item['value']>0) {
                      vm.costshow = vm.costshow + item['cName'] + ":" + item['value'] + "  "
                  }
			  })
		},
		iFempty:function(){
			vm.checkedCosts=[];
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
		del: function (event) {
			var pdIds = getSelectedRows();
			if(pdIds == null){
				return ;
			}
			layer.confirm('确定删除选中的已退租的记录？',{offset:'100px'}, function(){
				$.ajax({
					type: "POST",
					url: "../dprelationship/delete",
					data: JSON.stringify(pdIds),
					success: function(r){
						if(r.code == 0){
							layer.msg('删除成功',{offset:'100px'}, function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							layer.msg(r.msg,{offset:'100px'});
						}
					}
				});
			});
		},
		saveOrUpdate: function (event){
			if(vm.checkedCosts.length>0) {
				vm.dprelationship.costs = vm.checkedCosts;
			}else {
				vm.dprelationship.costs=null;
			}
        	vm.dprelationship.checkInTime=$("#entryTime").val();
        	vm.dprelationship.expectedDueTime=$("#endTime").val();
			vm.dprelationship.status=1;
			var url = vm.dprelationship.pdId == null ? "../dprelationship/save" : "../dprelationship/update";
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(vm.dprelationship),
				success: function(r){
					if(r.code === 0){
						layer.msg('操作成功',{offset:'100px'}, function(index){
							vm.cost=null;
                            $("#jqGrid").trigger("reloadGrid");
                            vm.disconnect();
                            vm.getDpm();
                            vm.room=[];
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

		psaveOrUpdate: function (event) {
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
								vm.getDpm();
								vm.dprelationship.pId=r.id;
								$("#jqGrid").trigger("reloadGrid");
								$("#apModal").modal("hide");
							});
						}
					},
					error:function (r) {
						layer.msg("提交失败！请检查信息是否有误或是漏填！",{offset: '100px;'});
					}
				});
			}else{
				layer.msg("提交失败！请完善带<span style=\"color:red;\">&nbsp;*&nbsp;</span>必填项信息！",{offset: '100px;'});
			}
		},
	}
});
function getSelectedStatus() {
	//获取员工名字
	var grid = $("#jqGrid").getGridParam("selrow");
	var rowStatus = $("#jqGrid").getCell(grid,"status");
	//限制多选
	var selectedStatus = $("#jqGrid").getGridParam("selarrrow");
	if(selectedStatus.length > 1){
		return ;
	}
	return rowStatus;
}