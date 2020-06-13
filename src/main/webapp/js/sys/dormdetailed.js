
$(function () {
    $("#jqGrid").jqGrid({
        url: '../dormdetailed/list',
        datatype: "json",
        colModel: [
            { label:'ID',name:'rId',width:50,key:true,hidden:true},
            { label: '房屋号', name:'dAddress', width:80 },
            { label: '房间', name:'roomName', width:80 },
            { label: '房屋类型', name: 'dType', width: 80,formatter:function (value, options, row) {
                    if(value==1){
                        return '合并';
                    }else if(value==2){
                        return '拆分';
                    }else{
                        return '';
                    }
                } },

            { label: '使用权管理', name:'useAdmin', width:80 },
            { label: '使用单位', name:'useUnit', width:80 },
            { label: '是否入住', name:'yesNo', width:80,formatter:function (value) {
                    if(value==0)return "<span style='color:green;font-weight: bold;'>否</span>";
                    if(value==1)return "<span style='color:red;font-weight: bold;'>是</span>";
                } },
            { label: '姓名', name:'pName', width:80 },
            { label: '人员类别', name:'pCategroy', width:80},
            { label: '部门', name:'pDepartment', width:80 },
            { label: '性别', name:'pSex', width:80,formatter:function (value) {
                    if(value==1){
                        return "男";
                    } else if(value==0){
                        return "女";
                    }else{return "";}
                } },
            { label: '手机', name:'pPhone', width:80 },
            { label: '房屋配置', name:'home', width:80,formatter:function (value) {
                var showdata='';
                    if(value.length>0){
                        for(var i=0;i<value.length;i++){
                            showdata=showdata+'<p>'+value[i].gName+' * '+value[i].gdNumber+'</p>';
                        }
                        return showdata;
                    }else{
                        return '';
                    }
                }},


        ],
        viewrecords: true,
        height: 750,
        rowNum: 20,
        rowList :[20,30,50,100,500],
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
        modaltitle:'',
    },
    created:function () {
        var date=new Date();
    },
    methods:{
        //查询
        search:function(){
            $("#jqGrid").jqGrid('setGridParam',{
                postData: {
                "bgTime":$("#dbgTime").val(),
                "endTime":$("#dendTime").val()
                },
                page:1
            }).trigger("reloadGrid");
        },
        //导出所有
        exportdate:function() {
            var month=vm.month;
            layer.confirm('确定要导出全部记录？',{offset:'100px'}, function() {
                $.ajax({
                    type: "post",
                    url: "../dormdetailed/exportAll",
                    success: function (r) {
                        if (r.code == 0) {
                            layer.msg('导出中......', {offset: '100px'}, function (index) {
                                location.href = "../dormdetailed/downloadExcel";
                            });
                        } else {
                            layer.msg(r.msg, {offset: '100px'});
                        }
                    }
                });
            });
        },
        //导出选中
        selectexport:function () {
            var month=vm.month;
            var rIds = getSelectedRows();
            if(rIds == null){
                return ;
            }
            layer.confirm('确定要导出选中的记录？',{offset:'100px'}, function(){
                $.ajax({
                    type: "post",
                    url: "../dormdetailed/exportSelect",
                    data: JSON.stringify(rIds),
                    success: function(r){
                        if(r.code == 0){
                            layer.msg('导出中......',{offset:'100px'}, function(index){
                                location.href="../dormdetailed/downloadExcel";
                            });
                        }else{
                            layer.msg(r.msg,{offset:'100px'});
                        }
                    }
                });
            });
        },
        //导出查询
        classexport:function () {
            var month=vm.month;
            location.href="../dormdetailed/downloadExcel";
        },

    }
});