<template>
  <div id="app">
    <div @click="handleClick()">test</div>

    <router-view/>
  </div>
</template>

<script>
  import api from './api/config'
export default {
  name: 'App',
  methods: {
    handleClick () {
      // bookInfoService.getSearch("test")
      //http://127.0.0.1:8911/book/search?bookName=%E7%BA%A2%E9%AB%98%E7%B2%B1
      this.getAxios(api.kindle.search.author, {bookName:'红高粱'})
    }
  }
}
</script>

<style lang="less">
.size{
  width: 100%;
  height: 100%;
}
html,body{
  .size;
  overflow: hidden;
  margin: 0;
  padding: 0;
}
#app {
  .size;
}
</style>
