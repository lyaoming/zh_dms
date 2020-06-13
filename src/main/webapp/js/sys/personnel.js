var time;
$(function () {
    $("#jqGrid").jqGrid({
        url: '../personnel/list',
        datatype: "json",
        colModel: [			
			{ label: 'pId', name: 'pId', width: 50, key: true,hidden:true },
			{ label: '姓名', name: 'pName', width: 80 }, 			
			{ label: '部室', name: 'pDepartment', width: 80 }, 			
			{ label: '性别', name: 'pSex', width: 80 , formatter:function (value, options, row) {
					return  value===1?
						'男' : '女';
				} },
			{ label: '入行时间', name: 'initiationTime', width: 80 }, 			
			{ label: '联系电话', name: 'pPhone', width: 80 }, 			
			{ label: '人员类型', name: 'classion', width: 80},
			{ label: '身份证号码', name: 'pNumber', width: 80 }			
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
	$("#djqGrid").jqGrid({
		url: '../department/list',
		datatype: "json",
		colModel: [
			{ label: 'ID', name: 'dpmId', width:250, key: true,hidden:true },
			{ label: '部室名称', name: 'dpmName', width:720 }
		],
		viewrecords: true,
		height:180,
		rowNum:5,
		rowList : [5,10,20],
		rownumbers: true,
		rownumWidth: 25,
		autowidth:true,
		multiselect:false,
		pager: "#djqGridPager",
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
			$("#djqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
		}
	});
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		modaltitle:'',
		personnel:{
			pName:null,
			pSex:null,
			pId:null,
			pDepartment:'',
			initiationTime:null,
		},
		classion:{},
		department:{},
		psName:null,
		pDepartment:null,
		select1:null,
		select2:null,
	},
	created:function(){
		this.getDpm();
		$.get("../classion/list",function (r) {
			vm.classion=r.classionList;
		})
	},
	methods: {
		getDpm:function(){
			$.get("../department/list",function (r) {
				vm.department=r.departmentList;
			});
		},
		read:function(){
		 vm.connect();
		},
		add:function(event){
/***********************************数据置空******************************************************/
			vm.personnel.pName='';
			vm.personnel.pId=null;
			vm.personnel.pDepartment='';
			vm.personnel.pSex=null;
			vm.personnel.pPhone='';
			vm.personnel.initiationTime='';
			vm.personnel.pCategroy='';
			vm.personnel.pNumber='';
			$("#iTime").val("");
			$("#Modal").modal("show");
			vm.modaltitle='新增';
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
		query: function () {
			$("#jqGrid").jqGrid('setGridParam',{
				postData: {
					'pName':vm.psName,
					'pDepartment':vm.pDepartment,
					'select1':vm.select1,
					'select2':vm.select2,
				},
				page:1
			}).trigger("reloadGrid");
		},
		searchDpm:function(index){
			vm. pDepartment=vm.department[index].dpmName;
		},
		update: function (event) {
			var pId = getSelectedRow();
			if(pId == null){
				return ;
			}
			vm.getInfo(pId);
		},
		addDpm:function(index){
			console.log(vm.department[index].dpmName)
			vm.personnel.pDepartment=vm.department[index].dpmName;
		},
		del: function (event) {
			var pIds = getSelectedRows();
			if(pIds == null){
				return ;
			}
			layer.confirm('确定要删除选中的记录？',{offset:'100px'}, function(){
				$.ajax({
					type: "POST",
				    url: "../personnel/delete",
				    data: JSON.stringify(pIds),
				    success: function(r){
						if(r.code == 0){
							layer.msg('操作成功',{offset:'100px'}, function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							layer.msg(r.msg,{offset:'100px'});
						}
					}
				});
			});
		},
		getInfo: function(pId){
			$("#Modal").modal("show");
			$.get("../personnel/info/"+pId, function(r){
				vm.personnel = r.personnel;
				$("#iTime").val(vm.personnel.initiationTime);
				vm.modaltitle='修改';
			});
		},
		saveOrUpdate: function (event) {
			vm.personnel.initiationTime=$("#iTime").val();
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
								$("#jqGrid").trigger("reloadGrid");
								$("#Modal").modal("hide");
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