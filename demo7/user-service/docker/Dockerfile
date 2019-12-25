# Building stage
FROM golang:1.11-alpine3.9 as builder

ENV CGO_ENABLED 0
ENV GOOS linux
ENV GO111MODULE=on

ARG PROJECT_PATH=github.com/Guojian/arch-revolution/demo7/user-service
ARG BUILD_FOLDER=build
ARG TARGET_BIN_DIR=pkg/cmd/user-service
ARG TARGET_BIN_FILE=user-service

WORKDIR /go/src/${PROJECT_PATH}

# Source code, building tools and dependences
COPY ./ /go/src/${PROJECT_PATH}/

ENV TIMEZONE "Asia/Shanghai"

RUN apk add --no-network --allow-untrusted /go/src/${PROJECT_PATH}/docker/pkgs/make-4.2.1-r2.apk 2>/dev/null

RUN make src.build
RUN mv ${BUILD_FOLDER}/${TARGET_BIN_DIR}/${TARGET_BIN_FILE} /go/bin

# Production stage
FROM alpine:3.9
WORKDIR /go/bin

# copy the go binaries from the building stage
COPY --from=builder /go/bin /go/bin

# copy the config files from the current working dir
RUN mkdir -p /etc/user-service
COPY etc/application.yaml /etc/user-service/application.yaml

EXPOSE 8081
ENTRYPOINT ["./user-service", "-c", "/etc/user-service/application.yaml"]
