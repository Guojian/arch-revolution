package dal

import "github.com/Guojian/arch-revolution/demo7/user-service/pkg/model"

type UserDao interface {
	FindAll()([]*model.User, error)
	SaveUser(u *model.User)error
	FindUserById(id int)(*model.User, error)
	UpdateUser(userId int, u *model.User)(*model.User, error)
	DeleteById(userId int)error
}

func NewUserDao() UserDao {
	return &userDao{}
}

type userDao struct {
}

func (this *userDao)FindAll()([]*model.User, error) {
	users := make([]*model.User, 0)
	err := db.Find(&users).Error
	if err != nil {
		return nil, err
	}
	return users, nil
}

func (this *userDao)SaveUser(u *model.User)error {
	if u.Id > 0 {
		return db.Save(u).Error
	} else {
		return db.Create(u).Error
	}
}

func (this *userDao)FindUserById(id int)(*model.User, error){
	u := &model.User{}
	err := db.First(u, id).Error
	if err != nil {
		return nil, err
	}
	return u, nil
}

func (this *userDao)UpdateUser(userId int, u *model.User)(*model.User, error){
	u.Id = userId
	err := db.Save(u).Error
	if err != nil {
		return nil, err
	}
	return u, nil
}

func (this *userDao)DeleteById(userId int)error{
	return db.Delete(&model.User{}, userId).Error
}
