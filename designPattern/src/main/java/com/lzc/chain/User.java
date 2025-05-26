package com.lzc.chain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @Max(100)
    @Min(200)
    Integer age;

}
