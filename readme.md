## Phát triển phần mềm hướng dịch vụ

#### HD chạy toàn bộ project:
- vào sale-service,user-service sửa file src/main/resource/application.properties
- sửa username/password của mysql giống máy mình
- run lần lượt server, zuul-server, sale-service, user-service

#### HD chạy service của riêng mình (user-service/sale-service):
- vào sale-service/user-service sửa username,password mysql
- bỏ comment 2 dòng cuối cùng trong file properties