package io.github.tubean.eureka.gallery.service;

import io.github.tubean.eureka.gallery.controller.request.UserRequest;
import io.github.tubean.eureka.gallery.dto.UserDto;

public interface UserService {
    UserDto create(UserRequest.Create request);
}
