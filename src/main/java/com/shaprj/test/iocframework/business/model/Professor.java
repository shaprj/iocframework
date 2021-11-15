package com.shaprj.test.iocframework.business.model;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Professor {
    private String name;
}
