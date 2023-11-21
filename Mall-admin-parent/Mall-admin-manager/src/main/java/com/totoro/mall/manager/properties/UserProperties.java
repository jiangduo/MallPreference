package com.totoro.mall.manager.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "mall.auth")
//需要在启动类上配置，使其生效
public class UserProperties {
    private List<String> noAuthUrls;
}
