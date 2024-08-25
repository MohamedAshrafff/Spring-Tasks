package com.sumerge.mappers;

import com.sumerge.task3.DTOs.AuthorDTO;
import com.sumerge.task3.DatabaseClasses.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(source = "author_id" , target = "author_id")
    @Mapping(source = "author_name" , target = "author_name")
    @Mapping(source = "author_email" , target = "author_email")
    @Mapping(source = "author_birthdate" , target = "author_birthdate")
    @Mapping(source = "courses" , target = "courses")
    AuthorDTO authorToAuthorDTO(Author author);

}