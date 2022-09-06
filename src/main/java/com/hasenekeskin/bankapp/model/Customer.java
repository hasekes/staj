package com.hasenekeskin.bankapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String tc;
    private int sifre;
    private String name;
    private String surname;
    private int musteriNo;
    private String sube;

}
