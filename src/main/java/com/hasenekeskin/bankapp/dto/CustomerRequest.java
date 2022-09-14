package com.hasenekeskin.bankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {

    @NotEmpty
    @Size(min=11,max=11)
    private String tc;
    @NotEmpty
    @Size(min=8,max=16)
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    private String customerNo;
    private String department;
    private BigDecimal balance;
}
