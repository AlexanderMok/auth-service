package com.auth.demo.dao;

import com.auth.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alexander Mok
 * @since 2019/01/29
 */
@Repository
public interface CustomerRepository
        extends PagingAndSortingRepository<Customer, Integer>, JpaSpecificationExecutor {
}
