package com.pojo;

public class Book {
    // id 主键
    private Integer id;
    // 书名
    private String bookName;
    // 作者
    private String author;
    // 出版社
    private String publisher;
    // 类别
    private String category;
    // 标签
    private String label;
    // 总量
    private Integer totalNumber;
    //余量
    private Integer remainNumber;

    //构造函数
    public Book(Integer id, String bookName, String author, String publisher, String category, String label, Integer totalNumber, Integer remainNumber) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.label = label;
        this.totalNumber = totalNumber;
        this.remainNumber = remainNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getRemainNumber() {
        return remainNumber;
    }

    public void setRemainNumber(Integer remainNumber) {
        this.remainNumber = remainNumber;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", category='" + category + '\'' +
                ", label='" + label + '\'' +
                ", totalNumber=" + totalNumber +
                ", remainNumber=" + remainNumber +
                '}';
    }
}
