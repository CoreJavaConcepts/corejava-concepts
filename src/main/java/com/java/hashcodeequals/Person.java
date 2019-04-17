package com.java.hashcodeequals;

import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //https://www.sitepoint.com/implement-javas-equals-method-correctly/
    // equals contract

    //You must override hashCode in every class that overrides equals. Failure to do so
    //will result in a violation of the general contract for Object

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName); //its java.util.Objects, having utility methods, since 1.7,
        //These utilities include null-safe or null-tolerant
        // methods for computing the hash code of an object, returning a string for an object, and comparing two objects.
        // internally it does same as below implementation of hashcode

        //hash code computation should not include any field that is not used for equality checks
    }

   /* @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = (result * prime) + (firstName == null ? 0 : firstName.hashCode());
        result = (result * prime) + (lastName == null ? 0 : lastName.hashCode());
        return result;
    }*/

    @Override
    public boolean equals(Object obj) {
        //compare reference
        if(this == obj) return true;

        //compare class
        if(!(obj instanceof Person)) return false;
        //extending class with new fields does not compare well may be reasonable, but if that extension
        // only adds behavior (maybe logging or other non-functional details), it should be able to equal instances of its supertype.
        // This becomes especially relevant if a framework spins new subtypes at runtime
        // (e.g. Hibernate or Spring), which could then never be equal to instances we created.

        //Say Employee extends Person and adds an additional field. If it overrides the equals implementation it inherits
        // from Person and includes the extra field, then person.equals(employee) can be true (because of instanceof)
        // but employee.equals(person) can’t (because person misses that field). This clearly violates the symmetry requirement
        //Use instanceof but make equals final because there is no way to override it correctly.

        //if(obj == null || obj.getClass() != this.getClass()) return false;
        //Use getClass and be aware that instances of the type and its subtypes can never equal.


        //compare fields
        Person person = (Person)obj;
        return Objects.equals(person.firstName, this.firstName) &&
                Objects.equals(person.lastName, this.lastName);//internal implementation is .equals only


    }
}
