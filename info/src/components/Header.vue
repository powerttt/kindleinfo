<template>
    <div class="header">
        <span class="header-title">KINDLE INFO</span>

    </div>
</template>

<script>
    import api from '@/api/config'
    export default {
        name: 'Header',
        components: {},
        data() {
            return {
                input: '',
                select: '',
                searchText: null
            }
        },
        methods: {
            getSearch() {
                this.getAxios(api.kindle.search.book.value, {bookName:this.searchText})
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
