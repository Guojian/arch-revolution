package tracer

import (
	"github.com/gin-gonic/gin"
	"github.com/opentracing/opentracing-go"
	"net/http"
)

const SPAN_CTX_KEY = "__SPAN_CTX__"

func ExtractSpanContextMiddleware(c *gin.Context) {
	tracer := opentracing.GlobalTracer()
	spanCtx, _ := tracer.Extract(opentracing.HTTPHeaders, opentracing.HTTPHeadersCarrier(c.Request.Header))
	if spanCtx != nil {
		c.Set(SPAN_CTX_KEY, spanCtx)
	}
	c.Next()
}

// propagate span context information when call other service
func InjectSpanContextFromGinContext(c *gin.Context, req *http.Request) {
	ctx := GetSpanContextFromGinContext(c)
	if ctx != nil {
		InjectSpanContext(ctx, req)
	}
}

func InjectSpanContext(ctx opentracing.SpanContext, req *http.Request){
	tracer := opentracing.GlobalTracer()
	tracer.Inject(ctx, opentracing.HTTPHeaders, opentracing.HTTPHeadersCarrier(req.Header))
}

func GetSpanContextFromGinContext(c *gin.Context)opentracing.SpanContext{
	if v, existed := c.Get(SPAN_CTX_KEY); existed {
		if ctx, ok := v.(opentracing.SpanContext); ok {
			return ctx
		}
	}
	return nil
}
