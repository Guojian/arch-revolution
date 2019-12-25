package config

import (
	"flag"
	"fmt"
	"gopkg.in/yaml.v2"
	"io/ioutil"
	"os"
)

var (
	h bool
	c string
	appConfig = AppConfig{}
)

func init() {
	flag.BoolVar(&h, "h", false, "show help")
	flag.StringVar(&c, "c", "etc/application.yaml", "app configuration file")

	flag.Usage = usage

	flag.Parse()

	f, err := os.OpenFile(c, 0644, 0644)
	if err != nil {
		panic(err)
	}
	b, err := ioutil.ReadAll(f)
	if err != nil {
		panic(err)
	}
	err = yaml.Unmarshal(b, &appConfig)
	if err != nil {
		panic(err)
	}
}

func GetAppConfig() *AppConfig {
	return &appConfig
}

func usage() {
	fmt.Fprintf(os.Stderr, `Usage: user-service [-c configfile]

Options:
`)
	flag.PrintDefaults()
}
