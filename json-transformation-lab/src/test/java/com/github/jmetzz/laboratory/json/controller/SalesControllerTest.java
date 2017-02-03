package com.github.jmetzz.laboratory.json.controller;


import com.github.jmetzz.laboratory.json.OrderLineMetadataCollector;
import com.github.jmetzz.laboratory.json.OrderMetadataCollector;
import com.github.jmetzz.laboratory.json.service.remote.OrderAPI;
import com.github.jmetzz.laboratory.json_processing.jackson.JsonUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.github.jmetzz.laboratory.json.controller.CustomAssertions.assertThat;
import static com.github.jmetzz.laboratory.json.controller.SalesController.COPY_ORDER_URL;
import static com.github.jmetzz.laboratory.json.controller.SalesController.GET_ORDER_LINE_URL;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

/**
 * Created by jean on 3/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SalesControllerTest extends AbstractControllerTest {


    private static final String ORDER_LINE_REF = "XXX";
    private static final String ORDER_REF = "YYY";
    @InjectMocks
    public SalesController controller;

    @Mock
    private OrderAPI orderApi;

    @Mock
    private OrderMetadataCollector orderMetaDataCollector;

    @Mock
    private OrderLineMetadataCollector orderLineMetaDataCollector;


    @Test
    public void copyOrder() throws Exception {


        String cloneId = "copy-order-id";

        // TODO adapt the json files (WIP - they are not correct yet and that is the reason for the @Ignore)
        when(orderApi.createOrder(JsonUtils.fromFile("/sales/copyOrder.json"))).thenReturn(cloneId);
        when(orderApi.addOrderLine(cloneId, JsonUtils.fromFile("/sales/copyOrderline.json"))).thenReturn(ORDER_LINE_REF);

        assertThat(mockMvc.perform(post(COPY_ORDER_URL, ORDER_REF)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", "/sales/order/" + cloneId)));

        assertThat(mockMvc.perform(get(GET_ORDER_LINE_URL, cloneId, ORDER_LINE_REF)))
                .matches("getOrderLine-200.json");

    }

}