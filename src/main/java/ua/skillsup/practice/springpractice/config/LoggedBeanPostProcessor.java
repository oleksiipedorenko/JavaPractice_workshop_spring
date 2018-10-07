package ua.skillsup.practice.springpractice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.*;

@Component
public class LoggedBeanPostProcessor implements BeanPostProcessor {

	private final Logger logger = LoggerFactory.getLogger(LoggedBeanPostProcessor.class);

	private Map<String, Class<?>> originalClasses = new HashMap<>();

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		for (Method method : bean.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(Logged.class)) {
				originalClasses.putIfAbsent(beanName, bean.getClass());
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> originalClass = originalClasses.get(beanName);
		if (Objects.nonNull(originalClass)) {
			return Proxy.newProxyInstance(originalClass.getClassLoader(),
					ClassUtils.getAllInterfacesForClass(originalClass),
					(proxy, method, args) -> {
						if (originalClass.getMethod(method.getName(), method.getParameterTypes()).
								isAnnotationPresent(Logged.class)) {
							logger.info("Class {}, method {}, args '{}'", method.getDeclaringClass().getName(), method.getName(),
									Arrays.toString(args));
						}
						return method.invoke(bean, args);
					});
		}
		return bean;
	}
}