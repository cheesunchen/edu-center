module.exports = {
    baseUrl: './',
    assetsDir: 'static',
    productionSourceMap: false,
    devServer: {
        proxy: {
            '/api':{
                // target:'http://www.cheesun.com:8088',
                // target:'http://www.bangmom.top:8088',
                target:'http://localhost:8088',
                changeOrigin:true,
                pathRewrite:{
                    '/api':''
                }
            }
        },
        disableHostCheck: true
    }
}