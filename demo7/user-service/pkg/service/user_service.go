package service

import (
	"github.com/Guojian/arch-revolution/demo7/user-service/pkg/cache"
	"github.com/Guojian/arch-revolution/demo7/user-service/pkg/dal"
	"github.com/Guojian/arch-revolution/demo7/user-service/pkg/model"
	"github.com/Guojian/arch-revolution/demo7/user-service/pkg/queue"
)

type UserService interface {
	FindAll()([]*model.User, error)
	SaveUser(u *model.User)error
	FindUserById(id int)(*model.User, error)
	UpdateUser(userId int, u *model.User)(*model.User, error)
	DeleteById(userId int)error
}

func NewUserService()UserService{
	return &userService{
		userDao: dal.NewUserDao(),
		userCache: cache.NewUserCache(),
		queueService: queue.NewQueueService(),
	}
}

type userService struct {
	userDao dal.UserDao
	userCache cache.UserCache
	queueService queue.QueueService
}

func (this *userService)FindAll()([]*model.User, error) {
	keys, err := this.userCache.GetUserKeys()
	if err != nil {
		return nil, err
	}
	if len(keys) == 0 {
		users, err := this.userDao.FindAll()
		if err != nil {
			return nil, err
		}
		err = this.userCache.CacheUsers(users)
		if err != nil {
			return nil, err
		}
		keys, err = this.userCache.GetUserKeys()
		if err != nil {
			return nil, err
		}
	}
	return this.userCache.GetUsers(keys...)
}

func (this *userService)SaveUser(u *model.User)error {
	err := this.userDao.SaveUser(u)
	if err != nil {
		return err
	}
	err = this.userCache.CacheUser(u)
	if err != nil {
		return err
	}
	this.queueService.PublishUserEvent("UserCreatedOrUpdated")
	return nil
}

func (this *userService)FindUserById(id int)(*model.User, error){
	u, err := this.userCache.GetCachedUser(id)
	if err != nil {
		return nil, err
	}
	if u == nil {
		var err error
		u, err = this.userDao.FindUserById(id)
		if err != nil {
			return nil, err
		}
		err = this.userCache.CacheUser(u)
		if err != nil {
			return nil, err
		}
	}
	return u, nil
}

func (this *userService)UpdateUser(userId int, u *model.User)(*model.User, error){
	u, err := this.userDao.UpdateUser(userId, u)
	if err != nil {
		return nil, err
	}
	err = this.userCache.CacheUser(u)
	if err != nil {
		return nil, err
	}
	this.queueService.PublishUserEvent("UserUpdated")
	return u, nil
}

func (this *userService)DeleteById(userId int)error{
	err := this.userDao.DeleteById(userId)
	if err != nil {
		return err
	}
	err = this.userCache.DeleteCachedUser(userId)
	if err != nil {
		return err
	}
	this.queueService.PublishUserEvent("UserDeleted")
	return nil
}
