package com.enset.ebankservice.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Customer {
    private Long id;
    private String name;
    private String email;
}
