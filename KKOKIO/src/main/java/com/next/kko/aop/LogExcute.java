package com.next.kko.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExcute {

	//dao에 있는 메소드가 실행될 때 작동
	public void before(JoinPoint j) throws Throwable {
		Logger log = LoggerFactory.getLogger(j.getTarget()+"");
		log.debug("시작");
		Object[] args = j.getArgs();
		if(args!=null) {
			log.debug("메소드:\t"+j.getSignature().getName());
			for (int i = 0; i < args.length; i++) {
				log.debug((i+1)+"번째:\t"+args[i]);
			}
		}
	}
	
	//dao에 있는 메소드가 반환이 될때
	public void afterReturning(JoinPoint j) throws Throwable {
		Logger log = LoggerFactory.getLogger(j.getTarget()+"");
		log.debug("끝");
	}
	
	//dao에 있는 메소드가 Error가 발생될 때
	public void daoError(JoinPoint j) {
		Logger log = LoggerFactory.getLogger(j.getTarget()+""+j.getKind());
		log.debug("에러"+j.getArgs());
		log.debug("에러"+j.toString());
	}
}
