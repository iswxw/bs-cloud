package com.wxw.cloud.tools;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Scanner;

/**
 * 代码生成器
 * 执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 */
public class CodeGenerator {

    // 基础数据微服务 路径
    public static String webPath="/cloud-web-server/cloud-bs-service/src/main/java";
    public static String userPath="/cloud-server-user/cloud-user-service/src/main/java";
    public static String orderPath="/cloud-server-order/cloud-order-service/src/main/java";
    public static String testPath="/test";
    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 1,全局配置
        GlobalConfig gc=new GlobalConfig();
        //获取程序当前路径
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
              gc.setOutputDir(projectPath+testPath)
                .setAuthor("WXW")
                .setEnableCache(false)
                .setFileOverride(true)
                .setOpen(false)
                .setIdType(IdType.AUTO)
                .setBaseResultMap(true)
                .setBaseColumnList(true);

        // 2，数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/bs-wxw?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai")
                .setUsername("root")
                .setPassword("123456");

        // 3,策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(true)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setNaming(NamingStrategy.underline_to_camel)
                .setRestControllerStyle(true)
                .setEntityLombokModel(true)
                .setTablePrefix("tb_")
                .setControllerMappingHyphenStyle(true)
                .setInclude(scanner("表名，多个英文逗号分割").split(","));

        // 4，包名策略配置  注意不同的模块生成时要修改对应模块包名
        PackageConfig pc = new PackageConfig();
        pc.setParent(null)
                .setEntity("com.wxw.cloud.com.wxw.cloud.domain")
                .setController("com.wxw.cloud.controller")
                .setMapper("com.wxw.cloud.dao")
                .setXml("com.wxw.cloud.dao.mapper")
                .setService("com.wxw.cloud.service")
                .setServiceImpl("com.wxw.cloud.service.impl");

        // 5,整合配置
        AutoGenerator mbg = new AutoGenerator();
        mbg.setGlobalConfig(gc)
                .setDataSource(dsc)
                .setStrategy(strategy)
                .setPackageInfo(pc)
                .setTemplate(null);

        // 6，执行
        mbg.execute();

    }
}
