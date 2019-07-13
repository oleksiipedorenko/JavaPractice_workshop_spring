package ua.skillsup.practice.springpractice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class MethodLoggerBeanPostProcessor implements BeanPostProcessor {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final Map<String, Class<?>> originalClasses = new HashMap<>();

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		for (Method method : bean.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(Logged.class)) {
				originalClasses.put(beanName, bean.getClass());
				break;
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> aClass = originalClasses.get(beanName);
		if (aClass != null) {
			return Proxy.newProxyInstance(
					aClass.getClassLoader(),
					aClass.getInterfaces(),
					(proxy, method, arg) -> {
						if (aClass.getDeclaredMethod(
									method.getName(),
									method.getParameterTypes())
								.isAnnotationPresent(Logged.class)) {
							logger.info("Bean {} got an incovation of method {} with parameters {}",
									beanName, method.getName(), Arrays.toString(arg));
						}

						return method.invoke(bean, arg);
					});
		}
		return bean;
	}
}