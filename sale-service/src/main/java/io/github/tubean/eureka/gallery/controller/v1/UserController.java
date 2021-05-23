package io.github.tubean.eureka.gallery.controller.v1;

import io.github.tubean.eureka.gallery.common.enums.ResponseCodeEnum;
import io.github.tubean.eureka.gallery.controller.request.ProductRequest;
import io.github.tubean.eureka.gallery.controller.request.UserRequest;
import io.github.tubean.eureka.gallery.controller.response.ResponseBodyDto;
import io.github.tubean.eureka.gallery.dto.ProductDto;
import io.github.tubean.eureka.gallery.dto.UserDto;
import io.github.tubean.eureka.gallery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Validated
public class UserController
{
    @Autowired
    UserService userService;
    @PostMapping(value = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> create(
            @RequestBody UserRequest.Create request
    ){
        UserDto userDto= userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}
