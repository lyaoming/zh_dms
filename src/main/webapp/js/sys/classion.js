$(function () {
    $("#jqGrid").jqGrid({
        url: '../classion/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true,hidden:true },
			{ label: '人员类型', name: 'classion', width: 80 }, 			
			{ label: '添加时间', name: 'addTime', width: 80 }, 			
			{ label: '操作人员', name: 'addName', width: 80 }			
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		modaltitle:"新增",
		classion:{
			id:null,
			classion:null,
			addTime:null,
			addName:null,
		}
	},
	methods: {
		add:function(event){
            vm.classion.classion=null;
            vm.classion.id=null;
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
			vm.classion.addTime=time.getFullYear()+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
			$.getJSON("../sys/user/info", function(r){
				vm.classion.addName= r.user.username;
			});
			$("#Modal").modal("show");
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.getInfo(id);
		},
		getInfo: function(id){
			$.get("../classion/info/"+id, function(r){
				vm.classion = r.classion;
				vm.modaltitle="修改";
				$("#Modal").modal("show");
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.classion.id == null ? "../classion/save" : "../classion/update";
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(vm.classion),
				success: function(r){
					if(r.code === 0){
						layer.msg('操作成功', {offset:'100px'},function(index){
							$("#jqGrid").trigger("reloadGrid");
							$("#Modal").modal("hide");
						});
					}else{
						layer.alert(r.msg);
					}
				},
				error:function (r) {
					layer.msg("请检查必填项信息是否遗漏!",{offset:'100px'})
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			layer.confirm('确定要删除选中的记录？',{offset:'100px'}, function(){
				$.ajax({
					type: "POST",
				    url: "../classion/delete",
				    data: JSON.stringify(ids),
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