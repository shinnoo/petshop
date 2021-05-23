package io.github.tubean.eureka.gallery.service.impl;

import io.github.tubean.eureka.gallery.common.error.BadRequestException;
import io.github.tubean.eureka.gallery.controller.request.UserRequest;
import io.github.tubean.eureka.gallery.dto.ProductDto;
import io.github.tubean.eureka.gallery.dto.UserDto;
import io.github.tubean.eureka.gallery.dto.mapper.CommonMapper;
import io.github.tubean.eureka.gallery.model.Product;
import io.github.tubean.eureka.gallery.model.User;
import io.github.tubean.eureka.gallery.repository.UserRepository;
import io.github.tubean.eureka.gallery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto create(UserRequest.Create request) {
        User user = new User();
        CommonMapper.copyPropertiesIgnoreNull(request,user);
        user= userRepository.save(user);
        return CommonMapper.map(user, UserDto.class);
    }
}
