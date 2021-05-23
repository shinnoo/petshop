package io.github.tubean.eureka.gallery.repository;

import io.github.tubean.eureka.gallery.common.constant.ResultPage;
import io.github.tubean.eureka.gallery.common.util.SqlProcess;
import io.github.tubean.eureka.gallery.model.OrderProduct;
import org.hibernate.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class OrderProductDetailRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public ResultPage<OrderProduct> getAllWithFilter(String orderId, Pageable pageable,
                                                     MultiValueMap<String, String> where) {

        Session session = (Session) entityManager.getDelegate();
        where.put("order_id", Arrays.asList(orderId));

        Map<String, Object> queryTypes = new HashMap<>();
        List<String> equalType = new ArrayList<String>(Arrays.asList("order_id", "product_id", "quantity"));
        queryTypes.put("equalType", equalType);

        return SqlProcess.getResultPage(session, "order_product", OrderProduct.class, pageable, where, queryTypes);
    }
}
