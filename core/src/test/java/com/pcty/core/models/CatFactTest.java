package com.pcty.core.models;

import com.day.cq.wcm.api.Page;
import io.wcm.testing.mock.aem.junit5.AemContext;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.*;

@ExtendWith(io.wcm.testing.mock.aem.junit5.AemContextExtension.class)
public class CatFactTest {
    private static CatFact configured;
    private static CatFact unconfigured;

    private static final String fact = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
    private static final Calendar factDate = Calendar.getInstance();
    private static final String id = "id_1234";

    @BeforeAll
    public static void beforeAll(AemContext aemContext) throws Exception {
        Page page = aemContext.create().page("/content/pcty/us/en");

        Resource unconfiguredResource = aemContext.create().resource(page, "unconfigured");
        aemContext.currentResource(unconfiguredResource);
        unconfigured = aemContext.request().adaptTo(CatFact.class);
        assertNotNull(unconfigured);

        factDate.set(2024, Calendar.JULY, 4, 0, 0, 0);
        Map<String, Object> properties = new HashMap<>();
        properties.put("fact", fact);
        properties.put("factDate", factDate);
        properties.put("id", id);
        Resource configuredResource = aemContext.create().resource(page, "configured", properties);
        aemContext.currentResource(configuredResource);
        configured = aemContext.request().adaptTo(CatFact.class);
        assertNotNull(configured);
    }

    @Test
    void testGetFact() {
        assertEquals(fact, configured.getFact());
        assertNull(unconfigured.getFact());
    }

    @Test
    void testGetFactDate() {
        assertEquals("07/04/2024", configured.getFactDate());
        assertNull(unconfigured.getFactDate());
    }

    @Test
    void testGetId() {
        assertEquals(id, configured.getId());
        assertNotNull(unconfigured.getId());
    }
}
