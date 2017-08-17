package ru.mozgovoy.oleg.exchangerate.model.exchange;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ValCurs")
public class ValCurs {
    private String date;
    private String name;
    private List<Valute> valuteList;

    @Attribute(name = "Date")
    public String getDate() {
        return date;
    }

    @Attribute(name = "Date")
    public void setDate(String date) {
        this.date = date;
    }

    @Attribute(name = "name")
    public String getName() {
        return name;
    }

    @Attribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @ElementList(inline = true, name = "Valute")
    public List<Valute> getValuteList() {
        return valuteList;
    }

    @ElementList(inline = true, name = "Valute")
    public void setValuteList(List<Valute> valuteList) {
        this.valuteList = valuteList;
    }
}
