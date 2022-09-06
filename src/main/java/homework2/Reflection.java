package homework2;

import homework2.person.Customer;
import homework2.publication.Genre;

import java.lang.reflect.*;

public class Reflection {
    public static void main(String[] args) {

        Class<Customer> reflectClass = Customer.class;

        String className = reflectClass.getName();

        System.out.println("className = " + className);

        int classModifiers = reflectClass.getModifiers();

        System.out.println("classModifiers = " + Modifier.isPublic(classModifiers));

        Class<? super Customer> classSuper = reflectClass.getSuperclass();

        System.out.println("classSuper = " + classSuper);

        Method[] classMethods = reflectClass.getMethods();

        for (Method method: classMethods){
            System.out.println("method.getName() = " + method.getName());

            System.out.println("return type: " + method.getReturnType());

            Class[] parameterTypes = method.getParameterTypes();
            for (Class parameter: parameterTypes) {
                System.out.println("parameter.getName() = " + parameter.getName());
            }
        }

        Constructor constructor = null;

        try {
            constructor = reflectClass.getConstructor(String.class, Genre.class);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        Customer newCustomer = null;

        String name = "Tomas";

        Genre genre = null;

        try {
            newCustomer = (Customer) constructor.newInstance(name, genre);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        newCustomer.setPreferences(Genre.WAR);


    }
}
