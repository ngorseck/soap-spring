//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.spring.training.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"countries"}
)
@XmlRootElement(
        name = "getCountriesResponse"
)
public class GetCountriesResponse {
    @XmlElement(
            required = true
    )
    protected List<Country> countries;

    public GetCountriesResponse() {
    }

    public List<Country> getCountries() {
        if (this.countries == null) {
            this.countries = new ArrayList();
        }

        return this.countries;
    }
}
