package com.sales.im.config;

import com.sales.im.constant.ImMqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;

import static com.sales.im.constant.ImMqConstants.EXCHANGE_TOPIC_INFORM;
import static com.sales.im.constant.ImMqConstants.QUEUE_INFORM_SMS;
import static com.sales.im.constant.ImMqConstants.ROUTINGKEY_SMS;

/**
 * @author wuyingqiang
 * @desc: TODO
 * @date 2022/8/2 23:35
 */
@Configuration
public class ImEventConfig {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		// 键序列化器
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		// 设置CacheManager的值序列化方式为json序列化，加入@Class属性
		GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
		// 配置序列化（解决乱码的问题）
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonRedisSerializer))
				.disableCachingNullValues(); // 禁用缓存空值，不缓存null校验
		RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
				.cacheDefaults(config)
				.build();
		return cacheManager;
	}

	@PostConstruct
	public void init() {
		// 设置发送时的消息转换器
		rabbitTemplate.setMessageConverter(new FastJsonMessageConverter());
	}

	/**
	 * 配置接收的消息转换器
	 *
	 * @return
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new FastJsonMessageConverter();
	}
//    @Bean
//    public MessageConverter messageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }

	// 声明交换机
	@Bean(name = ImMqConstants.EXCHANGE)
	public Exchange exchange() {
		return new DirectExchange(ImMqConstants.EXCHANGE, true, false, null);
	}

	// 声明队列
	@Bean(name = ImMqConstants.QUEUE)
	public Queue queue() {
		return new Queue(ImMqConstants.QUEUE, true, false, false, null);
	}

	// 声明交换机绑定队列
	@Bean(name = ImMqConstants.BINDING)
	public Binding binding() {
		return new Binding(ImMqConstants.QUEUE, Binding.DestinationType.QUEUE,
				ImMqConstants.EXCHANGE, ImMqConstants.BINDING, null);
	}


	// 声明交换机
	@Bean(EXCHANGE_TOPIC_INFORM)
	public Exchange EXCHANGE_TOPIC_INFORM(){
		// durable(true) 持久化, mq重启之后交换机还在
		return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC_INFORM).durable(true).build();
	}
	// 声明QUEUE_INFORM_SMS队列
	@Bean(QUEUE_INFORM_SMS)
	public Queue QUEUE_INFORM_SMS(){
		return new Queue(QUEUE_INFORM_SMS);
	}

	// ROUTINGKEY_SMS队列绑定交换机, 指定routingKey
	// @Qualifier注解给类成员变量使用时需要依赖@Autowired, 但是给方法参数可以单独使用
	@Bean
	public Binding BINDING_QUEUE_INFORM_SMS(@Qualifier(QUEUE_INFORM_SMS) Queue queue, @Qualifier (EXCHANGE_TOPIC_INFORM) Exchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_SMS).noargs();
	}
}
