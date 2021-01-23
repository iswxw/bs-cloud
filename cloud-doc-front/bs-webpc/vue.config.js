/*
 * @Descripttion: 
 * @version: V 1.0
 * @Author: wxw
 * @Date: 2020-04-13 16:35:05
 */
module.exports = {
  // 部署应用时的基本 URL
  //publicPath: './',
  publicPath: process.env.NODE_ENV === "production" ? "/bs-webpc/" : "/",
  // build时构建文件的目录 构建时传入 --no-clean 可关闭该行为
  // outputDir: "bs-webpc",
  // build时放置生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录
  assetsDir: "static",
  // 指定生成的 index.html 的输出路径 (相对于 outputDir)。也可以是一个绝对路径。
  indexPath: "index.html",
  // 默认在生成的静态资源文件名中包含hash以控制缓存
  filenameHashing: true,
  // devServer:{type:Object} 3个属性host,port,https
  devServer: {
    disableHostCheck: true,
    // 让浏览器 overlay 同时显示警告和错误
    overlay: {
      warnings: false,
      errors: true
    },
    port: 9002, // 端口
    open: false // 配置自动启动浏览器
    // proxy: {
    //   "/api": {
    //     target: "http://api.wxw.com",
    //     ws: true,
    //     changeOrigin: true,
    //     pathRewrite: {
    //       //"/api": ""
    //     }
    //   }
    // }
  },
  // 配置md文件解析
  configureWebpack: config => {
    config.module.rules.push({
      // 处理markdown文件
      test: /\.md$/,
      loader: 'vue-loader'
    },
    );
  }

};
