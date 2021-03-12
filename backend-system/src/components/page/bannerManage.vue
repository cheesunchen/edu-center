<template>
    <div>
        <div class='container'>
            <div class='handle-box'>
                <el-button
                    type='primary'
                    icon='el-icon-circle-plus-outline'
                    class='handle-del mr10'
                    @click='addNewBanner'
                >新增Banner图
                </el-button>
            </div>
            <el-table
                :data='tableData'
                border
                class='table'
                ref='multipleTable'
                header-cell-class-name='table-header'
            >
                <!--                <el-table-column type='selection' width='55' align='center'></el-table-column>-->
                <el-table-column prop='id' label='ID' width='55' align='center'></el-table-column>
                <el-table-column label='图片' align='center'>
                    <template slot-scope='scope'>
                        <el-image
                            class='table-td-thumb'
                            :src='scope.row.url'
                            :preview-src-list='[scope.row.url]'
                        ></el-image>
                    </template>
                </el-table-column>
                <el-table-column label='排序' width='100' align='center'>
                    <template slot-scope='scope'>{{ scope.row.status === 1 ? scope.row.sort : '-' }}</template>
                </el-table-column>
                <el-table-column label='状态' width='100' align='center'>
                    <template slot-scope='scope'>
                        <el-tag
                            :type="scope.row.status=== 1?'success':(scope.row.status===0?'danger':'')"
                        >{{ scope.row.status === 1 ? '显示' : (scope.row.status === 0 ? '隐藏' : '') }}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column label='操作' width='250' align='center'>
                    <template slot-scope='scope'>
                        <el-button
                            type='text'
                            icon='el-icon-edit'
                            @click='handleEdit(scope.$index, scope.row)'
                        >编辑
                        </el-button>
                    </template>
                </el-table-column>
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

        <!-- 添加或修改对话框 -->
        <el-dialog title='Banner图' :visible.sync='dialogFormVisible'>
            <el-form ref='dataForm' :model='dataForm' status-icon label-position='right' label-width='100px'
                     style='width: 400px; margin-left:50px;'>
                <el-form-item label='banner图片' prop='url'>
                    <el-upload
                        class='avatar-uploader'
                        action='/api/system/uploadFile'
                        :show-file-list='false'
                        :on-success='handleAvatarSuccess'
                        :before-upload='beforeAvatarUpload'>
                        <img v-if='dataForm.url' :src='dataForm.url' class='avatar'>
                        <i v-else class='el-icon-plus avatar-uploader-icon'></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label='排序' prop='sort'>
                    <el-input v-model='dataForm.sort' />
                </el-form-item>
                <el-form-item label='是否显示' prop='enabled'>
                    <el-select v-model='dataForm.status' placeholder='请选择'>
                        <el-option :value='1' label='显示' aria-selected='true' />
                        <el-option :value='0' label='隐藏' />
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot='footer' class='dialog-footer'>
                <el-button @click='dialogFormVisible = false'>取消</el-button>
                <el-button type='primary' @click='saveEdit'>确定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
import { getBannerPage, saveBannerInfo } from '../../api/index';
import { Loading } from 'element-ui';

export default {
    name: 'bannerManage',
    data() {
        return {
            title: 'banner管理',
            query: {
                pageNum: 1,
                pageSize: 10
            },
            tableData: [],
            pageTotal: 0,


            dialogFormVisible: false,
            dataForm: {
                status: 1,
                url: ''
            },
            imageUrl: '',
            loadingInstance: {}
        };
    },
    created() {
        this.getData();
    },
    methods: {
        getData() {
            getBannerPage(this.query).then(res => {
                if (res.status === 200) {
                    this.tableData = res.data.list;
                    this.pageTotal = res.data.total;
                } else {
                    this.$message.error(`获取课程列表异常：${res.status}-${res.msg}`);
                }
            });
        },
        // 编辑操作
        handleEdit(index, row) {
            console.log(row);
            this.dataForm = JSON.parse(JSON.stringify(row));
            this.dialogFormVisible = true;
        },
        // 保存编辑
        saveEdit() {
            saveBannerInfo(this.dataForm).then(res => {
                console.log(res);
                this.query.pageNum = 1;
                this.dialogFormVisible = false;
                this.getData();
            });
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageIndex', val);
            this.getData();
        },
        addNewBanner() {
            this.dialogFormVisible = true;
            this.dataForm = {
                url: '',
                status: 1
            };
        },
        handleAvatarSuccess(res, file) {
            if (res.status && res.status === 200) {
                this.dataForm.url = res.data;
            }
            this.loadingInstance.close();
            // this.dataForm.url = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            const options = {
                text: '图片上传中'
            };
            this.loadingInstance = Loading.service(options);
            return isJPG && isLt2M;
        }
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
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
    width: 220px;
    height: 70px;
}

.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 360px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 360px;
    height: 178px;
    display: block;
}
</style>
