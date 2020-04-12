package com.example.helloji;

public class DataNote
{
    String text;
    String post;
    String name;
    String hash;

    public DataNote(String text, String post, String name, String hash)
    {
        this.text = text;
        this.post = post;
        this.name = name;
        this.hash = hash;
    }

    public String getText()
    {
        return text;
    }

    public String getPost()
    {
        return post;
    }

    public String getName()
    {
        return name;
    }

    public String getHash()
    {
        return hash;
    }
}