package com.github.jmetzz.laboratory.mockito.persistence;

import com.github.jmetzz.laboratory.mockito.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by jean on 6/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerDAOTest {

    private CustomerDAO dao;

    private Customer newton, galileo, vinci;

    @Mock
    private EntityManager mockEntityManager;

    @Mock
    private TypedQuery<Customer> mockQuery;

    @Before
    public void setup() {
        dao = new CustomerDAO(mockEntityManager);
        setupCustomers();
    }


    @Test
    public void finding_existing_customer_should_return_customer() throws Exception {
        // Given
        long expectedId = 10;
        String expectedName = "John Doe";
        String expectedAddress = "21 Main Street";
        Customer expectedCustomer = new Customer(expectedId, expectedName, expectedAddress);

        when(mockEntityManager.find(Customer.class, expectedId)).thenReturn(expectedCustomer);

        // When
        Optional<Customer> actualCustomer = dao.findById(expectedId);

        // Then
        assertTrue(actualCustomer.isPresent());
        assertEquals(expectedId, actualCustomer.get().getId());
        assertEquals(expectedName, actualCustomer.get().getName());
        assertEquals(expectedAddress, actualCustomer.get().getAddress());
    }

    @Test
    public void finding_existing_customer_should_return_customer_bdd() throws Exception {
        // Given
        long expectedId = 10L;
        String expectedName = "John Doe";
        String expectedAddress = "21 Main Street";
        Customer expectedCustomer = new Customer(expectedId, expectedName, expectedAddress);

        given(mockEntityManager.find(Customer.class, expectedId)).willReturn(expectedCustomer);

        // When
        Optional<Customer> actualCustomer = dao.findById(expectedId);

        // Then
        assertTrue(actualCustomer.isPresent());
        assertEquals(expectedId, actualCustomer.get().getId());
        assertEquals(expectedName, actualCustomer.get().getName());
        assertEquals(expectedAddress, actualCustomer.get().getAddress());
    }

    @Test
    public void invoking_mock_with_unexpected_argument_returns_null() throws Exception {
        // Given
        long expectedId = 10L;
        long unexpectedId = 20L;
        String expectedName = "John Doe";
        String expectedAddress = "21 Main Street";
        Customer expectedCustomer = new Customer(expectedId, expectedName, expectedAddress);

        when(mockEntityManager.find(Customer.class, expectedId)).thenReturn(expectedCustomer);

        // When
        Optional<Customer> actualCustomer = dao.findById(unexpectedId);

        // Then
        assertFalse(actualCustomer.isPresent());
    }

    @Test
    public void invoking_mock_with_chained_stubs_returns_different_customers() throws Exception {
        // Given
        long expectedId1 = 10L;
        String expectedName1 = "John Doe";
        String expectedAddress1 = "21 Main Street";
        Customer expectedCustomer1 = new Customer(expectedId1, expectedName1, expectedAddress1);

        long expectedId2 = 20L;
        String expectedName2 = "Jane Deer";
        String expectedAddress2 = "46 High Street";
        Customer expectedCustomer2 = new Customer(expectedId2, expectedName2, expectedAddress2);

        when(mockEntityManager.find(Customer.class, expectedId1))
                .thenReturn(expectedCustomer1)
                .thenReturn(expectedCustomer2);

        // Mockito allows you to use VarArgs in thenReturn to stub consecutive calls, such as
        // when(mockEntityManager.find(Customer.class, expectedId)).thenReturn(expectedCustomer1, expectedCustomer2);
        // which is equivalent to the previews stub configuration

        // When
        Optional<Customer> actualCustomer1 = dao.findById(expectedId1);
        Optional<Customer> actualCustomer2 = dao.findById(expectedId1);
        Optional<Customer> actualCustomer3 = dao.findById(expectedId2);

        // Then
        assertEquals(expectedName1, actualCustomer1.get().getName());
        assertEquals(expectedName2, actualCustomer2.get().getName());
        assertFalse(actualCustomer3.isPresent());
    }

    @Test
    public void finding_customer_by_id_returns_appropriate_customer() throws Exception {
        // Given
        long[] expectedId = {1, 2, 3};
        long unexpectedId = 5;

        /*
        * Answer is a Functional Interface, having only one method.
        * Thus, we can use Java 8 lambda expression to represent our Answer
        * */
        when(mockEntityManager.find(eq(Customer.class), anyLong()))
                .thenAnswer(i -> getCustomerById(i));

        // When
        Optional<Customer> actualCustomer0 = dao.findById(expectedId[0]);
        Optional<Customer> actualCustomer1 = dao.findById(expectedId[1]);
        Optional<Customer> actualCustomer2 = dao.findById(expectedId[2]);
        Optional<Customer> actualCustomer3 = dao.findById(unexpectedId);

        // Then
        assertEquals("Isaac Newton", actualCustomer0.get().getName());
        assertEquals("Galileo Galilei", actualCustomer1.get().getName());
        assertEquals("Leonardo Da Vinci", actualCustomer2.get().getName());
        assertFalse(actualCustomer3.isPresent());
    }


    @Test
    public void finding_all_customers_should_return_all_customers() throws Exception {
        // Given
        given(mockQuery.getResultList())
                .willAnswer(i -> Arrays.asList(newton, galileo, vinci));

        given(mockEntityManager.createQuery(anyString(), eq(Customer.class)))
                .willReturn(mockQuery);

        // When
        List<Customer> actualCustomers = dao.findAll();

        // Then
        assertEquals(actualCustomers.size(), 3);
        assertTrue(actualCustomers.containsAll(Arrays.asList(newton, galileo, vinci)));
    }

    private Customer getCustomerById(InvocationOnMock invocation){
        int id =  ((Long) invocation.getArguments()[1]).intValue();
        switch (id){
            case 1: return newton;
            case 2: return galileo;
            case 3: return vinci;
            default: return null;
        }
    }
    private void setupCustomers() {
        newton = new Customer(1, "Isaac Newton", "Somewhere close to an apple tree");
        galileo = new Customer(1, "Galileo Galilei", "Moon");
        vinci = new Customer(1, "Leonardo Da Vinci", "Some church");
    }


}