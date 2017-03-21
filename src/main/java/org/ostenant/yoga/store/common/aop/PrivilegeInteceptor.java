package org.ostenant.yoga.store.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class PrivilegeInteceptor {

	public Object around(ProceedingJoinPoint jp) throws Throwable {
		Object o = jp.proceed();
		System.out.println(o);
		return o;
	}
}
