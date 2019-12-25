package log

import "fmt"

func Infof(format string, a ...interface{})  {
	l.Info(fmt.Sprintf(format, a...))
}
