package vip.zhguo.xier.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * [一句话描述该类的功能]
 *
 * @author : [zhenghg]
 * @version : [v1.0]
 * @createTime : [2023/2/10 15:01]
 */
public class GeneratorTool {
    public static void main(String[] args) {
        String url ="jdbc:mysql://localhost:3306/xier?serverTimezone=GMT%2B8&autoReconnect=true&useSSL=false";
        String username="root";
        String password="aabbcc570";
        FastAutoGenerator.create(url ,username, password)
                .globalConfig(builder -> {
                    builder.author("zhenghg") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride()// 覆盖已生成文件
                            .outputDir("D:\\IdeaProjects\\xier\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("vip.zhguo.xier") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\IdeaProjects\\xier\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_reply_wa_message") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
