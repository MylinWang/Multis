package com.gelin;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Aa {
    //    数据库url
    public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/test1?serverTimezone=GMT%2B8";
    //    数据库登录名
    public static final String MYSQL_UAERNAME = "root";
    //    数据库密码
    public static final String MYSQL_PASSWORD = "123456";
    //    代码 作者
    public static final String AUTHOR = "LIH";
    //    父类包名
    public static final String PARENT_PACKAGE = "com.gelin";
    //    需要生成代码的数据表名
    public static final String TABLE_NAME = "user";
    //    mapper的模板路径
    public static final String MAPPER_TEMPLATE = "/templates/mapper.java";
    //    mapper.xml模板的路径
    public static final String MAPPERXML_TEMPLATE = "/templates/mapper.xml";
    //    entity的模板路径
    public static final String ENTITY_TEMPLATE = "/templates/entity.java";


    public static void main(String[] args) {
        // 数据源配置
        FastAutoGenerator.create(MYSQL_URL, MYSQL_UAERNAME, MYSQL_PASSWORD)
                .globalConfig(builder -> {
                    builder.author(AUTHOR)        // 设置作者
                            .enableSwagger()        // 开启 swagger 模式 默认值:false
                            .disableOpenDir()       // 禁止打开输出目录 默认值:true
                            .commentDate("yyyy-MM-dd") // 注释日期
                            .dateType(DateType.ONLY_DATE)   //定义生成的实体类中日期类型 DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                            .outputDir("D:/java/ms/Multis/module" + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(PARENT_PACKAGE) // 父包模块名
                            .controller("controller")   //Controller 包名 默认值:controller
                            .entity("entity")           //Entity 包名 默认值:entity
                            .service("service")         //Service 包名 默认值:service
                            .mapper("mapper")           //Mapper 包名 默认值:mapper
//                            .other("model")
                            //.moduleName("xxx")        // 设置父包模块名 默认值:无
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:/java/ms/Multis/module" + "/src/main/resources/mapper")); // 设置mapperXml生成路径//默认存放在mapper的xml下
                })
                .injectionConfig(consumer -> {
                    Map<String, String> customFile = new HashMap<>();
                    consumer.customFile(customFile);
                })
                .strategyConfig(builder -> {
                    builder.addInclude(TABLE_NAME) // 设置需要生成的表名 可边长参数“user”, “user1”
                            //.addTablePrefix("", "") // 设置过滤表前缀
                            .serviceBuilder()//service策略配置
                            .formatServiceFileName("%sService")
                            // .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()// 实体类策略配置
                            .idType(IdType.AUTO)//主键策略
                            //.addTableFills(new Column("create_time", FieldFill.INSERT)) // 自动填充配置
                            //.addTableFills(new Property("update_time", FieldFill.INSERT_UPDATE))
                            .enableLombok() //开启lombok
                            .logicDeleteColumnName("deleted")// 说明逻辑删除是哪个字段
                            .enableTableFieldAnnotation()// 属性加上注解说明
                            .controllerBuilder() //controller 策略配置
                            .formatFileName("%sController")
                            .enableRestStyle() // 开启RestController注解
                            .mapperBuilder()// mapper策略配置
                            .enableMapperAnnotation()//@mapper注解开启
                            .formatXmlFileName("%sMapper")
                            .enableBaseColumnList()
                            .enableBaseResultMap();
                })
                .templateConfig(new Consumer<TemplateConfig.Builder>() {
                    @Override
                    public void accept(TemplateConfig.Builder builder) {
                        builder.disable(TemplateType.ENTITY,TemplateType.SERVICEIMPL)
                                .mapper(MAPPER_TEMPLATE)
                                .mapperXml(MAPPERXML_TEMPLATE)
                                .entity(ENTITY_TEMPLATE);
                    }
                })

                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                // .templateEngine(new EnhanceFreemarkerTemplateEngine())
                .execute();
    }

}
