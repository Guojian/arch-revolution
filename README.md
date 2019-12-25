# 应用架构演进

本项目是一个展示应用架构演进的示例工程，包含以下几个子工程：

## 1. 单体应用架构示例

`demo1`是一个传统的单体应用，该示例的详细说明见[demo1的README](demo1/README.md)。

## 2. 单体应用拆分过渡示例

`demo2`是一个单体微服务拆分过渡中的应用，该示例的详细说明见[demo2的README](demo2/README.md)。

## 3. 微服务架构示例

`demo3`是一个进行了微服务改造后的应用，该示例的详细说明见[demo3的README](demo3/README.md)。

## 4. Istio服务网格纳管传统微服务应用

`demo4`在`demo3`基础上进行了简单的调整，以适应将其部署到虚拟机，并由Istio服务网格纲管的场景。

该示例的详细说明见[demo4的README](demo4/README.md)。

## 5. 微服务应用架构优化

`demo5`在`demo4`的基础之上，为优化应用性能，加入了缓存服务、消息队列服务。

该示例的详细说明见[demo5的README](demo5/README.md)。

## 6. 微服务应用云原生化

`demo6`在`demo5`的基础上，将应用各模块打包成容器镜像，并以标准的[Chart](https://helm.sh/docs/developing_charts/)包形式描述整个应用部署信息，便于部署到kubernetes里去，以实现真正的应用云原生化，真正体验到云原生应用的优势。

该示例的详细说明见[demo6的README](demo6/README.md)。

## 7. Go语言重写微服务模块

`demo7`在`demo6`的基础上，将`user-service`这个微服务模块采用Go语言重写。

该示例的详细说明见[demo7的README](demo7/README.md)。

# 应用交付模板

应用经过一系列演进后，最终演进成为了云原生应用。为了方便容器平台的运维人员快速了解应用，从而高效地实施应用的运维操作，建议在完成改造后整理应用的相关说明文档，作为应用交付产物的一部分。

这里我们整理了[demo7的应用交付说明文档](demo7/docs/app_notes.md)，大家可以将这个作为应用的交付模板。