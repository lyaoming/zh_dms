let costId=[];

function intiMain(url) {
    let mydata={page:1,total:0,records:0,rows:[]};
    $.getJSON(url, function (r) {
        mydata.records=r.expenseList.length;
        if(r.expenseList!=null&&r.expenseList.length>0) {
            for (var i = 0; i < r.expenseList.length; i++) {
                let reportObject = {};
                $.each(r.expenseList[i], function (key, value) {
                    if (key != "pId") {//不要PID
                        reportObject[key] = value;
                    }
                });
                var cost = r.expenseList[i].costs;
                for (var j = 0; j < cost.length; j++) {
                    reportObject[cost[j].CId] = cost[j].value;
                }
               mydata.rows.push(reportObject);
            }
            mydata.total = (mydata.rows.length % 20 == 0) ? (mydata.rows.length / 20) : (Math.floor(mydata.rows.length / 20) + 1);
        }
        var grid=$("#jqGrid").jqGrid({
            datatype: "local",
            colNames: vm.names,
            colModel: vm.model,
            viewrecords: true,
            height: 750,
            rowNum:20,
            rowList:[20,30,50,100,500],
            rownumWidth: 25,
            autowidth: true,
            multiselect: true,
            sortname: '0',
            gridview: true,
            pager: "#jqGridPager",
        });
        var reader = {
            root: function(obj) { return mydata.rows; },
            page: function(obj) { return mydata.page; },
            total: function(obj) { return mydata.total; },
            records: function(obj) { return mydata.records; },
            repeatitems: false
        }
        grid.setGridParam({data: mydata.rows, reader: reader}).trigger('reloadGrid',{ fromServer: true, page: 1 });
    });
}

var vm = new Vue({
    el:'#rrapp',
    data:{
        model:[{label: 'recordId', name: 'recordId', width: 50, key: true, hidden: true}
            , {label: '姓名', name: 'pName', width: 80}
            , {label: '入住房屋', name: 'dorm', width: 80}
            , {label: '人员类别', name: 'pCategroy', width: 80}
            , {label: '部室', name: 'pDepartment', width: 80}
            , {label: '年份', name: 'recordYear', width: 80}
            , {label: '月份', name: 'recordMonth', width: 80}],
        names:['pdId', '人员名字', '入住房屋', '人员类型', '部室','年份','月份'],
        modaltitle:'',
        pDepartment:'',
        pCategroy:'',
        searchTime:null,
        searchyear:'',
        searchmonth:'',
        department:{},
        month:{},
        classion:{},
    },
    created:function () {
        $.get("../department/list",function (r) {
            vm.department=r.departmentList;
        });
        $.get("../month/list",function (r) {
           vm.month=r.monthList;
        });
        $.get("../update/list",function (r) {

        });
        $.get("../classion/list",function (r) {
            vm.classion=r.classionList;
        })
        this.getCostList();
    },
    methods:{
        getCostList:function(){
            $.getJSON("../cost/list", function (r) {
                if(r.costList!==null&&r.costList.length>0) {
                    for (var i = 0; i < r.costList.length; i++) {
                        costId.push(r.costList[i].cId);
                        vm.names.push(r.costList[i].cName);
                        vm.model.push({label: r.costList[i].cName, name: r.costList[i].cId, width: 100})
                    }
                    vm.names.push('总额');
                    vm.model.push({
                        label: '总额', name: 'costs', width: 80, formatter: function (value) {
                            if (value !==null && value.length > 0) {
                                var sum =0;
                                for (var j=0; j<value.length;j++) {
                                    for(var k=0;k<costId.length;k++){
                                       if(value[j].CId===costId[k]) {
                                           sum = sum + value[j].value;
                                       }
                                    }
                                }
                                return sum;
                            } else {
                                return 0;
                            }
                        }
                    });
                }
                var url="../expensetable/list?_search";
                intiMain(url);
            });
        },
        search:function(){
            //清空表格重新加载
          $("#jqGrid").jqGrid("clearGridData");
          for(var i=0;i<vm.month.length;i++){
              if(vm.month[i].mId===vm.searchTime){
                  vm.searchmonth=vm.month[i].mName;
                  vm.searchyear=vm.month[i].yName;
              }else if(vm.searchTime){
                  vm.searchmonth='';
                  vm.searchyear='';
              }
            }
          //把要搜索的连接加到请求后
          var url="../expensetable/list?_search&pDepartment="+vm.pDepartment+"&pCategroy="+vm.pCategroy+"&year="+vm.searchyear+"&month="+vm.searchmonth;
          //调用表格初始化函数
          intiMain(url);
        },
        searchDpm:function(index){
            var url="../expensetable/list?_search&pDepartment="+vm.pDepartment+"&pCategroy="+vm.pCategroy+"&year="+vm.searchyear+"&month="+vm.searchmonth;
            vm. pDepartment=vm.department[index].dpmName;
        },
        //全部导出
        exportdate:function() {
            layer.confirm('确定要导出全部记录？',{offset:'100px'}, function() {
                $.ajax({
                    type: "post",
                    url: "../expensetable/exportAll?_search"+"&year="+vm.searchyear+"&month="+vm.searchmonth,
                    success: function (r) {
                        if (r.code == 0) {
                            layer.msg('导出成功,请等候Excel下载中......', {offset: '100px'}, function (index) {
                                location.href = "../expensetable/downloadExcel";
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
            var pdIds = getSelectedRows();
            if(pdIds == null){
                return ;
            }
            layer.confirm('确定要导出选中的记录？',{offset:'100px'}, function(){
                $.ajax({
                    type: "post",
                    url: "../expensetable/exportSelect",
                    data: JSON.stringify(pdIds),
                    success: function(r){
                        if(r.code == 0){
                            layer.msg('导出成功,请等候Excel下载中......',{offset:'100px'}, function(index){
                                location.href="../expensetable/downloadExcel";
                            });
                        }else{
                            layer.msg(r.msg,{offset:'100px'});
                        }
                    }
                });
            });
        },
        //分类导出（导出查询）
        classexport:function () {
            location.href="../expensetable/downloadExcel";
        }
    }
});
function getSelectedRowName1() {
    //获取部门名字
    var grid = $("#djqGrid").getGridParam("selrow");
    var rowdpmName = $("#djqGrid").getCell(grid,"dpmName");
    //限制多选
    var selecteddpmName = $("#djqGrid").getGridParam("selarrrow");
    if(selecteddpmName.length > 1){
        return ;
    }
    return rowdpmName;
}