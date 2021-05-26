package com.ptit.sale.common.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public class PageProcess {
    public static String PageToSqlQuery(Pageable pageable, String defaultTable) {
        String page_ = "";
        Sort sort = pageable.getSort();
        List<Sort.Order> orders = sort.toList();
        for (Sort.Order order : orders) {
            String prop = order.getProperty();
            if (prop.split("\\.").length == 1) {
                // Nếu không muốn ghép table mà giữ nguyên tên trường thì truyền null
                if (defaultTable != null) {
                    prop = defaultTable + "." + prop;
                }
            }
            if (page_ != "") {
                page_ += " ," + prop;
            } else {
                page_ = " order by " + prop;
            }
            Sort.Direction direction = order.getDirection();
            if (direction.isAscending()) {
                page_ += " asc";
            } else {
                page_ += " desc";
            }
        }
        long offSet = pageable.getOffset();
        long pageSize = pageable.getPageSize();
        if (page_ == "") {
            page_ = " order by (select null)";
        }
        page_ += " OFFSET " + offSet + " ROWS FETCH NEXT " + pageSize + " ROWS ONLY";
        return page_;
    }

    public static String PageToSqlQueryWithDefaultSort(Pageable pageable, String defaultTable, String defaultSortCol) {
        String page_ = "";
        Sort sort = pageable.getSort();
        Set<Sort.Order> orders = sort.toSet();
        for (Sort.Order order : orders) {
            String prop = order.getProperty();
            if (prop.split("\\.").length == 1) {
                prop = defaultTable + "." + prop;
            }
            if (page_ != "") {
                page_ += " ," + prop;
            } else {
                page_ = " order by " + prop;
            }
            Sort.Direction direction = order.getDirection();
            if (direction.isAscending()) {
                page_ += " asc";
            } else {
                page_ += " desc";
            }
        }
        long offSet = pageable.getOffset();
        long pageSize = pageable.getPageSize();
        if (page_ == "") {
            page_ = " order by " + defaultSortCol;
        }
        page_ += " OFFSET " + offSet + " ROWS FETCH NEXT " + pageSize + " ROWS ONLY";
        return page_;
    }
}
