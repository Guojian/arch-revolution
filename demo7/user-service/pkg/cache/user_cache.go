package cache

import (
	"fmt"
	"github.com/Guojian/arch-revolution/demo7/user-service/pkg/model"
	"time"
)

const CACHE_KEY_PREFIX = "user_"

type UserCache interface {
	CacheUsers(users []*model.User) error
	GetUsers(keys ...string) ([]*model.User, error)
	CacheUser(u *model.User) error
	GetCachedUser(id int)(*model.User, error)
	DeleteCachedUser(id int)error
	GetUserKeys()([]string, error)
}

func NewUserCache()UserCache {
	return &userCache{}
}

type userCache struct {
}

func (c *userCache)CacheUsers(users []*model.User) error {
	pairs := make([]interface{}, 0)
	for _, u := range users {
		userCacheKey := fmt.Sprintf("%s%d", CACHE_KEY_PREFIX, u.Id)
		pairs = append(pairs, userCacheKey)
		pairs = append(pairs, u)
	}
	return GetClient().MSet(pairs...).Err()

}

func (c *userCache)GetUsers(keys ...string) ([]*model.User, error){
	vals, err := GetClient().MGet(keys...).Result()
	if err != nil {
		return nil, err
	}
	users := make([]*model.User, 0)
	for _, val := range vals {
		if v, ok := val.(string); ok {
			u := &model.User{}
			err := u.UnmarshalBinary([]byte(v))
			if err != nil {
				return nil, err
			}
			users = append(users, u)
		}
	}
	return users, nil
}

func (c *userCache)CacheUser(u *model.User) error {
	userCacheKey := fmt.Sprintf("%s%d", CACHE_KEY_PREFIX, u.Id)
	return GetClient().Set(userCacheKey, u, time.Duration(0)).Err()
}

func (c *userCache)GetCachedUser(id int)(*model.User, error){
	userCacheKey := fmt.Sprintf("%s%d", CACHE_KEY_PREFIX, id)
	val, err := GetClient().Get(userCacheKey).Result()
	if err != nil {
		return nil, err
	}
	u := &model.User{}
	err = u.UnmarshalBinary([]byte(val))
	if err != nil {
		return nil, err
	}
	return u, nil
}

func (c *userCache)DeleteCachedUser(id int)error{
	userCacheKey := fmt.Sprintf("%s%d", CACHE_KEY_PREFIX, id)
	return GetClient().Del(userCacheKey).Err()
}

func (c *userCache)GetUserKeys()([]string, error){
	return GetClient().Keys(CACHE_KEY_PREFIX + "*").Result()
}
