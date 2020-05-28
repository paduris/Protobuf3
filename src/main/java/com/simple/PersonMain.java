package com.simple;

import com.person.Person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A Simple program using Protobuf3
 * write message
 * read message
 */
public class PersonMain {

    public static void main(String[] args) {

        Person.Builder personBuilder = Person.newBuilder();

        Person.PhoneNumber hPhoneNumber = Person.PhoneNumber.newBuilder().setType(Person.PhoneType.HOME).setNumber("123-121-4444").build();
        Person.PhoneNumber mPhoneNumber = Person.PhoneNumber.newBuilder().setType(Person.PhoneType.MOBILE).setNumber("123-121-3333").build();

        List<Person.PhoneNumber> phoneNumberList = new ArrayList<Person.PhoneNumber>();
        phoneNumberList.add(hPhoneNumber);
        phoneNumberList.add(mPhoneNumber);

        personBuilder.setEmail("paduris@somedns.com")
                .setId(34)
                .setName("Paduris")
                .addAllPhone(phoneNumberList);

        System.out.println(personBuilder.toString());


        //write to file
        try {
            FileOutputStream outputStream = new FileOutputStream("person_output");
            personBuilder.build().writeTo(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //read from file
        try {
            FileInputStream inputStream = new FileInputStream("person_output");
            Person readPerson = Person.parseFrom(inputStream);
            System.out.println(readPerson);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}