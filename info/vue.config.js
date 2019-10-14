
module.exports = {
  // 设为false打包时不生成.map文件
  productionSourceMap: false,
  // 这里写你调用接口的基础路径，来解决跨域，如果设置了代理，那你本地开发环境的axios的baseUrl要写为 '' ，即空字符串
  // devServer: {
  //   proxy: 'localhost:3000'
  // }
  devServer: {
    historyApiFallback: true,
    hot: true,
    inline: true,
    stats: { colors: true },
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8843',
        angeOrigin: true,
        changeOrigin: true,
        pathRewrite: {  '^/api/': '/' }
      }
    }
  }
}
