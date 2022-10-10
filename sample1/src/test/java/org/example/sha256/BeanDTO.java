package org.example.sha256;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

@Data
public class BeanDTO {

    @Alias("data")
    private String data;
}
