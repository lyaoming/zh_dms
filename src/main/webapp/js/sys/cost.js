$(function () {
    $("#jqGrid").jqGrid({
        url: '../cost/list',
        datatype: "json",
        colModel: [			
			{ label: 'Key', name: 'cId', width: 50, key: true,hidden:true },
			{ label: '收费项', name: 'cName', width: 80 }	,
			{ label: '新增时间', name: 'addTime', width: 80 },
			{ label: '操作人员', name: 'addName', width: 80 }
		],
		viewrecords: true,
		height:700,
		rowNum:15,
		rowList :[15,30,50,100,500],
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		cost:{
			cName:'',
			addTime:'',
			addName:''
		},
		modaltitle:''
	},
	methods: {
		add:function(event){
			vm.modaltitle='新增';
			vm.cost.cName=null;
			vm.cost.cId=null
			var time=new Date();
			var month=time.getMonth();
			if(month<10){
				month="0"+month;
			}
			var day =time.getDay();
			if(day<10){
				day="0"+day;
			}
			var hours=time.getHours();
			if(hours<10){
				hours="0"+hours;
			}
			var minutes=time.getMinutes();
			if(minutes<10){
				minutes="0"+minutes;
			}
			var seconds=time.getSeconds();
			if(seconds<10){
				seconds="0"+seconds;
			}
			vm.cost.addTime=time.getFullYear()+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
			$.getJSON("../sys/user/info", function(r){
				vm.cost.addName= r.user.username;
			});
			$("#Modal").modal("show");
		},
		update: function (event) {
			vm.modaltitle='修改'
			var cId = getSelectedRow();
			if(cId == null){
				return ;
			}
			vm.getInfo(cId);
		},
		getInfo: function(cId){
			$.get("../cost/info/"+cId, function(r){
				vm.cost = r.cost;
				$("#Modal").modal("show");
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.cost.cId == null ? "../cost/save" : "../cost/update";
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(vm.cost),
				success: function(r){
					if(r.code === 0){
						layer.msg('操作成功',{offset:'100px'}, function(index){
							$("#Modal").modal("hide");
							$("#jqGrid").trigger("reloadGrid");

						});
					}else{
						layer.msg(r.msg,{offset: '100px'});
					}
				},
				error:function (r) {
					layer.msg("录入失败！请检查信息是否有误或是漏填！",{offset: '100px;'});
				}
			});
		},
		del: function (event) {
			var cIds = getSelectedRows();
			if(cIds == null){
				return ;
			}
			layer.confirm('<i class="fa fa-exclamation-triangle fa-3x" aria-hidden="true" style="color: red"></i>确定要删除选中的记录？若已在使用该收费可能会影响数据完整性！',{offset:'100px'}, function(){
				$.ajax({
					type: "POST",
				    url: "../cost/delete",
				    data: JSON.stringify(cIds),
				    success: function(r){
						if(r.code == 0){
							layer.msg('操作成功',{offset:'100px'}, function(index){
								$("#jqGrid").trigger("reloadGrid");
								$("#Modal").modal("hide");
							});
						}else{
							layer.msg(r.msg,{offset:'100px'});
						}
					},
					error:function (r) {
						layer.msg("录入失败！请检查信息是否有误或是漏填！",{offset: '100px;'});
					}
				});
			});
		}
	}
});