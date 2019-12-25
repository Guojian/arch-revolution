## Folder content generated files
SERVER_VERSION = 0.0.1-SNAPSHOT
BUILD_FOLDER = ./build
TARGET_BIN_DIR = pkg/cmd/user-service
TARGET_BIN_FILE = user-service
DOCKER_IMAGE_NAME = tcnp/demo7/user-service/user-service
DOCKER_REGISTRY = registry.tcnp.com
## command
GO           = go
GO_VENDOR    = go mod
MKDIR_P      = mkdir -p

## Random Alphanumeric String
SECRET_KEY   = $(shell cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)

## UNAME
UNAME := $(shell uname)

################################################

.PHONY: src.build
src.build:
	$(MKDIR_P) $(BUILD_FOLDER)/$(TARGET_BIN_DIR)/
	GO111MODULE=on $(GO) build -mod=vendor -v -o $(BUILD_FOLDER)/$(TARGET_BIN_DIR)/$(TARGET_BIN_FILE) \
	./cmd/...

.PHONY: docker.build
docker.build:
	docker build --rm --tag $(DOCKER_IMAGE_NAME):$(SERVER_VERSION) -f ./docker/Dockerfile .

.PHONY: docker.push
docker.push: docker.build
	docker tag $(DOCKER_IMAGE_NAME):$(SERVER_VERSION) $(DOCKER_REGISTRY)/$(DOCKER_IMAGE_NAME):$(SERVER_VERSION)
	docker push $(DOCKER_REGISTRY)/$(DOCKER_IMAGE_NAME):$(SERVER_VERSION)

.PHONY: generate.docs
generate.docs:
	swag init -g ./pkg/route/config.go
