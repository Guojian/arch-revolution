---
typora-root-url: ./
typora-copy-images-to: images
---

## Go语言重写微服务模块

由于Java语言目前被oracle公司把持，后续如想持续获得JDK的安全更新，需要向Oracle公司交纳费用。同时Java程序过多的CPU/内存消耗、缓慢的启动速度、程序包体积过大等问题也一直被人诟病。在云原生时代，我们建议使用Go语言进行微服务模块开发。

`demo7`基于`demo6`，架构上没有作大的改动，仅使用Go语言将原来Java写的`user-service`微服务模块重写了一遍。为了照顾原来的Java程序员的编程习惯，整个[user-service](user-service)示例也依照Java的习惯采用MVC架构模式编写。

## 部署指引

1. 编译`user-service`的docker镜像，参考命令如下：

   ```bash
   cd user-service
   make docker-build
   ```

2. 剩下的步骤完全参考[demo6](../demo6)的部署指引即可。



