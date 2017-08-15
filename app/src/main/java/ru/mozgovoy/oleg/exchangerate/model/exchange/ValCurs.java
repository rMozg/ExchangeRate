package ru.mozgovoy.oleg.exchangerate.model.exchange;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ValCurs")
public class ValCurs {
    @Attribute(name = "Date")
    public String date;

    @Attribute(name = "name")
    public String name;

    @ElementList(inline = true, name = "Valute ")
    public List<Valute> valuteList;
}
