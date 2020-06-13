$(function () {
    $("#jqGrid").jqGrid({
        url: '../department/list',
        datatype: "json",
        colModel: [			
			{ label: 'Key', name: 'dpmId', width: 50, key: true,hidden:true },
			{ label: '部室名称', name: 'dpmName', width: 80 },
			{ label: '新增时间', name: 'addTime', width: 80 },
			{ label: '操作人员', name: 'addName', width: 80 }
        ],
		viewrecords: true,
		height:550,
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
		modaltitle:" ",
		department:{
			dpmName:null,
			addTime:'',
			addName:''
		}
	},
	created:function(){

	},
	methods: {
		add:function(event){
            vm.modaltitle='新增';
            vm.department.dpmName=null;
            vm.department.dpmId=null;
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
            vm.department.addTime=time.getFullYear()+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
			$.getJSON("../sys/user/info", function(r){
				vm.department.addName= r.user.username;
			});
            $("#Modal").modal("show");
		},
		update: function (event) {
			var dpmId = getSelectedRow();
			vm.modaltitle='修改'
			vm.getInfo(dpmId);
		},
		getInfo: function(dpmId){
			$.get("../department/info/"+dpmId, function(r){
				vm.department = r.department;
			});
			$("#Modal").modal("show");
		},
		saveOrUpdate: function (event) {
			var url = vm.department.dpmId == null ? "../department/save" : "../department/update";
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(vm.department),
				success: function(r){
					if(r.code === 0){
						layer.msg('操作成功',{offset:'100px'}, function(index){
							$("#Modal").modal("hide");
							$("#jqGrid").trigger("reloadGrid");

						});
					}else{
						layer.msg(r.msg,{offset:'100px'});
					}
				},
				error:function (r) {
					layer.msg("录入失败！请检查信息是否有误或是漏填！",{offset: '100px;'});
				}
			});
		},
		del: function (event) {
			var dpmIds = getSelectedRows();
			if(dpmIds == null){
				return ;
			}
			layer.confirm('确定要删除选中的记录？',{offset:'100px'}, function(){
				$.ajax({
					type: "POST",
				    url: "../department/delete",
				    data: JSON.stringify(dpmIds),
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
		}
	}
});