package com.uade.marketplace.controller.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNoProducts {
    private Long id;
    private String name;
    private String lastName;
    private String email;
}
