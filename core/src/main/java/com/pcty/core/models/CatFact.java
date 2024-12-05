package com.pcty.core.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.adobe.cq.wcm.core.components.util.ComponentUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {
    SlingHttpServletRequest.class
}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = "pcty/components/cat-fact")
public class CatFact {

    @Self
    @Required
    private SlingHttpServletRequest request;

    @ValueMapValue
    private String fact;
    @ValueMapValue
    private Calendar factDate;
    @ValueMapValue
    private String id;

    public String getFact() {
        return fact;
    }

    public String getFactDate() {
        if (factDate == null) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(factDate.getTime());
    }

    public String getId() {
        return id == null || id.trim().isEmpty() ? ComponentUtils.generateId("cmp-cat-fact_", request.getResource().getPath()) : id;
    }
}
