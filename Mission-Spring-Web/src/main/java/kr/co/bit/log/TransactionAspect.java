package kr.co.bit.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

@Component

public class TransactionAspect {

	@Around("execution(* kr.co.bit..*Impl*.*(..))")
	public Object process(ProceedingJoinPoint joinPoint) {
		
		return null;
	}
}
