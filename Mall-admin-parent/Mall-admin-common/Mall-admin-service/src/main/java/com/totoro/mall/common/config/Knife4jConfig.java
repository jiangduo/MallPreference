package com.totoro.mall.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//配置类,,注意这个类并没有被springboot扫描到（默认启动类所在包及其子包，但是这里配置类的包不同，造成这里没有生效
// ）
public class Knife4jConfig {
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("admin-api")//分组名称，为接口分个组
                .pathsToMatch("/admin/**")//接口请求规则，满足这个规则的接口的路径进行显示
                .build();
    }

    /***
     * @description 自定义接口文档中显示的信息
     */
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()//信息
                .info(new Info()
                        .title("尚品甑选API接口文档")
                        .version("1.0")
                        .description("尚品甑选API接口文档")
                        .contact(new Contact().name("atguigu"))); // 设定作者
    }
}
