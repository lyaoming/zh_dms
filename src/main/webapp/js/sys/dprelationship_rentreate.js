$(function () {
	$("#jqGrid").jqGrid({
		url: '../dprelationship/retreatelist',
		datatype: "json",
		colModel: [
			{label: 'pdId', name: 'pdId', width: 50, key: true, hidden: true},
			{label: '姓名', name: 'pName', width: 80},
			{label: '入住房屋号', name: 'dAddress', width: 80},
			{ label: '预期到期时间', name: 'expectedDueTime', width: 80 },
			{
				label: '到期预警', name: 'expectedDueTime', width: 80, formatter: function (value, options, row) {
					var nowDate=new Date();
					var year=nowDate.getFullYear();
					var month=nowDate.getMonth()+1;
					if(month<10)month="0"+month;
					var day=nowDate.getDate();
					if(day<10)day="0"+day;
					var nowTime=year+"-"+month+"-"+day;
					var d1=new Date(nowTime);
					var d2=new Date(value);
					var res= parseInt((d2-d1)/(3600000*24*30));
					if((d2-d1<0)&&res<=3){
						return '<span style="color: red;">超期</span>'
					}
					else if((d2-d1>0)&&res>3){
						return '<span style="color:green">租期还有'+res+'个月</span>'
					}else if((d2-d1>0)&&res<=3){

						return '<span style="color:#FF8800">租约即将到期</span>';
					}
				}
			},
			{
				label: '状态', name: 'status', width: 80, formatter: function (value, options, row) {
					if (value == 1) {
						return '<span style="color: green">未退租</span>';
					} else if (value == 0) {
						return '<span style="color: red">已退租</span>';
					} else {
						return '';
					}
				}
			},
		],
		viewrecords: true,
		height:550,
		rowNum:15,
		rowList :[15,30,50,100,500],
		rownumbers: true,
		rownumWidth: 25,
		autowidth: true,
		multiselect: true,
		pager: "#jqGridPager",
		jsonReader: {
			root: "page.list",
			page: "page.currPage",
			total: "page.totalPage",
			records: "page.totalCount"
		},
		prmNames: {
			page: "page",
			rows: "limit",
			order: "order"
		},
		gridComplete: function () {
			//隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
		}
	});
});
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		dprelationship:{
			rentreate:'',
		},
		dpkeyword:'',
		modaltitle:'',
		select1:null,
		dpdorm:''

	},
	methods: {
		Rentreate:function(){
			var pdIds=getSelectedRows();
			if(pdIds==null){
				return;
			}
			vm.save(pdIds);
		},
		querydp:function(){
			$("#jqGrid").jqGrid('setGridParam',{
				postData: {
					'dpkeyword':vm.dpkeyword,
					'dpdorm':vm.dpdorm,
					'sbgTime':$("#sbgTime").val(),
					'sendTime':$("#sendTime").val(),
				},
				page:1
			}).trigger("reloadGrid");
		},
		Continute:function(){
			var pdId = getSelectedRow();
			var status=getSelectedStatus();
			if(pdId == null){
				return ;
			}
			if(status!='<span class="label label-danger">已退租</span>')
			{vm.getInfo(pdId);}else{layer.msg("人员已退租，无法修改信息！",{offset:'100PX'})}
		},
		getInfo: function(pdId){
			$.get("../dprelationship/info/"+pdId, function(r){
				vm.dprelationship = r.res[0];
				vm.modaltitle='续租';
				$("#endTime").val(vm.dprelationship.expectedDueTime);
				$("#Modal").modal("show");
			});
		},
		save: function (pdIds) {
			var url="../dprelationship/leaveSelect";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(pdIds),
			    success: function(r){
			    	if(r.code === 0){
						layer.msg('退租成功',{offset:'100px'}, function(index){
							$("#jqGrid").trigger("reloadGrid");
						});
					}else{
						layer.msg(r.msg,{offset:'100px'});
					}
				}
			});
		},
		saveOrUpdate: function (event){
			vm.dprelationship.expectedDueTime=$("#endTime").val();
			var url ="../dprelationship/continute ";
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(vm.dprelationship),
				success: function(r){
					if(r.code === 0){
						layer.msg('操作成功',{offset:'100px'}, function(index){
							$("#jqGrid").trigger("reloadGrid");
						});
					}else{
						layer.msg(r.msg,{offset:'100px'});
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
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