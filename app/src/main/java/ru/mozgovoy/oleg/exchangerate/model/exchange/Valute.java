package ru.mozgovoy.oleg.exchangerate.model.exchange;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

@Root(name = "Valute")
public class Valute {
    @Attribute(name = "ID")
    public int id;

    @Element(name = "NumCode")
    public int numCode;

    @Element(name = "CharCode")
    public int charCode;

    @Element(name = "Nominal")
    public int nominal;

    @Element(name = "Name")
    public String name;

    @Element(name = "Value")
    public BigDecimal value;
}
