//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.spring.training.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"person"}
)
@XmlRootElement(
        name = "createPersonRequest"
)
public class CreatePersonRequest {
    @XmlElement(
            required = true
    )
    protected Person person;

    public CreatePersonRequest() {
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person value) {
        this.person = value;
    }
}
