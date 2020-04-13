package com.bridgelabz.fundoonotes.utilis;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class RabbitProducer {

	@Autowired
	   private AmqpTemplate amqpTemplate;
	   
	   @Value("${rabbitmq.exchange}")
	   private String exchange;
	   
	   @Value("${rabbitmq.routingkey}")
	   private String routingKey;
	   
	   public void produceMsg(String msg){
	      amqpTemplate.convertAndSend(exchange, routingKey, msg);
	   }
}
