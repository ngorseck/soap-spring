//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.spring.training.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
        name = "Currency"
)
@XmlEnum
public enum Currency {
    GBP,
    EUR,
    PLN,
    XOF;

    private Currency() {
    }

    public String value() {
        return this.name();
    }

    public static Currency fromValue(String v) {
        return valueOf(v);
    }
}
