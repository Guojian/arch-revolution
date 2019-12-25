package log

import "go.uber.org/zap"

var (
	l *zap.Logger
)

func ConfigureLogger(logger *zap.Logger)  {
	l = logger
}


