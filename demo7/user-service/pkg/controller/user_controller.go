package controller

import (
	"github.com/Guojian/arch-revolution/demo7/user-service/pkg/model"
	"github.com/Guojian/arch-revolution/demo7/user-service/pkg/service"
	"github.com/Guojian/arch-revolution/demo7/user-service/pkg/util"
	"github.com/gin-gonic/gin"
	"net/http"
	"strconv"
)

type UserController interface {
	GetUsers(c *gin.Context)
	AddUser(c *gin.Context)
	GetUser(c *gin.Context)
	UpdateUser(c *gin.Context)
	DeleteUser(c *gin.Context)
}

func NewUserController() UserController {
	return &userController{
		userService: service.NewUserService(),
	}
}

type userController struct {
	userService service.UserService
}

// @summary Get all users
// @description 获取所有用户
// @success 200 {array} model.User OK
// @failure 500 {object} util.HTTPError Server Error
// @router /users [get]
func (this *userController)GetUsers(c *gin.Context)  {
	users, err := this.userService.FindAll()
	if err != nil {
		util.NewError(c, http.StatusInternalServerError, err)
		return
	}
	c.JSON(http.StatusOK, users)
}

// @summary Create user
// @description 创建用户
// @param   body   body    model.User  true     "The specified user body"
// @success 200 {object} model.User OK
// @failure 500 {object} util.HTTPError Server Error
// @router /users [post]
func (this *userController)AddUser(c *gin.Context)  {
	u := &model.User{}
	err := c.ShouldBindJSON(u)
	if err != nil {
		util.NewError(c, http.StatusInternalServerError, err)
		return
	}
	err = this.userService.SaveUser(u)
	if err != nil {
		util.NewError(c, http.StatusInternalServerError, err)
		return
	}
	c.JSON(http.StatusOK, u)
}

// @summary Get user
// @description 查询某个用户
// @param   id   path    int  true     "The id of user you want to get"
// @success 200 {object} model.User OK
// @failure 500 {object} util.HTTPError Server Error
// @router /users/{id} [get]
func (this *userController)GetUser(c *gin.Context)  {
	userId, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		util.NewError(c, http.StatusInternalServerError, err)
		return
	}
	u, err := this.userService.FindUserById(userId)
	if err != nil {
		util.NewError(c, http.StatusInternalServerError, err)
		return
	}
	c.JSON(http.StatusOK, u)
}

// @summary Update a user
// @description 更新某个用户
// @param   id   path    int  true     "The id of user you want to get"
// @param   body   body    model.User  true     "The specified user body"
// @success 200 {object} model.User OK
// @failure 500 {object} util.HTTPError Server Error
// @router /users/{id} [put]
func (this *userController)UpdateUser(c *gin.Context)  {
	userId, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		util.NewError(c, http.StatusInternalServerError, err)
		return
	}
	u := &model.User{}
	err = c.ShouldBindJSON(u)
	if err != nil {
		util.NewError(c, http.StatusInternalServerError, err)
		return
	}
	u, err = this.userService.UpdateUser(userId, u)
	if err != nil {
		util.NewError(c, http.StatusInternalServerError, err)
		return
	}
	c.JSON(http.StatusOK, u)
}

// @summary Delete a user
// @description 删除某个用户
// @param   id   path    int  true     "The id of user you want to delete"
// @success 200 {int} int OK
// @failure 500 {object} util.HTTPError Server Error
// @router /users/{id} [delete]
func (this *userController)DeleteUser(c *gin.Context)  {
	userId, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		util.NewError(c, http.StatusInternalServerError, err)
		return
	}
	err = this.userService.DeleteById(userId)
	if err != nil {
		util.NewError(c, http.StatusInternalServerError, err)
		return
	}
	c.JSON(http.StatusOK, userId)
}


