package queue

import "github.com/streadway/amqp"


type QueueService interface {
	PublishUserEvent(body string) error
}

func NewQueueService()QueueService{
	return &queueService{}
}

type queueService struct {
}

func (s *queueService)PublishUserEvent(body string) error {
	ch, err := conn.Channel()
	if err != nil {
		return err
	}
	defer ch.Close()
	q, err := ch.QueueDeclare(
		USER_EVNETS_QUEUE_NAME, // name
		false,   // durable
		false,   // delete when unused
		false,   // exclusive
		false,   // no-wait
		nil,     // arguments
	)
	if err != nil {
		return err
	}

	return ch.Publish(
		"",     // exchange
		q.Name, // routing key
		false,  // mandatory
		false,  // immediate
		amqp.Publishing {
			ContentType: "text/plain",
			Body:        []byte(body),
		})
}
