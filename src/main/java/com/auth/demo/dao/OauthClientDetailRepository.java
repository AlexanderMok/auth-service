package com.auth.demo.dao;

import com.auth.demo.entity.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Alexander Mok
 * @since 2019/01/29
 */
@Repository
public interface OauthClientDetailRepository
        extends PagingAndSortingRepository<OauthClientDetails, Integer>, JpaSpecificationExecutor {
    Optional<OauthClientDetails> findByClientId(String clientId);
}
