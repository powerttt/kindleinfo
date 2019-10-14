module.exports = {
    dev:{
        proxyTable: {
            '/api': {  //使用"/api"来代替"http://v.juhe.cn/toutiao/index"
                target: 'http://127.0.0.1:8911',
                changeOrigin: true, //改变源
                pathRewrite: {
                    '^/api': '' //路径重写
                }
            }
        },
    }
}
