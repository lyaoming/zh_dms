$(function () {
    $("#jqGrid").jqGrid({
        url: '../useunit/list',
        datatype: "json",
        colModel: [			
			{ label: 'tId', name: 'tId', width: 50, key: true,hidden:true},
			{ label: '使用单位', name: 'useUnit', width: 80 }, 			
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
		modaltitle:"新增",
		useUnit:{
			useUnit:null,
			tId:null,
			addName:null,
			addTime:null
		}
	},
	methods: {
		add:function(){
			vm.useUnit.useUnit=null;
			vm.modaltitle='新增';
			vm.useUnit.tId=null;
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
			vm.useUnit.addTime=time.getFullYear()+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
			$.getJSON("../sys/user/info", function(r){
				vm.useUnit.addName= r.user.username;
			});
			$("#Modal").modal("show");
		},
		update: function (event) {
			var tId = getSelectedRow();
			if(tId == null){
				return ;
			}
			vm.modaltitle="修改";
			vm.getInfo(tId);
			$("#Modal").modal("show");
		},
		getInfo: function(tId){
			$.get("../useunit/info/"+tId, function(r){
				vm.useUnit = r.useUnit;
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.useUnit.tId == null ? "../useunit/save" : "../useunit/update";
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(vm.useUnit),
				success: function(r){
					if(r.code === 0){
						layer.msg('操作成功', {offset:'100px'},function(index){
							$("#jqGrid").trigger("reloadGrid");
							$("#Modal").modal("hide");
						});
					}else{
						layer.alert(r.msg,{offset:'100px'});
					}
				},
				error:function (r) {
					layer.msg("提交失败！请检查信息是否有误或是漏填！",{offset: '100px;'});
				}
			});
		},
		del: function (event) {
			var tIds = getSelectedRows();
			if(tIds == null){
				return ;
			}
			
			layer.confirm('确定要删除选中的记录？',{offset:'100px'}, function(){
				$.ajax({
					type: "POST",
				    url: "../useunit/delete",
				    data: JSON.stringify(tIds),
				    success: function(r){
						if(r.code == 0){
							layer.msg('操作成功',{offset:'100px'}, function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							layer.alert(r.msg,{offset:'100px'});
						}
					}
				});
			});
		}
	}
});