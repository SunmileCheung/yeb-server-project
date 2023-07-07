<template>
    <div>
        <div>
            <el-input size="small" v-model="ec.ecTypeName" placeholder="添加奖惩名称..." prefix-icon="el-icon-plus"
                      style="width: 300px"></el-input>
            <el-select size="small" v-model="ec.ecType" placeholder="奖惩类别" style="margin-left: 6px;margin-right: 6px;">
                <el-option
                    v-for="item in titleLevels"
                    :key="item"
                    :label="item"
                    :value="item">
                </el-option>
            </el-select>
            <el-button size="small" type="primary" icon="el-icon-plus" @click="addJobLevel">添加</el-button>
        </div>
        <div style="margin-top: 10px;">
            <el-table
                :data="jls"
                stripe
                border
                size="small"
                @selection-change="handleSelectionChange"
                style="width: 70%">
                <el-table-column
                    type="selection"
                    width="55">
                </el-table-column>
                <el-table-column
                    prop="id"
                    label="编号"
                    width="55">
                </el-table-column>
                <el-table-column
                    prop="ecTypeName"
                    label="奖惩名称"
                    width="150">
                </el-table-column>
                <el-table-column
                    prop="ecType"
                    label="奖惩类别"
                    width="150">
                </el-table-column>
                <el-table-column
                    prop="ecDate"
                    label="创建日期"
                    width="150">
                </el-table-column>
                <el-table-column
                    prop="ecPoint"
                    label="绩效变更"
                    width="150">
                </el-table-column>
                <el-table-column
                    label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" @click="showEditView(scope.row)">编辑</el-button>
                        <el-button size="small" type="danger" @click="deleteHandler(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-button size="small" style="margin-top: 10px;" :disabled="this.multipleSelection.length==0"
                       type="danger" @click="deleteMany">批量删除</el-button>
        </div>
        <el-dialog
            title="编辑奖惩"
            :visible.sync="dialogVisible"
            width="30%">
            <table>
                <tr>
                    <td>
                        <el-tag>奖惩名称</el-tag>
                    </td>
                    <td>
                        <el-input v-model="updateEc.ecTypeName" size="small" style="margin-left: 6px"></el-input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <el-tag>奖惩类别</el-tag>
                    </td>
                    <td>
                        <el-select size="small" v-model="updateEc.ecType" placeholder="奖惩类别" style="margin-left: 6px;margin-right: 6px;">
                            <el-option
                                v-for="item in titleLevels"
                                :key="item"
                                :label="item"
                                :value="item">
                            </el-option>
                        </el-select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <el-tag>绩效变更</el-tag>
                    </td>
                    <td>
                        <el-input v-model="updateEc.ecPoint" size="small" style="margin-left: 6px"></el-input>
                    </td>
                </tr>
            </table>
            <span slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisible = false">取 消</el-button>
                <el-button size="small" type="primary" @click="doUpdate">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: "EcMana",
    data(){
        return{
            ec:{
                ecTypeName:'',
                ecType:'',
            },
            updateEc: {
                ecTypeName: '',
                ecType: '',
                ecPoint: ''
            },
            titleLevels:[
                '0',
                '1',

            ],
            jls:[],
            dialogVisible: false,
            multipleSelection: []
        }
    },//数据
    mounted(){
        this.initJls();
    },
    methods:{
        initJls(){
            this.getRequest('/system/basic/ec/').then(resp=>{
                if (resp) {
                    this.jls = resp;
                    this.ec.ecTypeName = '';
                    this.ec.ecType = '';
                }
            })
        },
        addJobLevel(){
            if (this.ec.ecTypeName && this.ec.ecType) {
                this.postRequest('/system/basic/ec/',this.ec).then(resp=>{
                    if (resp) {
                        this.initJls();
                    }
                })
            }else {
                this.$message.error('字段不能为空！');
            }
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        doUpdate(){
            this.putRequest('/system/basic/ec/',this.updateEc).then(resp=>{
                if (resp) {
                    this.initJls();
                    this.dialogVisible = false;//关闭弹出框
                }
            })
        },
        showEditView(data){
            Object.assign(this.updateEc,data);//让弹出框显示对应数据
            this.updateEc.createDate='';//创建时间设置空，不然更新会操作失败
            this.dialogVisible = true;
        },
        deleteMany(){
            this.$confirm('此操作将永久删除[' + this.multipleSelection.length + ']条奖惩, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                let ids = '?';
                this.multipleSelection.forEach(item=>{
                    ids+= 'ids='+item.id+'&';
                })
                this.deleteRequest('/system/basic/ec/' + ids).then(resp => {
                    if (resp) {
                        this.initJls();
                    }
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        deleteHandler(data){
            this.$confirm('此操作将永久删除[' + data.ecTypeName + ']奖惩, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.deleteRequest('/system/basic/ec/' + data.id).then(resp => {
                    if (resp) {
                        this.initJls();
                    }
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        }
    }
}
</script>

<style scoped>

</style>