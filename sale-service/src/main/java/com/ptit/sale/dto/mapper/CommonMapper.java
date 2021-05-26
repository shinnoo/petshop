package com.ptit.sale.dto.mapper;

import com.ptit.sale.common.constant.ResultPage;
import com.ptit.sale.controller.request.OrderProductRequest;
import com.ptit.sale.controller.request.OrdersRequest;
import com.ptit.sale.dto.OrderProductDto;
import com.ptit.sale.dto.OrdersDto;
import com.ptit.sale.model.OrderProduct;
import com.ptit.sale.model.Orders;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class CommonMapper {
    public static <T, E> E map(T source, Class<E> typeDestination) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(source, typeDestination);
    }

    public static <S, T> Page<T> toPage(Page<S> source, Class<T> targetClass, Pageable pageable) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Page<T> page = new PageImpl<T>(
                source.stream().map(item -> modelMapper.map(item, targetClass)).collect(Collectors.toList()), pageable,
                source.getTotalElements());
        return page;
    }

    public static <S, T> List<T> toList(List<S> source, Class<T> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return source.stream().map(item -> modelMapper.map(item, targetClass)).collect(Collectors.toList());
    }

    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        BeanUtils.copyProperties(source, target, emptyNames.toArray(result));
    }
    public static Orders createOrderRequestToOrder(OrdersRequest.Create createOrderRequest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull())
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(createOrderRequest, Orders.class);
    }
    public static OrdersDto orderToOrderDto(Orders order) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(order, OrdersDto.class);
    }
    public static List<OrderProductDto> orderProductsToOrderProductDtos(List<OrderProduct> orderProducts) {
        ModelMapper modelMapper = new ModelMapper();
        return orderProducts.stream().map(i -> modelMapper.map(i, OrderProductDto.class)).collect(Collectors.toList());
    }
    public ResultPage<OrderProductDto> toResultPageOrderProductDto(ResultPage<OrderProduct> resultPage) {
        ModelMapper modelMapper = new ModelMapper();
        ResultPage<OrderProductDto> resultPageDto = new ResultPage<>();
        resultPageDto.setTotalItems(resultPage.getTotalItems());
        resultPageDto.setPageList(resultPage.getPageList().stream().map(s -> modelMapper.map(s, OrderProductDto.class))
                .collect(Collectors.toList()));
        return resultPageDto;
    }
    public static OrderProduct createOrderProductRequestToOrderProduct(OrderProductRequest.Create createOrderProductRequest) {
        ModelMapper modelMapper= new ModelMapper();
        return modelMapper.map(createOrderProductRequest, OrderProduct.class);
    }
}
