package io.github.tubean.eureka.gallery.repository;

import io.github.tubean.eureka.gallery.common.constant.ResultPage;
import io.github.tubean.eureka.gallery.common.util.SqlProcess;
import io.github.tubean.eureka.gallery.model.Product;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
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
public class ProductDetailRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public ResultPage<Product> getAllWithFilter(Pageable pageable, MultiValueMap<String, String> where) {
        Session session = (Session) entityManager.getDelegate();

        Map<String, Object> queryTypes = new HashMap<>();
        List<String> equalType = new ArrayList<String>(Arrays.asList("type", "id", "code"));
        queryTypes.put("equalType", equalType);
        List<String> likeType = new ArrayList<String>();
        queryTypes.put("likeType", likeType);
        List<String> dateType = new ArrayList<String>(Arrays.asList());
        queryTypes.put("dateType", dateType);
        List<String> inRangeType = new ArrayList<String>(Arrays.asList("price","total_quantity"));
        queryTypes.put("inRangeType", inRangeType);

        // search
        Map<String, List<String>> searchType = new HashMap<>();

        // field được cho phép tìm kiếm
        List<String> stringSearchFields = new ArrayList<String>(Arrays.asList("name", "price"));
        searchType.put("string_search", stringSearchFields);

        queryTypes.put("searchType", searchType);
        return SqlProcess.getResultPage(session, "product", Product.class, pageable, where, queryTypes);
    }
}
