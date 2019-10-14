<template>
    <div id="app">
        <el-container>
            <el-header style="text-align: left" height="100px"><Header></Header></el-header>
            <el-main><router-view/></el-main>
            <el-footer>Footer</el-footer>
        </el-container>
    </div>
</template>

<script>
    // @ is an alias to /src
    import Header from '@/components/Header.vue'

    export default {
        components: {
            Header
        }
    }
</script>
<style>
    .el-header {
        color: #333;
        height: 540px;
    }

     .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        height: 240px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        height: 810px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }
</style>
