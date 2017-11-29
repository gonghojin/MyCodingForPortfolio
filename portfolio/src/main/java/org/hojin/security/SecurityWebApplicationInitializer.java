package org.hojin.security;


//register the springSecurityFilter with application war using below mentioned initializer class.
public class SecurityWebApplicationInitializer {
	/**
     * 이게 시큐리티 필터체인 역할을 하기 떄문에 xml에 character utf8걸어도 시큐리타 먼저 먹음
     * 자바코드를 할 경우
     * SecurityConfig에 
     * CharacterEncodingFilter filter = new CharacterEncodingFilter();
	        filter.setEncoding("UTF-8");
	        filter.setForceEncoding(true);
	        http.addFilterBefore(filter,CsrfFilter.class);
	    
	    이걸 걸어줘야 하고 만약 xml코드를 할 경우 web.xml에
	    <!-- Spring Security Char Encoding Filter-->
 <filter>
 <filter-name>characterEncodingFilter</filter-name>
 <filter-class>
 org.springframework.web.filter.CharacterEncodingFilter
 </filter-class>
 <init-param>
 <param-name>encoding</param-name>
 <param-value>UTF-8</param-value>
 </init-param>
 <init-param>
 <param-name>forceEncoding</param-name>
 <param-value>true</param-value>
 </init-param>
 </filter>
 <!-- Spring Security Filter-->
 <filter>
 <filter-name>springSecurityFilterChain</filter-name>
 <filter-class>
 org.springframework.web.filter.DelegatingFilterProxy
 </filter-class>
 </filter>

<!-- the Filter’s mappings -->
 <filter-mapping>
 <filter-name>characterEncodingFilter</filter-name>
 <url-pattern>/*</url-pattern>
 </filter-mapping>

<filter-mapping>
 <filter-name>springSecurityFilterChain</filter-name>
 <url-pattern>/*</url-pattern>
 </filter-mapping>
	
	추가
     */
}
