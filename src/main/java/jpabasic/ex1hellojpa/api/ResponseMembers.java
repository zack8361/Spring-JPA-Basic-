package jpabasic.ex1hellojpa.api;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMembers<T> {
    private int count;
    private T data;
}
