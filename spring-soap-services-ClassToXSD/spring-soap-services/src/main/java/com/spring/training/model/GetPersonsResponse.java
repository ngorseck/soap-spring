//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.spring.training.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"persons"}
)
@XmlRootElement(
        name = "getPersonsResponse"
)
public class GetPersonsResponse {
    protected List<Person> persons;

    public GetPersonsResponse() {
    }

    public List<Person> getPersons() {
        if (this.persons == null) {
            this.persons = new ArrayList();
        }

        return this.persons;
    }
}
