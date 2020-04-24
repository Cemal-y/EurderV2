package com.cml.eurder.domain.user;

public class Employee extends User {

    protected Employee(Builder<?> builder) {
        super(builder);
    }


    public static abstract class Builder<T extends Employee> extends User.Builder<T> {

        public static Employee.Builder<?> customerBuilder() {
            return new Employee.Builder<Employee>() {
                @Override
                public Employee build() {
                    return new Employee(this);
                }
            };
        }
    }
}
