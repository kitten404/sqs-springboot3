## SQS Commands 
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name test

aws sqs send-message --endpoint-url http://localhost:4566 --queue-url http://localhost:4566/000000000000/test --message-body ""

aws sqs receive-message --endpoint-url http://localhost:4566 --queue-url http://localhost:4566/000000000000/test
