<template>
    <div>
        <div class='container'>
            <div class='handle-box'>
                <el-input
                    v-model='query.name'
                    placeholder='用户名'
                    class='handle-input mr10'
                ></el-input>
                <el-button
                    type='primary'
                    icon='el-icon-search'
                    @click='handleSearch'
                >搜索</el-button>
            </div>
            <el-table
                :data='tableData'
                border
                class='table'
                ref='multipleTable'
                header-cell-class-name='table-header'
            >
                <el-table-column
                    prop='id'
                    label='ID'
                    width='55'
                    align='center'
                ></el-table-column>
                <el-table-column
                    prop='nickname'
                    label='微信昵称'
                    width='200'
                    align='center'
                ></el-table-column>
                <el-table-column
                    prop='wechatCode'
                    label='微信号'
                    width='150'
                    align='center'
                ></el-table-column>
                <el-table-column
                    prop='mobile'
                    label='电话号码'
                    width='150'
                    align='center'
                ></el-table-column>
                <el-table-column
                    label='性别'
                    align='center'
                    width='100'
                >
                    <template slot-scope='scope'>
                        <el-tag
                            :type="scope.row.sex==='男'?'success':(scope.row.sex==='女'?'danger':'')"
                        >{{ scope.row.sex }}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column
                    label='账户余额'
                    align='center'
                    width='150'
                >
                    <template slot-scope='scope'>￥{{ scope.row.money }}</template>
                </el-table-column>
                <el-table-column
                    prop='credit'
                    label='积分'
                    width='150'
                    align='center'
                ></el-table-column>
                <el-table-column
                    prop='purchaseAmount'
                    label='成交额'
                    width='150'
                    align='center'
                ></el-table-column>
                <el-table-column
                    prop='createTime'
                    label='注册时间'
                    align='center'
                ></el-table-column>
            </el-table>
            <div class='pagination'>
                <el-pagination
                    background
                    layout='total, prev, pager, next'
                    :current-page='query.pageIndex'
                    :page-size='query.pageSize'
                    :total='pageTotal'
                    @current-change='handlePageChange'
                ></el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
import { fetchData } from '../../api/index';

export default {
    name: 'customerManage',
    data() {
        return {
            query: {
                address: '',
                name: '',
                pageIndex: 1,
                pageSize: 10
            },
            tableData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            pageTotal: 0,
            form: {},
            idx: -1,
            id: -1
        };
    },
    created() {
        this.getData();
        this.testApi();
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            fetchData(this.query).then(res => {
                this.tableData = res.list;
                this.pageTotal = res.pageTotal || 50;
            });
        },
        // 触发搜索按钮
        handleSearch() {
            this.$set(this.query, 'pageIndex', 1);
            this.getData();
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageIndex', val);
            this.getData();
        }
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}

.table {
    width: 100%;
    font-size: 14px;
}

.red {
    color: #ff0000;
}

.mr10 {
    margin-right: 10px;
}

.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
