<template>
    <div>
        <div>
            <el-input placeholder="请输入内容" v-model="searchText" class="input-with-select">
                <el-select v-model="select" slot="prepend" placeholder="SEARCH">
                    <el-option label="BOOK" value="1"></el-option>
                    <el-option label="AUTHOR" value="2"></el-option>
                </el-select>
                <el-button slot="append" @click="getSearch" icon="el-icon-search"></el-button>
            </el-input>
        </div>
        <br>
        <!--    <div class="demo-image">-->
        <!--        <span class="demonstration">{{ fit }}</span>-->
        <!--        <el-image-->
        <!--                style="width: 165px; height: 225px"-->
        <!--                :src="url"-->
        <!--                :fit="fill"></el-image>-->
        <!--    </div>-->
        <el-table
                :data="pageInfo.list"
                stripe
                style="width: 100%">
            <el-table-column prop="bookName" label="书籍"></el-table-column>
            <el-table-column prop="score" label="豆瓣评分"></el-table-column>
            <el-table-column prop="author" label="作者"></el-table-column>
        </el-table>
        <el-pagination
                background @current-change="getPage"
                layout="prev, pager, next" :current-page="pageInfo.pageNum"
                :total="pageInfo.total">
        </el-pagination>
    </div>
</template>

<script>
    import api from '@/api/config'
    //data:image/png;base64,
    export default {
        mounted() {
            this.getSearch()
        },
        data() {
            return {
                select: '',
                searchText: null,
                pageInfo: {
                    list: [],
                    pageNum: 1,
                    total: 0,
                    pageSize: 10,
                },
                searchData: {},
            }
        },
        methods: {
            getPage(pageNum) {
                this.pageInfo.pageNum = pageNum
                this.getSearch()
            },
            async getSearch() {
                this.pageInfo = await this.getAxios(api.kindle.search.book.value, {
                    pageNum: this.pageInfo.pageNum,
                    pageSize: this.pageInfo.pageSize, ...this.searchData
                })
            }
        }
    }
</script>

<style>
    .el-select {
        width: 130px;
    }

    .input-with-select .el-input-group__prepend {
        background-color: #fff;
    }

    .header-title {
        font-size: 40px;
    }

    .header-search {
        text-align: center;
    }
</style>
