var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		classion:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../classion/info/"+id, function(r){
                vm.classion = r.classion;
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
						alert('操作成功', function(index){
							vm.back();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
	}
});