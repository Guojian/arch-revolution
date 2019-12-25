---
typora-root-url: ./
typora-copy-images-to: ../images
---



应用经过一系列演进后，最终演进成为了云原生应用。为了方便容器平台的运维人员快速了解应用，从而高效地实施应用的运维操作，建议在完成改造后整理应用的相关说明文档，作为应用交付产物的一部分。

这里我们整理了demo7的应用交付说明文档，大家可以将这个作为应用的交付模板。

## 1. 应用简述

demo7是一个简单的示例微服务应用，其由spring boot及Go语言编写的多个微服务模块构成。

## 2. 应用构成

| 应用微服务模块名称 |   模块服务端口   |   是否无状态   |  需要的CPU（单位vcpu）   |  需要的内存（单位M）   |   需要的存储空间（单位G）   |
| ------------------ | ---- | ---- | ---- | ---- | ---- |
| user-service | http/8081 | 无状态 | 无特殊要求 | 无特殊要求 | 不需要 |
| blog-service | http/8082 | 无状态 | 无特殊要求 | 无特殊要求 | 不需要 |
| aggregation-service | http/8083 | 无状态 | 无特殊要求 | 无特殊要求 | 不需要 |
| apigateway-service | http/8084 | 无状态 | 无特殊要求 | 无特殊要求 | 不需要 |
| frontend-service | http/8085 | 无状态 | 无特殊要求 | 无特殊要求 | 不需要 |
| mysql-service | tcp/3306 | 有状态 | 0.5 | 512 | 10 |
| redis-service | tcp/6379 | 有状态 | 0.5 | 1024 | 2 |
| rabbitmq-service | tcp/5672 | 有状态 | 0.5 | 1024 | 2 |

## 3. 各微服务模块日志

| 应用微服务模块名称 |     日志说明（如日志输出到容器某路径下，须详细说明）     |
| ------------------ | ---- |
| user-service | 直接输出到标准输出 |
| blog-service | 直接输出到标准输出 |
| aggregation-service | 直接输出到标准输出 |
| apigateway-service | 直接输出到标准输出 |
| frontend-service | 直接输出到标准输出 |
| mysql-service | 直接输出到标准输出 |
| redis-service | 直接输出到标准输出 |
| rabbitmq-service | 直接输出到标准输出 |

## 4. 各微服务模块应用监控指标

| 应用微服务模块名称 |     应用监控指标说明（如以prometheus等方式暴露应用监控指标，须详细说明）     |
| ------------------ | ---- |
| user-service | 以 http://${user_service_ip}:8081/metrics 暴露prometheus格式的应用监控指标 |
| blog-service | 示例应用，未暴露应用监控指标 |
| aggregation-service | 示例应用，未暴露应用监控指标 |
| apigateway-service | 示例应用，未暴露应用监控指标 |
| frontend-service | 示例应用，未暴露应用监控指标 |
| mysql-service | 示例应用，未暴露应用监控指标 |
| redis-service | 示例应用，未暴露应用监控指标 |
| rabbitmq-service | 示例应用，未暴露应用监控指标 |

## 5. 各微服务模块调用关系

![1556184940347](../images/1556184940347.png)



## 6. 应用依赖的镜像

| 应用微服务模块名称 |   docker镜像名称  |   docker镜像tag   | 相关dockerfile |
| ------------------ | ---- | ---- | ---- |
| user-service | demo7/user-service | 0.0.1-SNAPSHOT | [user-service的Dockerfile](../user-service/docker/Dockerfile) |
| blog-service | demo7/blog-service | 0.0.1-SNAPSHOT | [blog-service的pom.xml](../blog-service/pom.xml)(直接由mvn命令生成docker镜像) |
| aggregation-service | demo7/aggregation-service | 0.0.1-SNAPSHOT | [aggregation-service的pom.xml](../aggregation-service/pom.xml)(直接由mvn命令生成docker镜像) |
| apigateway-service | demo7/apigateway-service | 0.0.1-SNAPSHOT | [apigateway-service的pom.xml](../apigateway-service/pom.xml)(直接由mvn命令生成docker镜像) |
| frontend-service | demo7/frontend-service | 0.0.1-SNAPSHOT | [frontend-service的pom.xml](../frontend-service/pom.xml)(直接由mvn命令生成docker镜像) |
| mysql-service | mysql<br>busybox<br> | 5.7.14<br>1.29.3 | 官方镜像 |
| redis-service | bitnami/redis | 4.0.14 | 官方镜像 |
| rabbitmq-service | bitnami/rabbitmq | 3.7.14 | 官方镜像 |

## 7. 应用部署的chart包

应用部署到kubernetes里所使用的[chart包](../chart/demo7)

