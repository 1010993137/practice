package com.houfz.framework.security.context;

import org.springframework.security.core.Authentication;

/**
 * 身份验证信息
 * 
 * @author houfz
 */
public class AuthenticationContextHolder
{
    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContext()
    {
        return contextHolder.get();
    }

    public static void setContext(Authentication context)
    {
        contextHolder.set(context);
    }

    public static void clearContext()
    {
        contextHolder.remove();
    }
}
