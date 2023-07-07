<template>
    <div>
        <div>
            <el-menu
                    :default-active="activeIndex2"
                    class="el-menu-demo"
                    mode="horizontal"
                    @select="handleSelect"
                    background-color="#909399"
                    text-color="#fff"
                    size="small"
                    style="margin-top: 15px;width:600px"
                    active-text-color="#ffd04b">
                <el-menu-item index="1">基础信息展示</el-menu-item>
                <el-submenu index="2">
                    <template slot="title">奖惩列表筛选</template>
                    <el-menu-item index="2-1">奖励</el-menu-item>
                    <el-menu-item index="2-2">处罚</el-menu-item>
                    <!--      <el-submenu index="2-4">
                            <template slot="title">选项4</template>
                            <el-menu-item index="2-4-1">选项1</el-menu-item>
                            <el-menu-item index="2-4-2">选项2</el-menu-item>
                            <el-menu-item index="2-4-3">选项3</el-menu-item>
                          </el-submenu>-->
                </el-submenu>
                <el-menu-item index="3">消息中心</el-menu-item>
                <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
            </el-menu>
        </div>
        <div>
            <el-input v-model="ename" placeholder="请输入员工姓名" style="width: 300px;margin-right: 10px">
            </el-input>
            <el-button type="primary" icon="el-icon-refresh" @click="searchEmpEcInfos"
                       style="margin-top: 10px;margin-bottom: 10px">奖惩信息
            </el-button>
        </div>
        <div v-if="selectIndex == 1">
            <el-table
                    :data="tableData"
                    style="width: 100%"
                    border
                    :cell-style="tableRowClassName">
                //:row-class-name="tableRowClassName(this.ecTypeArray)">
                <el-table-column
                        prop="eid"
                        label="员工编号"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="ename"
                        label="员工姓名"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="ecReason"
                        label="奖惩原因"
                        width="220">
                </el-table-column>
                <el-table-column
                        prop="ecPoint"
                        label="奖罚分"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="ecDate"
                        label="奖惩日期"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="ecType"
                        label="奖惩类型"
                        :filters="[{ text: '0', value: '奖励' }, { text: '1', value: '处罚' }]"
                        width="220px">
                    <template slot-scope="scope">
                        <el-tag :type="scope.row.ecType !== 0 ? 'warning':'success' " disable-transitions>{{scope.row.ecType !== 0 ? '处罚':'奖励'}}
                        </el-tag>
                    </template>
                </el-table-column>
            </el-table>
            <!--  分页    -->
            <div style="display: flex;justify-content: flex-end;margin-top: 10px">
                <span class="demonstration">请选择要跳往的页面</span>
                <el-pagination
                        @size-change="sizeChange"
                        @current-change="currentChange"
                        :page-size=this.pageSize
                        layout="sizes, prev, pager, next, jumper, ->, total"
                        :total=this.total>
                </el-pagination>
            </div>
        </div>
        <div v-if="selectIndex.startsWith('2')">
            <el-table
                    :data="tableData"
                    v-model="searchEcType"
                    stripe
                    style="width: 100%">
                <el-table-column
                        prop="id"
                        label="奖惩信息编号"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="eid"
                        label="员工编号"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="ename"
                        label="员工姓名"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="ecReason"
                        v-if="searchEcType==0"
                        label="奖励原因"
                        width="220">
                </el-table-column>
                <el-table-column
                        prop="ecReason"
                        v-if="searchEcType==1"
                        label="处罚原因"
                        width="220">
                </el-table-column>
                <el-table-column
                        prop="ecPoint"
                        label="奖罚分"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="ecDate"
                        label="奖惩日期"
                        width="180">
                </el-table-column>
                <el-table-column
                        label="操作"
                        width="200">
                    <template slot-scope="scope">
                        <el-button v-if="searchEcType == 0" @click="canCalWarning(scope.row)" type="text" size="small">取消奖励</el-button>
                        <el-button v-if="searchEcType == 1" type="text" size="small" @click="canCalWarning(scope.row)">取消惩罚</el-button>
                        <el-button type="primary" @click="updateEcCode(scope.row)" size="small">修改奖罚分</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!--  分页    -->
            <div style="display: flex;justify-content: flex-end;margin-top: 10px">
                <span class="demonstration">请选择要跳转的页码</span>
                <el-pagination
                        @size-change="sizeChange"
                        @current-change="currentChange"
                        :current-page=this.currentPage
                        :page-size=this.pageSize
                        layout="sizes, prev, pager, next, jumper, ->, total"
                        :total=this.total>
                </el-pagination>
            </div>
        </div>

    </div>


</template>

<script>
export default {
    name: "perEc",
    data() {
        return {
            activeIndex: '1',
            activeIndex2: '1',
            selectIndex: '1',
            tableData: [],
            ename: '',
            total: 0,
            currentPage: 1,
            pageSize: 10,
            ecTypeArray: [],
            ecType: 0,
            searchEcType: -1,

        };
    },
    computed: {
        getResult(ecType) {
            if (ecType === 0) {
                return true;
            } else if (ecType === 1) {
                return false;
            }
        }
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key);
            if (key === '1') {
                this.searchEcType = undefined;
            }
            if (key === "2-1") {
                this.searchEcType = 0;
            }
            if (key === "2-2") {
                this.searchEcType = 1;
            }
            this.selectIndex = key;
            this.initEmpEcList();
        },
        tableRowClassName({row}) {
            //console.log("此处ecType的值为"+this.ecType)
            if (row.ecType === 0) {
                //this.ecType = 1;
                console.log("此处应为enType1")
                return 'background-color: #f0f9eb';
            } else if (row.ecType === 1) {
                //this.ecType = 0;
                console.log("此处应为enType0")
                return 'background-color: oldlace';
            }
            return '';
        },

        initEmpEcList() {
            let url = "/employee-ec/page?currentPage=" + this.currentPage + "&pageSize=" + this.pageSize
            if (null != this.ename && this.ename != undefined && this.ename.trim() != '') {
                url += ("&ename=" + this.ename);
            }
            if (this.searchEcType == 0) {
                url += ("&ecType=0");
            } else if (this.searchEcType == 1) {
                url += ("&ecType=1");
            }
            this.getRequest(url).then(resp => {
                if (resp) {
                    console.log(resp);
                    //console.log(resp.data.ecType)
                    //this.ecType = resp.data.ecType;
                    this.total = resp.total;
                    this.tableData = resp.data;
                    this.tableData.forEach((eec => {
                        // console.log(this.ecType)
                        this.ecType = eec.ecType;
                        //this.ecTypeArra.push(eec.ecType);
                        console.log(this.ecType);
                        //this.tableRowClassName(eec.ecType);
                    }));
                    /* this.ecTypeArray = resp.data.map((item,index) =>{
                        console.log(item);
                        console.log(this.ecTypeArray);
                        return Object.assign({},{'ecType':item.ecType})
                      });*/

                }
            });
        },
        searchEmpEcInfos() {
            this.initEmpEcList();
        },
        sizeChange(size) {
            this.size = size;
            this.initEmpEcList();
        },
        currentChange(currentPage) {
            this.currentPage = currentPage;
            this.initEmpEcList();
        },
        tableRowColor(row) {
            return "{'background':row.ecType === 0 ? '#f0f9eb' : 'oldlace'}"
        },

        /*取消奖罚*/
        cancalReward(data) {
            console.log(data);
            //this.canCalWarning(data);
            this.deleteRequest("/employee-ec/delete?id=" + data.id).then(resp => {
                if (resp) {
                    if (resp == 200) {
                        this.$message({
                            type: 'info',
                            message: `action: ${resp.message}`
                        });

                    } else {
                        this.$message({
                            type: 'error',
                            message: `action: ${resp.message}`
                        });
                    }
                } else {
                    this.$message({
                        type: 'error',
                        message: `action: 没有信息返回`
                    });
                }
                this.initEmpEcList();
            });
        },
        /*修改奖惩分*/
        updateEcCode(data) {
            console.log(data);
            this.putRequest("/employee-ec/update", data).then(resp => {
                if (resp) {
                    if (resp.code == 200) {

                    }
                }
            })
        },

        /*警告框*/
        canCalWarning(data) {
            this.$confirm('此操作将删除对' + data.ename + '的奖惩信息, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.cancalReward(data)
                this.$message({
                    type: 'success',
                    message: '删除奖惩成功!'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消操作'
                });
            });
        },
    },
    mounted() {
        this.initEmpEcList();
    }
}
</script>

<style>

.el-table .warning-row {
    background: oldlace;
}

.el-table .success-row {
    background: #f0f9eb;
}

</style>