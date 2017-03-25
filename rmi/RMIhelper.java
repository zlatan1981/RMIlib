package rmi;

import java.lang.reflect.Method;

public class RMIhelper {

	public RMIhelper() {
		// TODO Auto-generated constructor stub
	}

	public static <T> boolean isremoteinterface(Class<T> c) {

		if (c == null || !c.isInterface())
			return false;

		Method[] servermethods = c.getMethods();
		for (Method m : servermethods) {
			Class[] excs = m.getExceptionTypes();
			boolean findrmiexc = false;
			for (Class e : excs) {
				if (e.getName().contains("RMIException")) {
					findrmiexc = true;
					break;
				}
			}
			if (!findrmiexc)
				return false;
		}
		return true;
	}

	public static <T> boolean isequals(Method method) {
		String methodName = method.getName();
		return (methodName.equals("equals") && method.getParameterTypes().length == 1
				&& method.getParameterTypes()[0].getName().equals("java.lang.Object")
				&& method.getReturnType().getName().equals("boolean"));

	}

	public static <T> boolean istostring(Method method) {
		String methodName = method.getName();
		return (methodName.equals("toString") && method.getParameterTypes().length == 0
				&& method.getReturnType().getName().equals("java.lang.String"));

	}

	public static <T> boolean ishashcode(Method method) {
		String methodName = method.getName();
		return (methodName.equals("hashCode") && method.getParameterTypes().length == 0
				&& method.getReturnType().getName().equals("int"));

	}
}
