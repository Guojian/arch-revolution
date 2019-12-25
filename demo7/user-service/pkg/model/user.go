package model

import (
	"encoding/json"
)

type User struct {
	Id int `json:"id" gorm:"column:id;primary_key;not null"`
	Nickname string `json:"nickname" gorm:"column:nickname;not null;type:varchar(45)"`
	Password string `json:"password" gorm:"column:password;not null;type:varchar(45)"`
	FirstName string `json:"firstName" gorm:"column:first_name;type:varchar(45)"`
	LastName string `json:"lastName" gorm:"column:last_name;type:varchar(45)"`
}

// 设置User的表名为`User`
func (User) TableName() string {
	return "user"
}

func(u *User)UnmarshalBinary(data []byte) error{
	// convert data to yours, let's assume its yaml data
	return json.Unmarshal(data, u)
}

func(u *User)MarshalBinary() (data []byte, err error) {
	return json.Marshal(u)
}
