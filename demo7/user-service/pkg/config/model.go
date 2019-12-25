package config

type AppConfig struct {
	Server struct {
		Port int `yaml:"port"`
	} `yaml:"server"`
	Application struct {
		Service struct {
			Name string `yaml:"name"`
		} `yaml:"service"`
		Logging struct {
			Env string `yaml:"env"`
		} `yaml:"logging"`
		Datasource struct {
			Password string    `yaml:"password"`
			URL      string `yaml:"url"`
			Username string `yaml:"username"`
			MaxIdleConns int `yaml:"maxIdleConns"`
			MaxOpenConns int `yaml:"maxOpenConns"`
		} `yaml:"datasource"`
		Rabbitmq struct {
			Host     string `yaml:"host"`
			Password string `yaml:"password"`
			Port     int    `yaml:"port"`
			Username string `yaml:"username"`
		} `yaml:"rabbitmq"`
		Redis struct {
			Database   int    `yaml:"database"`
			Host       string `yaml:"host"`
			PoolConfig struct {
				MaxActive int `yaml:"max-active"`
				MaxIdle   int `yaml:"max-idle"`
				MaxWait   int `yaml:"max-wait"`
				MinIdle   int `yaml:"min-idle"`
			} `yaml:"poolConfig"`
			Password string `yaml:"password"`
			Port     int    `yaml:"port"`
			Timeout  int    `yaml:"timeout"`
		} `yaml:"redis"`
	} `yaml:"application"`
}
