echo "############# Configurando RabbitMQ #############"

echo "### Declarando Exchanges ###"

sudo docker exec -it rabbitmq rabbitmqadmin declare exchange name=payment.processor type=direct -u rabbituser -p P@ssw0rd
sudo docker exec -it rabbitmq rabbitmqadmin declare exchange name=payment.status type=fanout -u rabbituser -p P@ssw0rd

echo "\n### Declarando Queues ###"
sudo docker exec -it rabbitmq rabbitmqadmin declare queue name=payment.processor.solicitation durable=true -u rabbituser -p P@ssw0rd
sudo docker exec -it rabbitmq rabbitmqadmin declare queue name=payment.status.cart.service durable=true -u rabbituser -p P@ssw0rd
sudo docker exec -it rabbitmq rabbitmqadmin declare queue name=payment.status.email.service durable=true -u rabbituser -p P@ssw0rd

echo "\n### Declarando Binding ###"
sudo docker exec -it rabbitmq rabbitmqadmin declare binding source=payment.processor destination=payment.processor.solicitation routing_key=payment.processor.solicitation -u rabbituser -p P@ssw0rd
sudo docker exec -it rabbitmq rabbitmqadmin declare binding source=payment.status destination=payment.status.cart.service routing_key=payment.status.cart.service -u rabbituser -p P@ssw0rd
sudo docker exec -it rabbitmq rabbitmqadmin declare binding source=payment.status destination=payment.status.email.service routing_key=payment.status.email.service -u rabbituser -p P@ssw0rd
