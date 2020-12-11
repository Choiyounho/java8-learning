package com.soten.java8.stream;

import com.soten.java8.Progress;

import java.util.Optional;

public class OnlineClass {

    private int id;

    private String title;

    private boolean closed;

    private Progress progress;

    public OnlineClass(int id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

//    public Progress getProgress() {
//        return progress;
//    }

    // 리턴 값에만 옵셔널을 넣는 것을 권장한다. null 값이 존재할 수도 있을 때는 Optional.ofNullable 사용
    public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress); // null 을 리턴 하기 보다 Optional.empty() 리턴 하는 것이 좋다
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
