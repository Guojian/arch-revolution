package server

import (
	"fmt"
	"github.com/Guojian/arch-revolution/demo7/user-service/pkg/config"
	"github.com/Guojian/arch-revolution/demo7/user-service/pkg/route"
	"github.com/Guojian/arch-revolution/demo7/user-service/pkg/log"
	"github.com/gin-gonic/gin"
)

func StartServer() error {
	r := gin.Default()
	route.ConfigureRoutes(r)

	serverBindAddr := fmt.Sprintf(":%d", config.GetAppConfig().Server.Port)
	log.Infof("Run server[service-name=%s] at %s", config.GetAppConfig().Application.Service.Name, serverBindAddr)
	return r.Run(serverBindAddr) // listen and serve
}
