package com.github.jmetzz.laboratory.json.controller;

import com.github.jmetzz.laboratory.json.service.remote.CompanyAPI;
import com.github.jmetzz.laboratory.json.service.remote.OrderAPI;
import com.github.jmetzz.laboratory.json.service.remote.ProductAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.IOException;

import static com.github.jmetzz.laboratory.json_processing.jackson.JsonUtils.fromFile;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jean on 3/01/2017.
 */
@Configuration
public class RemoteTestConfig {

    private static final String COMPANY_ID = "XXX";
    private static final String CUSTOMER_ID = "YYYY";
    private static final String ADDRESS_ID = "1";
    private static final String ORDER_REF = "YYYY";
    private static final String ORDER_LINE_REF = "ZZZZ";
    private static final String PRODUCT_ID = "DFS";

    @Bean
    public CompanyAPI companyApi() throws IOException {
        CompanyAPI companyApi = mock(CompanyAPI.class);
        //TODO configure the mock
        when(companyApi.getDeliveryPreferences(matches(ADDRESS_ID),matches(COMPANY_ID), matches(CUSTOMER_ID)))
                .thenReturn(new AsyncResult<>(fromFile("/company/listBillingAddresses.json")));
        return companyApi;
    }

    @Bean
    public OrderAPI orderApi() throws IOException {
        OrderAPI orderApi = mock(OrderAPI.class);
        when(orderApi.getOrderById(ORDER_REF)).thenReturn(fromFile("/sales/getOrder.json"));
        when(orderApi.getOrderLineById(ORDER_REF, ORDER_LINE_REF)).thenReturn(fromFile("/sales/getOrderLine.json"));
        when(orderApi.getOrderLinesById(ORDER_REF)).thenReturn(fromFile("/sales/getOrderLines.json"));
        when(orderApi.createOrder(fromFile("/sales/createOrder.json"))).thenReturn(ORDER_REF);
        when(orderApi.modifyOrderLine(ORDER_REF, ORDER_LINE_REF, fromFile("/sales/modifyOrderLine.json"))).thenReturn(ORDER_LINE_REF);
        when(orderApi.addOrderLine(ORDER_REF, fromFile("/sales/addOrderLine.json"))).thenReturn(ORDER_LINE_REF);
        return orderApi;
    }

    @Bean
    public ProductAPI productApi() throws IOException {
        ProductAPI productApi = mock(ProductAPI.class);
        when(productApi.getPacking(PRODUCT_ID)).thenReturn(new AsyncResult<>(fromFile("/product/listPackagings.json")));
//        when(productApi.getProduct(PRODUCT_ID)).thenReturn(new AsyncResult<>(fromFile("/product.json")));
        return productApi;
    }

}
