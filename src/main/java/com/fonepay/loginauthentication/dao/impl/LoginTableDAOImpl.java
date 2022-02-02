package com.fonepay.loginauthentication.dao.impl;

import com.fonepay.loginauthentication.dao.LoginTableDAO;
import com.fonepay.loginauthentication.dto.GetDataDTO;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginTableDAOImpl implements LoginTableDAO {
    @PersistenceContext()
    private EntityManager em;

    @Override
    public List<GetDataDTO> searchDetail(Map<String, String> allRequestParams, Pageable pageable) {
        try {
            String queryString = "SELECT"
                    + " lt.login_id as id,"
                    + " lt.login_username as userName,"
                    + " lt.login_emailid as email,"
                    + " lt.created_by as createdBy,"
                    + " lt.status as status"
                    + " FROM login_table lt";
            queryString = getSearchCriteriaQuery(allRequestParams, queryString);
            queryString += " ORDER BY lt.login_id ASC";
            Query sqlQuery = this.em.createNativeQuery(queryString);
            if (pageable != null) {
                sqlQuery.setFirstResult((pageable.getPageNumber()) * pageable.getPageSize());
                sqlQuery.setMaxResults(pageable.getPageSize());
            }
            setQueryParameters(sqlQuery, allRequestParams);
            NativeQuery nativeQuery = sqlQuery.unwrap(NativeQuery.class);
            Query query = nativeQuery.addScalar("id", LongType.INSTANCE)
                    .addScalar("userName", StringType.INSTANCE)
                    .addScalar("email", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("status", BooleanType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(GetDataDTO.class));
            List<GetDataDTO> getDataDTOS = query.getResultList();
            return getDataDTOS;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Long getPaymentClientAppUserDetailsCount(Map<String, String> allRequestParams) {
        try {
            String queryString = "SELECT"
                    + " count(lt.login_id) AS totalCount"
                    + " FROM login_table lt";
            queryString = getSearchCriteriaQuery(allRequestParams, queryString);
            Query sqlQuery = this.em.createNativeQuery(queryString);
            setQueryParameters(sqlQuery, allRequestParams);
            Object result = sqlQuery.getSingleResult();
            return Long.parseLong(String.valueOf(result));
        } catch (Exception e) {
            return 0L;
        }
    }

    public String getSearchCriteriaQuery(Map<String, String> allRequestParams, String queryString) {
        boolean isFirst = true;

        if (allRequestParams.containsKey("email")) {
            queryString += queryBuilder(isFirst, " lt.login_emailid LIKE :email");
            isFirst = false;
        }
        if (allRequestParams.containsKey("userName")) {
            queryString += queryBuilder(isFirst, " lt.login_username LIKE :userName");
            isFirst = false;
        }
        if (allRequestParams.containsKey("createdBy")) {
            queryString += queryBuilder(isFirst, " lt.created_by LIKE :createdBy");
            isFirst = false;
        }
        if (allRequestParams.containsKey("status")) {
            queryString += queryBuilder(isFirst, " lt.status = :status");
            isFirst = false;
        }
        return queryString;
    }

    public String queryBuilder(boolean isFirst, String queryString) {
        String modifiedQueryString = "";
        if (isFirst) {
            modifiedQueryString += " WHERE";
        } else {
            modifiedQueryString += " AND";
        }

        return modifiedQueryString + queryString;
    }

    public Map<String, String> setQueryParameters(Query query, Map<String, String> allRequestParams) {
        Map<String, String> parametersMap = new HashMap<>();
        if (allRequestParams.containsKey("email")) {
            query.setParameter("email", allRequestParams.get("email")+ "%");
            parametersMap.put("email", allRequestParams.get("email") + "%");
        }

        if (allRequestParams.containsKey("userName")) {
            query.setParameter("userName", allRequestParams.get("userName") + "%");
            parametersMap.put("userName", allRequestParams.get("userName") + "%");

        }

        if (allRequestParams.containsKey("createdBy")) {
            query.setParameter("createdBy", allRequestParams.get("createdBy") + "%");
            parametersMap.put("createdBy", allRequestParams.get("createdBy") + "%");
        }

        if (allRequestParams.containsKey("status")) {
            query.setParameter("status", allRequestParams.get("status"));
            parametersMap.put("status", allRequestParams.get("status"));
        }
        return parametersMap;
    }

}
