package com.christinagorina.homework.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {
    @ChangeSet(order = "001", id = "dropDb", author = "gorina", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertBooks", author = "gorina")
    public void insertBooks(MongoDatabase db) {
        MongoCollection<Document> bookCollection = db.getCollection("books");
        List<Document> bookList = new ArrayList<>();
        var book1 = Document.parse("{\"_id\":\"1\", \"name\":\"Love story\", \"genre\":\"Mystic\", \"year\":\"1878\", \"language\":\"English\", \"authors\": [{\"name\":\"Orlov G. O.\"}], \"lastComments\": [\"Comment 1 for book 1\", \"Comment 2 for book 1\", \"Comment 3 for book 1\"]}");
        var book2 = Document.parse("{\"_id\":\"2\", \"name\":\"Call of the night\", \"genre\":\"Fantastic\", \"year\":\"2020\", \"language\":\"Russian\", \"authors\": [{\"name\":\"Somov E. V.\"}], \"lastComments\": [\"Comment 8 for book 2\", \"Comment 9 for book 2\", \"Comment 10 for book 2\"]}");
        var book3 = Document.parse("{\"_id\":\"3\", \"name\":\"Close your eyes\", \"genre\":\"Horror\", \"year\":\"1890\", \"language\":\"German\", \"authors\": [{\"name\":\"Levin O. F.\"}], \"lastComments\": [\"Comment 13 for book 3\", \"Comment 14 for book 3\", \"Comment 15 for book 3\"]}");
        var book4 = Document.parse("{\"_id\":\"4\", \"name\":\"Bloodlust\", \"genre\":\"Drama\", \"year\":\"2001\", \"language\":\"Finnish\", \"authors\": [{\"name\":\"Emilov T. N.\"}], \"lastComments\": [\"Comment 18 for book 4\", \"Comment 19 for book 4\", \"Comment 20 for book 4\"]}");
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookCollection.insertMany(bookList);
    }

    @ChangeSet(order = "003", id = "insertComments", author = "gorina")
    public void insertComments(MongoDatabase db) {
        MongoCollection<Document> commentCollection = db.getCollection("comments");
        List<Document> commentList = new ArrayList<>();
        var comment1 = Document.parse("{\"_id\":\"1\", \"text\":\"Comment 1 for book 1\", \"book_id\":\"1\", \"date\":\"2021-07-01T17:45:55.9483536\"}");
        var comment2 = Document.parse("{\"_id\":\"2\", \"text\":\"Comment 2 for book 1\", \"book_id\":\"1\", \"date\":\"2021-07-01T18:46:55.9483536\"}");
        var comment3 = Document.parse("{\"_id\":\"3\", \"text\":\"Comment 3 for book 1\", \"book_id\":\"1\", \"date\":\"2021-07-01T19:47:55.9483536\"}");
        var comment4 = Document.parse("{\"_id\":\"4\", \"text\":\"Comment 4 for book 1\", \"book_id\":\"1\", \"date\":\"2021-07-01T20:48:55.9483536\"}");
        var comment5 = Document.parse("{\"_id\":\"5\", \"text\":\"Comment 5 for book 1\", \"book_id\":\"1\", \"date\":\"2021-07-01T21:49:55.9483536\"}");
        var comment6 = Document.parse("{\"_id\":\"6\", \"text\":\"Comment 6 for book 2\", \"book_id\":\"2\", \"date\":\"2021-07-01T12:50:55.9483536\"}");
        var comment7 = Document.parse("{\"_id\":\"7\", \"text\":\"Comment 7 for book 2\", \"book_id\":\"2\", \"date\":\"2021-07-01T13:41:55.9483536\"}");
        var comment8 = Document.parse("{\"_id\":\"8\", \"text\":\"Comment 8 for book 2\", \"book_id\":\"2\", \"date\":\"2021-07-01T14:42:55.9483536\"}");
        var comment9 = Document.parse("{\"_id\":\"9\", \"text\":\"Comment 9 for book 2\", \"book_id\":\"2\", \"date\":\"2021-07-01T15:43:55.9483536\"}");
        var comment10 = Document.parse("{\"_id\":\"10\", \"text\":\"Comment 10 for book 2\", \"book_id\":\"2\", \"date\":\"2021-07-01T16:44:55.9483536\"}");
        var comment11 = Document.parse("{\"_id\":\"11\", \"text\":\"Comment 11 for book 3\", \"book_id\":\"3\", \"date\":\"2021-07-01T18:21:55.9483536\"}");
        var comment12 = Document.parse("{\"_id\":\"12\", \"text\":\"Comment 12 for book 3\", \"book_id\":\"3\", \"date\":\"2021-07-01T19:22:55.9483536\"}");
        var comment13 = Document.parse("{\"_id\":\"13\", \"text\":\"Comment 13 for book 3\", \"book_id\":\"3\", \"date\":\"2021-07-01T20:23:55.9483536\"}");
        var comment14 = Document.parse("{\"_id\":\"14\", \"text\":\"Comment 14 for book 3\", \"book_id\":\"3\", \"date\":\"2021-07-01T21:24:55.9483536\"}");
        var comment15 = Document.parse("{\"_id\":\"15\", \"text\":\"Comment 15 for book 3\", \"book_id\":\"3\", \"date\":\"2021-07-01T22:25:55.9483536\"}");
        var comment16 = Document.parse("{\"_id\":\"16\", \"text\":\"Comment 16 for book 4\", \"book_id\":\"4\", \"date\":\"2021-07-01T12:23:55.9483536\"}");
        var comment17 = Document.parse("{\"_id\":\"17\", \"text\":\"Comment 17 for book 4\", \"book_id\":\"4\", \"date\":\"2021-07-01T13:25:55.9483536\"}");
        var comment18 = Document.parse("{\"_id\":\"18\", \"text\":\"Comment 18 for book 4\", \"book_id\":\"4\", \"date\":\"2021-07-01T14:28:55.9483536\"}");
        var comment19 = Document.parse("{\"_id\":\"19\", \"text\":\"Comment 19 for book 4\", \"book_id\":\"4\", \"date\":\"2021-07-01T14:27:55.9483536\"}");
        var comment20 = Document.parse("{\"_id\":\"20\", \"text\":\"Comment 20 for book 4\", \"book_id\":\"4\", \"date\":\"2021-07-01T14:26:55.9483536\"}");
        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);
        commentList.add(comment4);
        commentList.add(comment5);
        commentList.add(comment6);
        commentList.add(comment7);
        commentList.add(comment8);
        commentList.add(comment9);
        commentList.add(comment10);
        commentList.add(comment11);
        commentList.add(comment12);
        commentList.add(comment13);
        commentList.add(comment14);
        commentList.add(comment15);
        commentList.add(comment16);
        commentList.add(comment17);
        commentList.add(comment18);
        commentList.add(comment19);
        commentList.add(comment20);
        commentCollection.insertMany(commentList);
    }

    @ChangeSet(order = "004", id = "insertGenre", author = "gorina")
    public void insertGenre(MongoDatabase db) {
        MongoCollection<Document> genreCollection = db.getCollection("genres");
        List<Document> genreList = new ArrayList<>();
        var genre1 = Document.parse("{\"_id\":\"1\", \"name\":\"Mystic\", \"books\": [{\"name\": \"Love story\", \"year\": \"1878\"}]}");
        var genre2 = Document.parse("{\"_id\":\"2\", \"name\":\"Fantastic\", \"books\": [{\"name\": \"Call of the night\", \"year\": \"2020\"}]}");
        var genre3 = Document.parse("{\"_id\":\"3\", \"name\":\"Horror\", \"books\": [{\"name\": \"Close your eyes\", \"year\": \"1890\"}]}");
        var genre4 = Document.parse("{\"_id\":\"4\", \"name\":\"Drama\", \"books\": [{\"name\": \"Bloodlust\", \"year\": \"2021\"}]}");
        genreList.add(genre1);
        genreList.add(genre2);
        genreList.add(genre3);
        genreList.add(genre4);
        genreCollection.insertMany(genreList);
    }

    @ChangeSet(order = "005", id = "insertAuthor", author = "gorina")
    public void insertAuthor(MongoDatabase db) {
        MongoCollection<Document> authorCollection = db.getCollection("authors");
        List<Document> authorList = new ArrayList<>();
        var author1 = Document.parse("{\"_id\":\"1\", \"name\": \"Orlov G. O.\", \"booksNames\": [\"Love story\"]}");
        var author2 = Document.parse("{\"_id\":\"2\", \"name\": \"Somov E. V.\", \"booksNames\": [\"Call of the night\"]}");
        var author3 = Document.parse("{\"_id\":\"3\", \"name\": \"Levin O. F.\", \"booksNames\": [\"Close your eyes\"]}");
        var author4 = Document.parse("{\"_id\":\"4\", \"name\": \"Emilov T. N.\", \"booksNames\": [\"Bloodlust\"]}");
        authorList.add(author1);
        authorList.add(author2);
        authorList.add(author3);
        authorList.add(author4);
        authorCollection.insertMany(authorList);
    }

}
