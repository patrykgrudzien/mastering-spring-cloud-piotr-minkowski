package com.jurik99.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "person")
public class Person {

	@Id
    private String id;  // changed type from Long to String because MongoDB generates primary keys is UUID format

    private String firstName;
    private String lastName;
    private int age;
    private String gender;
}
