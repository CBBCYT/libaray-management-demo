package com.pojo;

public class BorrowInfo extends Borrow{

    private String userName;
    private String bookName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BorrowInfo(String userName, String bookName, Borrow borrow)
    {
        this.userName = userName;
        this.bookName = bookName;
        setId(borrow.getId());
        setUserId(borrow.getUserId());
        setBookId(borrow.getBookId());
        setBorrowTime(borrow.getBorrowTime());
        setReturnTime(borrow.getReturnTime());
    }
}
